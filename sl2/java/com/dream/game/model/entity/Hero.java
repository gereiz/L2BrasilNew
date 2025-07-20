package com.dream.game.model.entity;

import com.dream.Config;
import com.dream.L2DatabaseFactory;
import com.dream.game.datatables.HeroSkillTable;
import com.dream.game.datatables.sql.ClanTable;
import com.dream.game.model.L2Clan;
import com.dream.game.model.L2Skill;
import com.dream.game.model.actor.instance.L2ItemInstance;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.model.olympiad.Olympiad;
import com.dream.game.model.world.L2World;
import com.dream.game.network.SystemMessageId;
import com.dream.game.network.serverpackets.InventoryUpdate;
import com.dream.game.network.serverpackets.PledgeShowInfoUpdate;
import com.dream.game.network.serverpackets.SocialAction;
import com.dream.game.network.serverpackets.SystemMessage;
import com.dream.game.network.serverpackets.UserInfo;
import com.dream.game.templates.item.L2Item;
import com.dream.game.util.L2Utils;
import com.dream.util.StatsSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

public class Hero
{
	private final static Logger _log = Logger.getLogger(Hero.class.getName());

	private static Hero _instance;

	private static final String GET_HEROES = "SELECT * FROM heroes WHERE played = 1";

	private static final String GET_ALL_HEROES = "SELECT * FROM heroes";

	private static final String UPDATE_ALL = "UPDATE heroes SET played = 0";

	private static final String INSERT_HERO = "INSERT INTO heroes VALUES (?,?,?,?,?)";
	private static final String UPDATE_HERO = "UPDATE heroes SET count = ?, played = ?" + " WHERE charId = ?";
	private static final String GET_CLAN_ALLY = "SELECT characters.clanid AS clanid, coalesce(clan_data.ally_Id, 0) AS allyId FROM characters LEFT JOIN clan_data ON clan_data.clan_id = characters.clanid " + " WHERE characters.charId = ?";
	private static final String GET_CLAN_NAME = "SELECT clan_name FROM clan_data WHERE clan_id = (SELECT clanid FROM characters WHERE char_name = ?)";
	private static final String DELETE_ITEMS = "DELETE FROM items WHERE item_id IN " + "(6842, 6611, 6612, 6613, 6614, 6615, 6616, 6617, 6618, 6619, 6620, 6621, 9388, 9389, 9390) " + "AND owner_id NOT IN (SELECT charId FROM characters)";
	private static final String DELETE_SKILLS = "DELETE FROM character_skills WHERE skill_id IN " + "(395, 396, 1374, 1375, 1376) " + "AND charId NOT IN (SELECT charId FROM characters)";

	private static Map<Integer, StatsSet> _heroes;
	private static Map<Integer, StatsSet> _completeHeroes;

