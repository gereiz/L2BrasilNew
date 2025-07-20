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
package com.dream.game.taskmanager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dream.game.model.actor.L2Character;
import com.dream.game.model.actor.L2Summon;
import com.dream.game.model.actor.instance.L2CubicInstance;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.network.serverpackets.AutoAttackStop;

public final class AttackStanceTaskManager extends AbstractPeriodicTaskManager
{
	public static final long COMBAT_TIME = 15000;

	private static AttackStanceTaskManager _instance;

	public static AttackStanceTaskManager getInstance()
	{
		if (_instance == null)
		{
			_instance = new AttackStanceTaskManager();
		}

		return _instance;
	}

	private final Map<L2Character, Long> _attackStanceTasks = new ConcurrentHashMap<>();

	private AttackStanceTaskManager()
	{
		super(1000);
	}

	public void addAttackStanceTask(L2Character actor)
	{
		synchronized (_attackStanceTasks)
		{
			if (actor instanceof L2Summon)
			{
				actor = ((L2Summon) actor).getOwner();
			}

			if (actor instanceof L2PcInstance)
			{
				for (L2CubicInstance cubic : ((L2PcInstance) actor).getCubics().values())
					if (cubic.getId() != L2CubicInstance.LIFE_CUBIC)
					{
						cubic.doAction();
					}
			}

			_attackStanceTasks.put(actor, System.currentTimeMillis() + COMBAT_TIME);
		}
	}

	public boolean hasAttackStanceTask(L2Character actor)
	{
		if (actor instanceof L2Summon)
		{
			actor = ((L2Summon) actor).getOwner();
		}
		return _attackStanceTasks.containsKey(actor);
	}

	public void removeAttackStanceTask(L2Character actor)
	{
		synchronized (_attackStanceTasks)
		{
			if (actor instanceof L2Summon)
			{
				actor = ((L2Summon) actor).getOwner();
			}

			_attackStanceTasks.remove(actor);
		}
	}

	@Override
	public void run()
	{
		synchronized (_attackStanceTasks)
		{
			for (Map.Entry<L2Character, Long> entry : _attackStanceTasks.entrySet())
				if (System.currentTimeMillis() > entry.getValue())
				{
					final L2Character actor = entry.getKey();

					actor.broadcastPacket(new AutoAttackStop(actor.getObjectId()));

					if (actor instanceof L2PcInstance)
					{
						final L2Summon pet = ((L2PcInstance) actor).getPet();
						if (pet != null)
						{
							pet.broadcastPacket(new AutoAttackStop(pet.getObjectId()));
						}
					}

					actor.getAI().setAutoAttacking(false);

					_attackStanceTasks.remove(actor);
				}
		}
	}
}
