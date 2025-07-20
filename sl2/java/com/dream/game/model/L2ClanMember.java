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
package com.dream.game.model;

import com.dream.Config;
import com.dream.L2DatabaseFactory;
import com.dream.game.manager.SiegeManager;
import com.dream.game.model.actor.instance.L2PcInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class L2ClanMember
{
	public static int calculatePledgeClass(L2PcInstance player)
	{
		int pledgeClass = 0;

		if (player == null)
			return pledgeClass;

		L2Clan clan = player.getClan();
		if (clan != null)
		{
			if (player.isHero())
				return 8;

			switch (player.getClan().getLevel())
			{
				case 4:
					if (player.isClanLeader())
					{
						pledgeClass = 3;
					}
					break;
				case 5:
					if (player.isClanLeader())
					{
						pledgeClass = 4;
					}
					else
					{
						pledgeClass = 2;
					}
					break;
				case 6:
					switch (player.getPledgeType())
					{
						case -1:
							pledgeClass = 1;
							break;
						case 100:
						case 200:
							pledgeClass = 2;
							break;
						case 0:
							if (player.isClanLeader())
							{
								pledgeClass = 5;
							}
							else
							{
								switch (clan.getLeaderSubPledge(player.getObjectId()))
								{
									case 100:
									case 200:
										pledgeClass = 4;
										break;
									case -1:
									default:
										pledgeClass = 3;
										break;
								}
							}
							break;
					}
					break;
				case 7:
					switch (player.getPledgeType())
					{
						case -1:
							pledgeClass = 1;
							break;
						case 100:
						case 200:
							pledgeClass = 3;
							break;
						case 1001:
						case 1002:
						case 2001:
						case 2002:
							pledgeClass = 2;
							break;
						case 0:
							if (player.isClanLeader())
							{
								pledgeClass = 7;
							}
							else
							{
								switch (clan.getLeaderSubPledge(player.getObjectId()))
								{
									case 100:
									case 200:
										pledgeClass = 6;
										break;
									case 1001:
									case 1002:
									case 2001:
									case 2002:
										pledgeClass = 5;
										break;
									case -1:
									default:
										pledgeClass = 4;
										break;
								}
							}
							break;
					}
					break;
				case 8:
					switch (player.getPledgeType())
					{
						case -1:
							pledgeClass = 1;
							break;

						case 100:
						case 200:
							pledgeClass = 4;
							break;

						case 1001:
						case 1002:
						case 2001:
						case 2002:
							pledgeClass = 3;
							break;

						case 0:
							if (player.isClanLeader())
							{
								pledgeClass = 8;
							}
							else
							{
								switch (clan.getLeaderSubPledge(player.getObjectId()))
								{
									case 100:
									case 200:
										pledgeClass = 7;
										break;

									case 1001:
									case 1002:
									case 2001:
									case 2002:
										pledgeClass = 6;
										break;

									case -1:
									default:
										pledgeClass = 5;
										break;
								}
							}
							break;
					}
					break;

				default:
					pledgeClass = 1;
					break;
			}
		}

		if (player.isHero() && pledgeClass < 8)
		{
			pledgeClass = 8;
		}
		else if (player.isNoble() && pledgeClass < 5)
		{
			pledgeClass = 5;
		}

		return pledgeClass;
	}

	public static int getCurrentPledgeClass(L2PcInstance activeChar)
	{
		if (activeChar.isHero())
			return 8;

		int pledgeClass = 0;
		if (activeChar.getClan() != null)
		{
			pledgeClass = calculatePledgeClass(activeChar);
		}

		if (activeChar.isNoble() && pledgeClass < 5)
			return 5;

		return pledgeClass;
	}

	private final L2Clan _clan;
	private int _objectId;
	private String _name;
	private String _title;
	private int _pledgeRank;
	private int _level;
	private int _classId;
	private L2PcInstance _player;
	private int _subPledgeType;
	private int _apprentice;
	private int _sponsor;

	private int _sex;

	private int _race;

	public L2ClanMember(L2Clan clan, L2PcInstance player)
	{
		_clan = clan;
		_player = player;
		_name = _player.getName();
		_level = _player.getLevel();
		_classId = _player.getClassId().getId();
		_objectId = _player.getObjectId();
		_pledgeRank = _player.getPledgeRank();
		_subPledgeType = _player.getPledgeType();
		_title = _player.getTitle();
		_apprentice = 0;
		_sponsor = 0;
		_sex = _player.getAppearance().getSex() ? 1 : 0;
		_race = _player.getRace().ordinal();
	}

	public L2ClanMember(L2Clan clan, String name, int level, int classId, int objectId, int subPledgeType, int pledgeRank, String title, int sex, int race)
	{
		if (clan == null)
			throw new IllegalArgumentException("Can not create a ClanMember with a null clan.");
		_clan = clan;
		_name = name;
		_level = level;
		_classId = classId;
		_objectId = objectId;
		_pledgeRank = pledgeRank;
		_title = title;
		_subPledgeType = subPledgeType;
		_apprentice = 0;
		_sponsor = 0;
		_sex = sex;
		_race = race;
	}

	public L2ClanMember(L2PcInstance player)
	{
		if (player.getClan() == null)
			throw new IllegalArgumentException("Can not create a ClanMember if player has a null clan.");
		_clan = player.getClan();
		_player = player;
		_name = _player.getName();
		_level = _player.getLevel();
		_classId = _player.getClassId().getId();
		_objectId = _player.getObjectId();
		_pledgeRank = _player.getPledgeRank();
		_subPledgeType = _player.getPledgeType();
		_title = _player.getTitle();
		_apprentice = 0;
		_sponsor = 0;
		_sex = _player.getAppearance().getSex() ? 1 : 0;
		_race = _player.getRace().ordinal();
	}

	public int getApprentice()
	{
		if (_player != null)
			return _player.getApprentice();

		return _apprentice;
	}

	public String getApprenticeOrSponsorName()
	{
		if (_player != null)
		{
			_apprentice = _player.getApprentice();
			_sponsor = _player.getSponsor();
		}

		if (_apprentice != 0)
		{
			L2ClanMember apprentice = _clan.getClanMember(_apprentice);
			if (apprentice != null)
				return apprentice.getName();

			return "Error";
		}
		if (_sponsor != 0)
		{
			L2ClanMember sponsor = _clan.getClanMember(_sponsor);
			if (sponsor != null)
				return sponsor.getName();

			return "Error";
		}
		return "";
	}

	public L2Clan getClan()
	{
		return _clan;
	}

	public int getClassId()
	{
		return _player != null ? _player.getClassId().getId() : _classId;
	}

	public int getLevel()
	{
		return _player != null ? _player.getLevel() : _level;
	}

	public String getName()
	{
		return _player != null ? _player.getName() : _name;
	}

	public int getObjectId()
	{
		return _player != null ? _player.getObjectId() : _objectId;
	}

	public L2PcInstance getPlayerInstance()
	{
		return _player;
	}

	public int getPledgeRank()
	{
		if (_player != null)
			return _player.getPledgeRank();
		return _pledgeRank;
	}

	public int getPledgeType()
	{
		return _player != null ? _player.getPledgeType() : _subPledgeType;
	}

	public int getRaceOrdinal()
	{
		return _player != null ? _player.getRace().ordinal() : _race;
	}

	public int getSex()
	{
		if (_player != null)
			return _player.getAppearance().getSex() ? 1 : 0;

		return _sex;
	}

	public int getSponsor()
	{
		if (_player != null)
			return _player.getSponsor();

		return _sponsor;
	}

	public String getTitle()
	{
		return _player != null ? _player.getTitle() : _title;
	}

	public void initApprenticeAndSponsor(int apprenticeID, int sponsorID)
	{
		_apprentice = apprenticeID;
		_sponsor = sponsorID;
	}

	public boolean isOnline()
	{
		return _player != null && _player.getClient() != null;
	}

	
	public void saveApprenticeAndSponsor(int apprentice, int sponsor)
	{
		Connection con = null;

		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement("UPDATE characters SET apprentice=?,sponsor=? WHERE charId=?");
			statement.setInt(1, apprentice);
			statement.setInt(2, sponsor);
			statement.setInt(3, getObjectId());
			statement.execute();
			statement.close();
		}
		catch (SQLException e)
		{
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	public void setPlayerInstance(L2PcInstance player)
	{
		if (player == null && _player != null)
		{
			_name = _player.getName();
			_level = _player.getLevel();
			_classId = _player.getClassId().getId();
			_objectId = _player.getObjectId();
			_pledgeRank = _player.getPledgeRank();
			_subPledgeType = _player.getPledgeType();
			_title = _player.getTitle();
			_apprentice = _player.getApprentice();
			_sponsor = _player.getSponsor();
			_sex = _player.getAppearance().getSex() ? 1 : 0;
			_race = _player.getRace().ordinal();
		}
		if (player != null)
		{
			if (_clan.getReputationScore() >= 0)
			{
				L2Skill[] skills = _clan.getAllSkills();
				for (L2Skill sk : skills)
					if (sk.getMinPledgeClass() <= player.getPledgeClass())
					{
						player.addSkill(sk, false);
					}
			}
			if (_clan.getLevel() >= Config.SIEGE_CLAN_MIN_LEVEL && player.isClanLeader())
			{
				SiegeManager.addSiegeSkills(player);
			}
			if (player.isClanLeader())
			{
				_clan.setLeader(this);
			}
		}
		_player = player;
	}

	public void setPledgeRank(int pledgeRank)
	{
		_pledgeRank = pledgeRank;
		if (_player != null)
		{
			_player.setPledgeRank(pledgeRank);
		}
		else
		{
			updatePledgeRank();
		}
	}

	public void setSubPledgeType(int subPledgeType)
	{
		_subPledgeType = subPledgeType;
		if (_player != null)
		{
			_player.setPledgeType(subPledgeType);
		}
		else
		{
			updateSubPledgeType();
		}
	}

	
	public void updatePledgeRank()
	{
		Connection con = null;

		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement("UPDATE characters SET pledge_rank=? WHERE charId=?");
			statement.setLong(1, _pledgeRank);
			statement.setInt(2, getObjectId());
			statement.execute();
			statement.close();
		}
		catch (Exception e)
		{
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	
	public void updateSubPledgeType()
	{
		Connection con = null;

		try
		{
			con = L2DatabaseFactory.getInstance().getConnection(con);
			PreparedStatement statement = con.prepareStatement("UPDATE characters SET subpledge=? WHERE charId=?");
			statement.setLong(1, _subPledgeType);
			statement.setInt(2, getObjectId());
			statement.execute();
			statement.close();
		}
		catch (Exception e)
		{
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
}