	public static final String COUNT = "count";
	public static final String PLAYED = "played";
	public static final String CLAN_NAME = "clan_name";
	public static final String CLAN_CREST = "clan_crest";
	public static final String ALLY_NAME = "ally_name";
	public static final String ALLY_CREST = "ally_crest";

	
	private static void deleteItemsInDb()
	{
		Connection con = null;

		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement(DELETE_ITEMS);
			statement.execute();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.error("", e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	
	private static void deleteSkillsInDb()
	{
		Connection con = null;

		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement(DELETE_SKILLS);
			statement.execute();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.error(e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	public static Hero getInstance()
	{
		if (_instance == null)
		{
			_instance = new Hero();
		}
		return _instance;
	}

	private static void init()
	{
		_heroes = new ConcurrentHashMap<>();
		_completeHeroes = new HashMap<>();

		Connection con = null;
		Connection con2 = null;

		PreparedStatement statement;
		PreparedStatement statement2;

		ResultSet rset;
		ResultSet rset2;

		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			con2 = L2DatabaseFactory.getInstance().getConnection(con2);
			statement = con.prepareStatement(GET_HEROES);
			rset = statement.executeQuery();

			while (rset.next())
			{
				StatsSet hero = new StatsSet();
				int charId = rset.getInt(Olympiad.CHAR_ID);
				hero.set(Olympiad.CHAR_NAME, rset.getString(Olympiad.CHAR_NAME));
				hero.set(Olympiad.CLASS_ID, rset.getInt(Olympiad.CLASS_ID));
				hero.set(COUNT, rset.getInt(COUNT));
				hero.set(PLAYED, rset.getInt(PLAYED));

				statement2 = con2.prepareStatement(GET_CLAN_ALLY);
				statement2.setInt(1, charId);
				rset2 = statement2.executeQuery();

				initRelationBetweenHeroAndClan(rset2, hero);

				rset2.close();
				statement2.close();

				_heroes.put(charId, hero);
			}

			rset.close();
			statement.close();

			statement = con.prepareStatement(GET_ALL_HEROES);
			rset = statement.executeQuery();

			while (rset.next())
			{
				StatsSet hero = new StatsSet();
				int charId = rset.getInt(Olympiad.CHAR_ID);
				hero.set(Olympiad.CHAR_NAME, rset.getString(Olympiad.CHAR_NAME));
				hero.set(Olympiad.CLASS_ID, rset.getInt(Olympiad.CLASS_ID));
				hero.set(COUNT, rset.getInt(COUNT));
				hero.set(PLAYED, rset.getInt(PLAYED));

				statement2 = con2.prepareStatement(GET_CLAN_ALLY);
				statement2.setInt(1, charId);
				rset2 = statement2.executeQuery();

				initRelationBetweenHeroAndClan(rset2, hero);

				rset2.close();
				statement2.close();

				_completeHeroes.put(charId, hero);
			}

			rset.close();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.warn("HeroSystem: Couldnt load Heroes");
		}
		finally
		{
			try
			{
				if (con != null)
				{
					con.close();
				}

				if (con2 != null)
				{
					con2.close();
				}
			}
			catch (Exception e)
			{
			}
		}

		_log.info("Hero Data: Loaded " + _heroes.size() + " Heroes.");
		_log.info("Hero Data: Loaded " + _completeHeroes.size() + " all time Heroes.");
	}

	private static void initRelationBetweenHeroAndClan(ResultSet resultSet, StatsSet hero) throws SQLException
	{
		if (resultSet.next())
		{
			int clanId = resultSet.getInt("clanid");
			int allyId = resultSet.getInt("allyId");

			String clanName = "";
			String allyName = "";
			int clanCrest = 0;
			int allyCrest = 0;

			L2Clan clan = ClanTable.getInstance().getClan(clanId);

			if (clan != null)
			{
				if (clanId > 0)
				{
					clanName = clan.getName();
					clanCrest = clan.getCrestId();

					if (allyId > 0)
					{
						allyName = clan.getAllyName();
						allyCrest = clan.getAllyCrestId();
					}
				}

				hero.set(CLAN_CREST, clanCrest);
				hero.set(CLAN_NAME, clanName);
				hero.set(ALLY_CREST, allyCrest);
				hero.set(ALLY_NAME, allyName);
			}
			else if (hero.getInteger(PLAYED, 1) == 1)
				if (Config.HERO_LOG_NOCLAN)
				{
					_log.warn("Hero: initRelationBetweenHeroAndClan: " + hero.getString(Olympiad.CHAR_NAME));
				}
		}
	}

	public Hero()
	{
		init();
	}

	
	public synchronized void computeNewHeroes(List<StatsSet> newHeroes)
	{
		updateHeroes(true);

		L2ItemInstance[] items;
		InventoryUpdate iu;

		if (!_heroes.isEmpty())
		{
			for (StatsSet hero : _heroes.values())
			{
				String name = hero.getString(Olympiad.CHAR_NAME);

				L2PcInstance player = L2Utils.loadPlayer(name);

				if (player == null)
				{
					continue;
				}
				try
				{
					player.setHero(false);

					items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_LR_HAND);
					iu = new InventoryUpdate();
					for (L2ItemInstance item : items)
					{
						iu.addModifiedItem(item);
					}

					player.sendPacket(iu);

					items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_R_HAND);
					iu = new InventoryUpdate();
					for (L2ItemInstance item : items)
					{
						iu.addModifiedItem(item);
					}

					player.sendPacket(iu);

					items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_HAIR);
					iu = new InventoryUpdate();
					for (L2ItemInstance item : items)
					{
						iu.addModifiedItem(item);
					}

					player.sendPacket(iu);

					items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_FACE);
					iu = new InventoryUpdate();
					for (L2ItemInstance item : items)
					{
						iu.addModifiedItem(item);
					}

					player.sendPacket(iu);

					items = player.getInventory().unEquipItemInBodySlotAndRecord(L2Item.SLOT_HAIRALL);
					iu = new InventoryUpdate();
					for (L2ItemInstance item : items)
					{
						iu.addModifiedItem(item);
					}

					player.sendPacket(iu);

					for (L2ItemInstance item : player.getInventory().getAvailableItems(false))
					{
						if (item == null)
						{
							continue;
						}
						if (!item.isHeroItem())
						{
							continue;
						}

						player.destroyItem("Hero", item, null, true);
						iu = new InventoryUpdate();
						iu.addRemovedItem(item);
						player.sendPacket(iu);
					}

					player.sendPacket(new UserInfo(player));
					player.broadcastUserInfo();
				}
				catch (NullPointerException e)
				{
				}
			}
		}

		if (newHeroes.size() == 0)
		{
			_heroes.clear();
			return;
		}

		Map<Integer, StatsSet> heroes = new HashMap<>();

		for (StatsSet hero : newHeroes)
		{
			int charId = hero.getInteger(Olympiad.CHAR_ID);

			if (_completeHeroes != null && _completeHeroes.containsKey(charId))
			{
				StatsSet oldHero = _completeHeroes.get(charId);
				int count = oldHero.getInteger(COUNT);
				oldHero.set(COUNT, count + 1);
				oldHero.set(PLAYED, 1);

				heroes.put(charId, oldHero);
			}
			else
			{
				StatsSet newHero = new StatsSet();
				newHero.set(Olympiad.CHAR_NAME, hero.getString(Olympiad.CHAR_NAME));
				newHero.set(Olympiad.CLASS_ID, hero.getInteger(Olympiad.CLASS_ID));
				newHero.set(COUNT, 1);
				newHero.set(PLAYED, 1);

				heroes.put(charId, newHero);
			}
		}

