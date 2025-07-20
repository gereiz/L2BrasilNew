/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.dream.game.model.entity;

import com.dream.Config;
import com.dream.L2DatabaseFactory;
import com.dream.Message;
import com.dream.game.datatables.sql.ClanTable;
import com.dream.game.idfactory.IdFactory;
import com.dream.game.manager.AuctionManager;
import com.dream.game.manager.ClanHallManager;
import com.dream.game.model.L2Clan;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.model.world.L2World;
import com.dream.game.network.SystemMessageId;
import com.dream.game.network.ThreadPoolManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Auction
{
	public class AutoEndTask implements Runnable
	{
		@Override
		public void run()
		{
			try
			{
				endAuction();
			}
			catch (Exception e)
			{
				_log.error(e.getMessage(), e);
			}
		}
	}

	public class Bidder
	{
		private final String _name;
		private final String _clanName;
		private int _bid;
		private final Calendar _timeBid;

		public Bidder(String name, String clanName, int bid, long timeBid)
		{
			_name = name;
			_clanName = clanName;
			_bid = bid;
			_timeBid = Calendar.getInstance();
			_timeBid.setTimeInMillis(timeBid);
		}

		public int getBid()
		{
			return _bid;
		}

		public String getClanName()
		{
			return _clanName;
		}

		public String getName()
		{
			return _name;
		}

		public Calendar getTimeBid()
		{
			return _timeBid;
		}

		public void setBid(int bid)
		{
			_bid = bid;
		}

		public void setTimeBid(long timeBid)
		{
			_timeBid.setTimeInMillis(timeBid);
		}
	}

	public static enum ItemTypeEnum
	{
		ClanHall
	}

	protected static Logger _log = Logger.getLogger(Auction.class.getName());

	private static final String[] ItemTypeName =
	{
		"ClanHall"
	};

	public static String getItemTypeName(ItemTypeEnum value)
	{
		return ItemTypeName[value.ordinal()];
	}

	private int _id = 0;
	private final int _adenaId = Config.AUCTION_ITEM_ID;
	private long _endDate;
	private int _highestBidderId = 0;
	private String _highestBidderName = "";
	private int _highestBidderMaxBid = 0;
	private int _itemId = 0;
	private String _itemName = "";
	private int _itemObjectId = 0;
	private final int _itemQuantity = 0;
	private String _itemType = "";

	private int _sellerId = 0;

	private String _sellerClanName = "";

	private String _sellerName = "";

	private int _currentBid = 0;

	private int _startingBid = 0;

	private final Map<Integer, Bidder> _bidders = new HashMap<>();

	public Auction(int auctionId)
	{
		_id = auctionId;
		load();
		startAutoTask();
	}

	public Auction(int itemId, L2Clan Clan, long delay, int bid, String name)
	{
		_id = itemId;
		_endDate = System.currentTimeMillis() + delay;
		_itemId = itemId;
		_itemName = name;
		_itemType = "ClanHall";
		_sellerId = Clan.getLeaderId();
		_sellerName = Clan.getLeaderName();
		_sellerClanName = Clan.getName();
		_startingBid = bid;
	}

	public void cancelAuction()
	{
		deleteAuctionFromDB();
		removeBids();
	}

	
	public synchronized void cancelBid(int bidder)
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement;

			statement = con.prepareStatement("DELETE FROM auction_bid WHERE auctionId=? AND bidderId=?");
			statement.setInt(1, getId());
			statement.setInt(2, bidder);
			statement.execute();

			statement.close();
		}
		catch (Exception e)
		{
			_log.fatal("Exception: Auction.cancelBid(String bidder): " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}

		returnItem(_bidders.get(bidder).getClanName(), Config.AUCTION_ITEM_ID, _bidders.get(bidder).getBid(), true);
		ClanTable.getInstance().getClanByName(_bidders.get(bidder).getClanName()).setAuctionBiddedAt(0, true);
		_bidders.clear();
		loadBid();
	}

	
	public void confirmAuction()
	{
		AuctionManager.getInstance().getAuctions().add(this);
		Connection con = null;
		try
		{
			PreparedStatement statement;
			con = L2DatabaseFactory.getInstance().getConnection(con);

			statement = con.prepareStatement("INSERT INTO auction (id, sellerId, sellerName, sellerClanName, itemType, itemId, itemObjectId, itemName, itemQuantity, startingBid, currentBid, endDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, getId());
			statement.setInt(2, _sellerId);
			statement.setString(3, _sellerName);
			statement.setString(4, _sellerClanName);
			statement.setString(5, _itemType);
			statement.setInt(6, _itemId);
			statement.setInt(7, _itemObjectId);
			statement.setString(8, _itemName);
			statement.setInt(9, _itemQuantity);
			statement.setInt(10, _startingBid);
			statement.setInt(11, _currentBid);
			statement.setLong(12, _endDate);
			statement.execute();
			statement.close();
			loadBid();
		}
		catch (Exception e)
		{
			_log.fatal("Exception: Auction.load(): " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	
	public void deleteAuctionFromDB()
	{
		AuctionManager.getInstance().getAuctions().remove(this);
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement;
			statement = con.prepareStatement("DELETE FROM auction WHERE itemId=?");
			statement.setInt(1, _itemId);
			statement.execute();
			statement.close();
		}
		catch (Exception e)
		{
			_log.fatal("Exception: Auction.deleteFromDB(): " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	public void endAuction()
	{
		if (ClanHallManager.loaded())
		{
			if (_highestBidderId == 0 && _sellerId == 0)
			{
				startAutoTask();
				return;
			}
			if (_highestBidderId == 0 && _sellerId > 0)
			{
				int aucId = AuctionManager.getInstance().getAuctionIndex(_id);
				AuctionManager.getInstance().getAuctions().remove(aucId);
				return;
			}
			if (_sellerId > 0)
			{
				returnItem(_sellerClanName, Config.AUCTION_ITEM_ID, _highestBidderMaxBid, true);
				returnItem(_sellerClanName, Config.AUCTION_ITEM_ID, ClanHallManager.getInstance().getClanHallById(_itemId).getLease(), false);
			}
			deleteAuctionFromDB();
			L2Clan Clan = ClanTable.getInstance().getClanByName(_bidders.get(_highestBidderId).getClanName());
			removeBids();
			ClanHallManager.getInstance().setOwner(_itemId, Clan);
		}
		else
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new AutoEndTask(), 3000);
		}
	}

	public final Map<Integer, Bidder> getBidders()
	{
		return _bidders;
	}

	public final int getCurrentBid()
	{
		return _currentBid;
	}

	public final long getEndDate()
	{
		return _endDate;
	}

	public final int getHighestBidderId()
	{
		return _highestBidderId;
	}

	public final int getHighestBidderMaxBid()
	{
		return _highestBidderMaxBid;
	}

	public final String getHighestBidderName()
	{
		return _highestBidderName;
	}

	public final int getId()
	{
		return _id;
	}

	public final int getItemId()
	{
		return _itemId;
	}

	public final String getItemName()
	{
		return _itemName;
	}

	public final int getItemObjectId()
	{
		return _itemObjectId;
	}

	public final int getItemQuantity()
	{
		return _itemQuantity;
	}

	public final String getItemType()
	{
		return _itemType;
	}

	public final String getSellerClanName()
	{
		return _sellerClanName;
	}

	public final int getSellerId()
	{
		return _sellerId;
	}

	public final String getSellerName()
	{
		return _sellerName;
	}

	public final int getStartingBid()
	{
		return _startingBid;
	}

	
	private void load()
	{
		Connection con = null;
		try
		{
			PreparedStatement statement;
			ResultSet rs;

			con = L2DatabaseFactory.getInstance().getConnection(con);

			statement = con.prepareStatement("SELECT * FROM auction WHERE id = ?");
			statement.setInt(1, getId());
			rs = statement.executeQuery();

			while (rs.next())
			{
				_currentBid = rs.getInt("currentBid");
				_endDate = rs.getLong("endDate");
				_itemId = rs.getInt("itemId");
				_itemName = rs.getString("itemName");
				_itemObjectId = rs.getInt("itemObjectId");
				_itemType = rs.getString("itemType");
				_sellerId = rs.getInt("sellerId");
				_sellerClanName = rs.getString("sellerClanName");
				_sellerName = rs.getString("sellerName");
				_startingBid = rs.getInt("startingBid");
			}
			statement.close();
			loadBid();
		}
		catch (Exception e)
		{
			_log.error("Exception: Auction.load(): ", e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	
	private void loadBid()
	{
		_highestBidderId = 0;
		_highestBidderName = "";
		_highestBidderMaxBid = 0;

		Connection con = null;
		try
		{
			PreparedStatement statement;
			ResultSet rs;

			con = L2DatabaseFactory.getInstance().getConnection(con);

			statement = con.prepareStatement("SELECT bidderId, bidderName, maxBid, clan_name, time_bid FROM auction_bid WHERE auctionId = ? ORDER BY maxBid DESC");
			statement.setInt(1, getId());
			rs = statement.executeQuery();

			while (rs.next())
			{
				if (rs.isFirst())
				{
					_highestBidderId = rs.getInt("bidderId");
					_highestBidderName = rs.getString("bidderName");
					_highestBidderMaxBid = rs.getInt("maxBid");
				}
				_bidders.put(rs.getInt("bidderId"), new Bidder(rs.getString("bidderName"), rs.getString("clan_name"), rs.getInt("maxBid"), rs.getLong("time_bid")));
			}

			statement.close();
		}
		catch (Exception e)
		{
			_log.error("Exception: Auction.loadBid(): ", e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	
	private void removeBids()
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement;

			statement = con.prepareStatement("DELETE FROM auction_bid WHERE auctionId=?");
			statement.setInt(1, getId());
			statement.execute();

			statement.close();
		}
		catch (Exception e)
		{
			_log.fatal("Exception: Auction.deleteFromDB(): " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}

		for (Bidder b : _bidders.values())
		{
			if (ClanTable.getInstance().getClanByName(b.getClanName()).getHasHideout() == 0)
			{
				returnItem(b.getClanName(), Config.AUCTION_ITEM_ID, b.getBid() / 100 * 90, false); // 10 % tax
			}
			else
			{
				L2PcInstance bidder = L2World.getInstance().getPlayer(b.getName());
				if (bidder != null)
				{
					bidder.sendMessage(Message.getMessage(bidder, Message.MessageId.MSG_YOU_WIN_AUCTION));
				}
			}
			ClanTable.getInstance().getClanByName(b.getClanName()).setAuctionBiddedAt(0, true);
		}
		_bidders.clear();
	}

	private void returnItem(String Clan, int itemId, int quantity, boolean penalty)
	{
		if (penalty)
		{
			quantity *= 0.9;
		}
		ClanTable.getInstance().getClanByName(Clan).getWarehouse().addItem("Outbidded", _adenaId, quantity, null, null);
	}

	
	private void saveAuctionDate()
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement("UPDATE auction SET endDate = ? WHERE id = ?");
			statement.setLong(1, _endDate);
			statement.setInt(2, _id);
			statement.execute();

			statement.close();
		}
		catch (Exception e)
		{
			_log.fatal("Exception: saveAuctionDate(): " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	public synchronized void setBid(L2PcInstance bidder, int bid)
	{
		int requiredAdena = bid;
		if (getHighestBidderName().equals(bidder.getClan().getLeaderName()))
		{
			requiredAdena = bid - getHighestBidderMaxBid();
		}

		if (getHighestBidderId() > 0 && bid > getHighestBidderMaxBid() || getHighestBidderId() == 0 && bid >= getStartingBid())
			if (takeItem(bidder, _adenaId, requiredAdena))
			{
				updateInDB(bidder, bid);
				bidder.getClan().setAuctionBiddedAt(_id, true);
				return;
			}
		if (bid < getStartingBid() || bid <= getHighestBidderMaxBid())
		{
			bidder.sendPacket(SystemMessageId.BID_PRICE_MUST_BE_HIGHER);
		}
	}

	private void startAutoTask()
	{
		long currentTime = System.currentTimeMillis();
		long taskDelay = 0;
		if (_endDate <= currentTime)
		{
			_endDate = currentTime + 7 * 24 * 60 * 60 * 1000;
			saveAuctionDate();
		}
		else
		{
			taskDelay = _endDate - currentTime;
		}
		ThreadPoolManager.getInstance().scheduleGeneral(new AutoEndTask(), taskDelay);
	}

	private boolean takeItem(L2PcInstance bidder, int itemId, int quantity)
	{
		if (bidder.getClan() != null && bidder.getClan().getWarehouse().getAdena() >= quantity)
		{
			bidder.getClan().getWarehouse().destroyItemByItemId("Auction", _adenaId, quantity, bidder, bidder);
			return true;
		}
		bidder.sendPacket(SystemMessageId.NOT_ENOUGH_ADENA_IN_CWH);
		return false;
	}

	private void updateInDB(L2PcInstance bidder, int bid)
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement;

			if (getBidders().get(bidder.getClanId()) != null)
			{
				statement = con.prepareStatement("UPDATE auction_bid SET bidderId=?, bidderName=?, maxBid=?, time_bid=? WHERE auctionId=? AND bidderId=?");
				statement.setInt(1, bidder.getClanId());
				statement.setString(2, bidder.getClan().getLeaderName());
				statement.setInt(3, bid);
				statement.setLong(4, System.currentTimeMillis());
				statement.setInt(5, getId());
				statement.setInt(6, bidder.getClanId());
				statement.execute();
				statement.close();
			}
			else
			{
				statement = con.prepareStatement("INSERT INTO auction_bid (id, auctionId, bidderId, bidderName, maxBid, clan_name, time_bid) VALUES (?, ?, ?, ?, ?, ?, ?)");
				statement.setInt(1, IdFactory.getInstance().getNextId());
				statement.setInt(2, getId());
				statement.setInt(3, bidder.getClanId());
				statement.setString(4, bidder.getName());
				statement.setInt(5, bid);
				statement.setString(6, bidder.getClan().getName());
				statement.setLong(7, System.currentTimeMillis());
				statement.execute();
				statement.close();
				L2PcInstance highest = L2World.getInstance().getPlayer(_highestBidderName);
				if (highest != null)
				{
					highest.sendMessage(Message.getMessage(highest, Message.MessageId.MSG_YOU_OUT_BIDDED));
				}
			}
			_highestBidderId = bidder.getClanId();
			_highestBidderMaxBid = bid;
			_highestBidderName = bidder.getClan().getLeaderName();
			if (_bidders.get(_highestBidderId) == null)
			{
				_bidders.put(_highestBidderId, new Bidder(_highestBidderName, bidder.getClan().getName(), bid, System.currentTimeMillis()));
			}
			else
			{
				_bidders.get(_highestBidderId).setBid(bid);
				_bidders.get(_highestBidderId).setTimeBid(System.currentTimeMillis());
			}
			bidder.sendPacket(SystemMessageId.BID_IN_CLANHALL_AUCTION);
		}
		catch (Exception e)
		{
			_log.fatal("Exception: Auction.updateInDB(L2PcInstance bidder, int bid): ", e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
}