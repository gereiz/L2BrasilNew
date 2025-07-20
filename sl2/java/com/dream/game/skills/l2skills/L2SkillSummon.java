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
package com.dream.game.skills.l2skills;

import com.dream.Config;
import com.dream.game.datatables.sql.NpcTable;
import com.dream.game.idfactory.IdFactory;
import com.dream.game.model.L2Object;
import com.dream.game.model.L2Skill;
import com.dream.game.model.actor.L2Character;
import com.dream.game.model.actor.instance.L2CubicInstance;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.model.actor.instance.L2SiegeSummonInstance;
import com.dream.game.model.actor.instance.L2SummonInstance;
import com.dream.game.model.base.Experience;
import com.dream.game.model.world.L2World;
import com.dream.game.network.SystemMessageId;
import com.dream.game.templates.chars.L2NpcTemplate;
import com.dream.util.StatsSet;

public class L2SkillSummon extends L2Skill
{
	private final int _npcId;
	private final float _expPenalty;

	public L2SkillSummon(StatsSet set)
	{
		super(set);

		_npcId = set.getInteger("npcId", 0);
		_expPenalty = set.getFloat("expPenalty", 0.f);
	}

	@Override
	public boolean checkCondition(L2Character activeChar, L2Object target)
	{
		if (activeChar instanceof L2PcInstance)
		{
			L2PcInstance player = (L2PcInstance) activeChar;
			if (isCubic())
				return true;

			if (player.inObserverMode())
				return false;

			if (player.isInOlympiadMode())
				return true;

			if (!Config.ALLOW_AIO_TELEPORT)
			{
				if (player.isAio())
					return false;
			}
			if (player.getPet() != null)
			{
				activeChar.sendPacket(SystemMessageId.SUMMON_ONLY_ONE);
				return false;
			}
		}
		return super.checkCondition(activeChar, target);
	}

	public int getNpcId()
	{
		return _npcId;
	}

	@Override
	public void useSkill(L2Character caster, L2Character... targets)
	{
		if (caster.isAlikeDead() || !(caster instanceof L2PcInstance))
			return;

		L2PcInstance activeChar = (L2PcInstance) caster;

		if (_npcId == 0)
			return;

		if (isCubic())
		{
			int skillLevel = getLevel();
			if (skillLevel >= 100)
			{
				skillLevel = getCubicSkillLevel();
			}
			if (targets.length > 1)
			{
				for (L2Character obj : targets)
				{
					if (!(obj instanceof L2PcInstance))
					{
						continue;
					}
					L2PcInstance player = (L2PcInstance) obj;
					int mastery = player.getSkillLevel(L2Skill.SKILL_CUBIC_MASTERY);
					if (mastery < 0)
					{
						mastery = 0;
					}
					if (mastery == 0 && !player.getCubics().isEmpty())
					{
						for (L2CubicInstance c : player.getCubics().values())
						{
							c.stopAction();
							c = null;
						}
						player.getCubics().clear();
					}
					if (player.getCubics().containsKey(_npcId))
					{
						L2CubicInstance cubic = player.getCubic(_npcId);
						cubic.stopAction();
						cubic.cancelDisappear();
						player.delCubic(_npcId);
					}
					if (player.getCubics().size() > mastery)
					{
						continue;
					}
					player.addCubic(_npcId, skillLevel, getPower(), getActivationTime(), getActivationChance(), getTotalLifeTime());
					player.broadcastUserInfo();
				}
				return;
			}

			int mastery = activeChar.getSkillLevel(L2Skill.SKILL_CUBIC_MASTERY);
			if (mastery < 0)
			{
				mastery = 0;
			}
			if (activeChar.getCubics().containsKey(_npcId))
			{
				L2CubicInstance cubic = activeChar.getCubic(_npcId);
				cubic.stopAction();
				cubic.cancelDisappear();
				activeChar.delCubic(_npcId);
			}
			if (activeChar.getCubics().size() > mastery)
			{
				if (_log.isDebugEnabled())
				{
					_log.debug("player can't summon any more cubics. ignore summon skill");
				}
				activeChar.sendPacket(SystemMessageId.CUBIC_SUMMONING_FAILED);
				return;
			}
			activeChar.addCubic(_npcId, skillLevel, getPower(), getActivationTime(), getActivationChance(), getTotalLifeTime());
			activeChar.broadcastUserInfo();
			return;
		}

		if (activeChar.getPet() != null || activeChar.isMounted())
		{
			if (_log.isDebugEnabled() || Config.DEBUG)
			{
				_log.debug("player has a pet already. ignore summon skill");
			}
			return;
		}

		L2SummonInstance summon;
		L2NpcTemplate summonTemplate = NpcTable.getInstance().getTemplate(_npcId);
		if (summonTemplate == null)
		{
			_log.warn("Summon attempt for nonexisting NPC ID:" + _npcId + ", skill ID:" + getId());
			return;
		}
		if (summonTemplate.getType().equalsIgnoreCase("L2SiegeSummon"))
		{
			summon = new L2SiegeSummonInstance(IdFactory.getInstance().getNextId(), summonTemplate, activeChar, this);
		}
		else
		{
			summon = new L2SummonInstance(IdFactory.getInstance().getNextId(), summonTemplate, activeChar, this);
		}

		summon.setName(summonTemplate.getName());
		summon.setTitle(activeChar.getName());
		summon.setExpPenalty(_expPenalty);
		if (summon.getLevel() >= Experience.LEVEL.length)
		{
			summon.getStat().setExp(Experience.LEVEL[Experience.LEVEL.length - 1]);
			_log.warn("Summon (" + summon.getName() + ") NpcID: " + summon.getNpcId() + " has a level above 75. Please rectify.");
		}
		else
		{
			summon.getStat().setExp(Experience.LEVEL[summon.getLevel() % Experience.LEVEL.length]);
		}

		summon.getStatus().setCurrentHp(summon.getMaxHp());
		summon.getStatus().setCurrentMp(summon.getMaxMp());
		summon.setHeading(activeChar.getHeading());
		summon.setRunning();
		activeChar.setPet(summon);

		L2World.getInstance().storeObject(summon);
		summon.spawnMe(activeChar.getX() + 20, activeChar.getY() + 20, activeChar.getZ());
	}
}