		deleteItemsInDb();
		deleteSkillsInDb();

		_heroes.clear();
		_heroes.putAll(heroes);
		heroes.clear();

		updateHeroes(false);

		for (StatsSet hero : _heroes.values())
		{
			String name = hero.getString(Olympiad.CHAR_NAME);

			L2PcInstance player = L2World.getInstance().getPlayer(name);

			if (player != null)
			{
				player.broadcastPacket(new SocialAction(player, 16));
				player.setHero(true);
				L2Clan clan = player.getClan();
				if (clan != null)
				{
					clan.setReputationScore(clan.getReputationScore() + 1000, true);
					clan.broadcastToOnlineMembers(new PledgeShowInfoUpdate(clan));
					clan.broadcastToOnlineMembers(SystemMessage.getSystemMessage(SystemMessageId.CLAN_MEMBER_S1_BECAME_HERO_AND_GAINED_S2_REPUTATION_POINTS).addString(name).addNumber(1000));
				}
				player.broadcastUserInfo();

				for (L2Skill skill : HeroSkillTable.getHeroSkills())
				{
					player.addSkill(skill);
				}
			}
			else
			{
				Connection con = null;

				try
				{
					con = L2DatabaseFactory.getInstance().getConnection(con);
					PreparedStatement statement = con.prepareStatement(GET_CLAN_NAME);
					statement.setString(1, name);
					ResultSet rset = statement.executeQuery();
					if (rset.next())
					{
						String clanName = rset.getString("clan_name");
						if (clanName != null)
						{
							L2Clan clan = ClanTable.getInstance().getClanByName(clanName);
							if (clan != null)
							{
								clan.setReputationScore(clan.getReputationScore() + 1000, true);
								clan.broadcastToOnlineMembers(new PledgeShowInfoUpdate(clan));
								clan.broadcastToOnlineMembers(SystemMessage.getSystemMessage(SystemMessageId.CLAN_MEMBER_S1_BECAME_HERO_AND_GAINED_S2_REPUTATION_POINTS).addString(name).addNumber(1000));
							}
						}
					}

					rset.close();
					statement.close();
				}
				catch (Exception e)
				{
					_log.warn("HeroSystem: Couldnt get Clanname of " + name);
				}
				finally
				{
					L2DatabaseFactory.close(con);
				}
			}
		}
	}

	public Map<Integer, StatsSet> getHeroes()
	{
		return _heroes;
	}

	
	public void updateHeroes(boolean setDefault)
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			if (setDefault)
			{
				try
				{
					PreparedStatement statement = con.prepareStatement(UPDATE_ALL);
					statement.execute();
					statement.close();
				}
				catch (SQLException e)
				{
					_log.warn("HeroSystem: Couldnt update all Heroes");
				}
				finally
				{
					L2DatabaseFactory.close(con);
				}
			}
			else
			{
				PreparedStatement statement;

				for (Integer heroId : _heroes.keySet())
				{
					StatsSet hero = _heroes.get(heroId);

					if (_completeHeroes == null || !_completeHeroes.containsKey(heroId))
					{
						try
						{
							statement = con.prepareStatement(INSERT_HERO);
							statement.setInt(1, heroId);
							statement.setString(2, hero.getString(Olympiad.CHAR_NAME));
							statement.setInt(3, hero.getInteger(Olympiad.CLASS_ID));
							statement.setInt(4, hero.getInteger(COUNT));
							statement.setInt(5, hero.getInteger(PLAYED));
							statement.execute();

							Connection con2 = null;
							con2 = L2DatabaseFactory.getInstance().getConnection(con2);
							PreparedStatement statement2 = con2.prepareStatement(GET_CLAN_ALLY);
							statement2.setInt(1, heroId);
							ResultSet rset2 = statement2.executeQuery();

							initRelationBetweenHeroAndClan(rset2, hero);

							rset2.close();
							statement2.close();
							con2.close();

							_heroes.remove(heroId);
							_heroes.put(heroId, hero);

							_completeHeroes.put(heroId, hero);

							statement.close();
						}
						catch (SQLException e)
						{
							_log.warn("HeroSystem: Couldnt insert Heroes");
						}
					}
					else
					{
						try
						{
							statement = con.prepareStatement(UPDATE_HERO);
							statement.setInt(1, hero.getInteger(COUNT));
							statement.setInt(2, hero.getInteger(PLAYED));
							statement.setInt(3, heroId);
							statement.execute();
							statement.close();
						}
						catch (SQLException e)
						{
							_log.warn("HeroSystem: Couldnt update Heroes");
						}
					}
				}
				L2DatabaseFactory.close(con);
			}
		}
		catch (Exception e)
		{
			_log.error("", e);
		}
	}
}