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
package com.dream.game.manager.grandbosses;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import com.dream.Config;
import com.dream.Message;
import com.dream.annotations.L2Properties;
import com.dream.game.ai.CtrlIntention;
import com.dream.game.datatables.sql.NpcTable;
import com.dream.game.datatables.xml.MapRegionTable.TeleportWhereType;
import com.dream.game.model.L2Boss;
import com.dream.game.model.L2Spawn;
import com.dream.game.model.actor.L2Attackable;
import com.dream.game.model.actor.L2Character;
import com.dream.game.model.actor.L2Npc;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.model.actor.position.L2CharPosition;
import com.dream.game.model.entity.GrandBossState;
import com.dream.game.model.entity.GrandBossState.StateEnum;
import com.dream.game.model.quest.pack.ai.Sailren;
import com.dream.game.network.ThreadPoolManager;
import com.dream.game.network.serverpackets.SocialAction;
import com.dream.game.templates.chars.L2NpcTemplate;
import com.dream.game.util.Util;
import com.dream.tools.random.Rnd;

public class SailrenManager extends BossLair
{
	// limit of time coming.
	private class ActivityTimeEnd implements Runnable
	{
		public ActivityTimeEnd()
		{
		}

		@Override
		public void run()
		{
			setUnspawn();
			_activityTimeEndTask = null;

		}
	}

	// spawn teleport cube.
	public class CubeSpawn implements Runnable
	{
		@Override
		public void run()
		{
			spawnCube();
		}
	}

	// interval end.
	public class IntervalEnd implements Runnable
	{
		@Override
		public void run()
		{
			_state.setState(GrandBossState.StateEnum.NOTSPAWN);
			_state.update();
		}
	}

	// spawn monster.
	private class SailrenSpawn implements Runnable
	{
		private final int _npcId;
		private final L2CharPosition _pos = new L2CharPosition(27628, -6109, -1982, 44732);

		public SailrenSpawn(int npcId)
		{
			_npcId = npcId;
		}

		@Override
		public void run()
		{
			if (_activityTimeEndTask == null)
			{
				_activityTimeEndTask = ThreadPoolManager.getInstance().scheduleGeneral(new ActivityTimeEnd(), ACTIVITY_TIME * 60000);
			}

			switch (_npcId)
			{
				case 22218: // Velociraptor
					_activeMob = _velociraptorSpawn.doSpawn();
					_activeMob.getAI().setIntention(CtrlIntention.MOVE_TO, _pos);
					if (_socialTask != null)
					{
						_socialTask.cancel(true);
						_socialTask = null;
					}
					_socialTask = ThreadPoolManager.getInstance().scheduleGeneral(new Social(_activeMob, 2), 6000);
					break;
				case 22199: // Pterosaur
					_activeMob = _pterosaurSpawn.doSpawn();
					_activeMob.getAI().setIntention(CtrlIntention.MOVE_TO, _pos);
					if (_socialTask != null)
					{
						_socialTask.cancel(true);
						_socialTask = null;
					}
					_socialTask = ThreadPoolManager.getInstance().scheduleGeneral(new Social(_activeMob, 2), 6000);
					break;
				case 22217: // Tyrannosaurus
					_activeMob = _tyrannoSpawn.doSpawn();
					_activeMob.getAI().setIntention(CtrlIntention.MOVE_TO, _pos);
					if (_socialTask != null)
					{
						_socialTask.cancel(true);
						_socialTask = null;
					}
					_socialTask = ThreadPoolManager.getInstance().scheduleGeneral(new Social(_activeMob, 2), 6000);
					break;
				case 29065: // Sailren
					_sailren = (L2Boss) _sailrenSapwn.doSpawn();
					_activeMob = _sailren;
					_state.setState(GrandBossState.StateEnum.ALIVE);
					_state.update();

					_sailren.getAI().setIntention(CtrlIntention.MOVE_TO, _pos);
					if (_socialTask != null)
					{
						_socialTask.cancel(true);
						_socialTask = null;
					}
					_socialTask = ThreadPoolManager.getInstance().scheduleGeneral(new Social(_sailren, 2), 6000);
					break;
			}
			if (_activeMob != null)
			{
				((L2Attackable) _activeMob).setCanReturnToSpawnPoint(false);
			}
			_sailrenSpawnTask = null;
		}
	}

	// social.
	private class Social implements Runnable
	{
		private final int _action;
		private final L2Npc _npc;

		public Social(L2Npc npc, int actionId)
		{
			_npc = npc;
			_action = actionId;
		}

		@Override
		public void run()
		{
			_npc.broadcastPacket(new SocialAction(_npc, _action));
		}
	}

	private static SailrenManager _instance;

	public static long MIN_RESPAWN;

	public static long MAX_RESPAWN;
	public static boolean ENABLE_SINGLE;

	public static long INTERVAL_OF_MONSTER;
	public static long ACTIVITY_TIME;

	public static SailrenManager getInstance()
	{
		if (_instance == null)
		{
			_instance = new SailrenManager();
		}
		return _instance;
	}

	// teleport cube location.
	protected L2Spawn _sailrenCubeSpawn;
	protected L2Npc _sailrenCube;
	// spawn data of monsters
	protected L2Spawn _velociraptorSpawn; // Velociraptor

	protected L2Spawn _pterosaurSpawn; // Pterosaur
	protected L2Spawn _tyrannoSpawn; // Tyrannosaurus
	protected L2Spawn _sailrenSapwn; // Sailren
	// Instance of monsters
	public L2Npc _activeMob; // Velociraptor
	protected L2Boss _sailren; // Sailren
	// Tasks
	protected ScheduledFuture<?> _cubeSpawnTask = null;
	protected ScheduledFuture<?> _sailrenSpawnTask = null;

	protected ScheduledFuture<?> _intervalEndTask = null;

	protected ScheduledFuture<?> _activityTimeEndTask = null;

	protected ScheduledFuture<?> _onPartyAnnihilatedTask = null;

	protected ScheduledFuture<?> _socialTask = null;

	private boolean ENABLED;

	// State of sailren's lair.
	protected boolean _isAlreadyEnteredOtherParty = false;

	private SailrenManager()
	{

		super();
		try
		{
			L2Properties p = new L2Properties("./config/main/bosses.properties");
			ENABLED = Boolean.parseBoolean(p.getProperty("SailrenEnabled", "true"));
			if (!ENABLED)
				return;

			MIN_RESPAWN = Integer.parseInt(p.getProperty("SailrenMinRespawn", "1440"));
			MAX_RESPAWN = Integer.parseInt(p.getProperty("SailrenMaxRespawn", "2880"));
			INTERVAL_OF_MONSTER = Integer.parseInt(p.getProperty("SailrenIntervalOfMonsters", "5"));
			ENABLE_SINGLE = Boolean.parseBoolean(p.getProperty("SailrenEnableSinglePlayer", "false"));
			ACTIVITY_TIME = Integer.parseInt(p.getProperty("SailrenActivityTime", "50"));
		}
		catch (Exception e)
		{
			_log.error("SailrenManager: Error while reading config", e);
			return;
		}

		_state = new GrandBossState(29065);
		_state.load();
	}

	// whether it is permitted to enter the sailren's lair is confirmed.
	public int canIntoSailrenLair(L2PcInstance pc)
	{
		if (_state.getState().equals(GrandBossState.StateEnum.DEAD))
			return 1;
		else if (_isAlreadyEnteredOtherParty)
			return 2;
		else if (_state.getState().equals(GrandBossState.StateEnum.INTERVAL))
			return 3;
		else if (!ENABLE_SINGLE && pc.getParty() == null)
			return 4;
		else if (_state.getState().equals(GrandBossState.StateEnum.NOTSPAWN))
			return 0;

		return 0;

	}

	// teleporting player to sailren's lair.
	public void entryToSailrenLair(L2PcInstance pc)
	{
		int driftx;
		int drifty;

		if (canIntoSailrenLair(pc) != 0)
		{
			pc.sendMessage(Message.getMessage(pc, Message.MessageId.MSG_BAD_CONDITIONS));
			_isAlreadyEnteredOtherParty = false;
			return;
		}

		if (pc.getParty() == null)
		{
			driftx = Rnd.get(-80, 80);
			drifty = Rnd.get(-80, 80);
			pc.teleToLocation(27734 + driftx, -6938 + drifty, -1982);
		}
		else
		{
			List<L2PcInstance> members = new ArrayList<>();
			for (L2PcInstance mem : pc.getParty().getPartyMembers())
				if (!mem.isDead() && Util.checkIfInRange(700, pc, mem, true))
				{
					members.add(mem);
				}
			for (L2PcInstance mem : members)
			{
				driftx = Rnd.get(-80, 80);
				drifty = Rnd.get(-80, 80);
				mem.teleToLocation(27734 + driftx, -6938 + drifty, -1982);
			}
		}
		_isAlreadyEnteredOtherParty = true;
		setSailrenSpawnTask(Sailren.VELOCIRAPTOR);
	}

	@Override
	public void init()
	{
		// init state.
		if (!ENABLED)
			return;

		_isAlreadyEnteredOtherParty = false;
		try
		{
			L2NpcTemplate template1;

			// Velociraptor
			template1 = NpcTable.getInstance().getTemplate(22218); // Velociraptor
			_velociraptorSpawn = new L2Spawn(template1);
			_velociraptorSpawn.setLocx(27852);
			_velociraptorSpawn.setLocy(-5536);
			_velociraptorSpawn.setLocz(-1983);
			_velociraptorSpawn.setHeading(44732);
			_velociraptorSpawn.setAmount(1);
			// Pterosaur
			template1 = NpcTable.getInstance().getTemplate(22199); // Pterosaur
			_pterosaurSpawn = new L2Spawn(template1);
			_pterosaurSpawn.setLocx(27852);
			_pterosaurSpawn.setLocy(-5536);
			_pterosaurSpawn.setLocz(-1983);
			_pterosaurSpawn.setHeading(44732);
			_pterosaurSpawn.setAmount(1);

			// Tyrannosaurus
			template1 = NpcTable.getInstance().getTemplate(22217); // Tyrannosaurus
			_tyrannoSpawn = new L2Spawn(template1);
			_tyrannoSpawn.setLocx(27852);
			_tyrannoSpawn.setLocy(-5536);
			_tyrannoSpawn.setLocz(-1983);
			_tyrannoSpawn.setHeading(44732);
			_tyrannoSpawn.setAmount(1);

			// Sailren
			template1 = NpcTable.getInstance().getTemplate(29065); // Sailren
			_sailrenSapwn = new L2Spawn(template1);
			_sailrenSapwn.setLocx(27810);
			_sailrenSapwn.setLocy(-5655);
			_sailrenSapwn.setLocz(-1983);
			_sailrenSapwn.setHeading(44732);
			_sailrenSapwn.setAmount(1);
			_bossSpawn = _sailrenSapwn;

		}
		catch (Exception e)
		{
			_log.warn(e.getMessage());
		}

		new Sailren();
		_questName = Sailren.QUEST;

		try
		{
			L2NpcTemplate cube = NpcTable.getInstance().getTemplate(32107);
			_sailrenCubeSpawn = new L2Spawn(cube);
			_sailrenCubeSpawn.setLocx(27734);
			_sailrenCubeSpawn.setLocy(-6838);
			_sailrenCubeSpawn.setLocz(-1982);
		}
		catch (Exception e)
		{
			_log.warn(e.getMessage());
		}

		switch (_state.getState())
		{
			case DEAD:
				_state.setRespawnDate(Rnd.get(MIN_RESPAWN, MAX_RESPAWN) * 60000);
				_state.setState(StateEnum.INTERVAL);
			case SLEEP:
			case INTERVAL:
				setIntervalEndTask();
				break;

			case UNKNOWN:
			case ALIVE:
				_state.setState(StateEnum.NOTSPAWN);
		}
		_log.info("Sailren: State is " + _state.getState() + ".");

	}

	@Override
	public void onEnter(L2Character cha)
	{
		L2PcInstance pc = cha.getActingPlayer();
		if (pc != null && !pc.isGM() && Config.EPIC_REQUIRE_QUEST)
		{
			if (pc.getQuestState(_questName) == null)
			{
				if (pc.getParty() != null && pc.getParty().getLeader().getQuestState(_questName) != null)
					return;
			}
			else
				return;
			pc.teleToLocation(TeleportWhereType.Town);
		}
	}

	// task of teleport cube spawn.
	public void setCubeSpawn()
	{
		_activeMob = null;
		_state.setState(StateEnum.DEAD);
		_state.update();
		_cubeSpawnTask = ThreadPoolManager.getInstance().scheduleGeneral(new CubeSpawn(), 10000);
	}

	// task of interval of sailren spawn.
	public void setIntervalEndTask()
	{
		ThreadPoolManager.getInstance().scheduleGeneral(new IntervalEnd(), _state.getInterval());
	}

	@Override
	public void setRespawn()
	{
	}

	// set sailren spawn task.
	public void setSailrenSpawnTask(int npcId)
	{
		_activeMob = null;
		if (_sailrenSpawnTask == null)
		{
			_sailrenSpawnTask = ThreadPoolManager.getInstance().scheduleGeneral(new SailrenSpawn(npcId), INTERVAL_OF_MONSTER * 60000);
		}
	}

	// clean up sailren's lair.
	@Override
	public void setUnspawn()
	{
		if (_state.getState() != StateEnum.DEAD)
		{
			_state.setState(GrandBossState.StateEnum.NOTSPAWN);
		}
		else
		{
			_state.setState(StateEnum.INTERVAL);
			long respawn = Rnd.get(MIN_RESPAWN, MAX_RESPAWN) * 60000;
			_state.setRespawnDate(respawn);
		}
		_state.update();
		setIntervalEndTask();
		if (_sailrenSpawnTask != null)
		{
			_sailrenSpawnTask.cancel(true);
			_sailrenSpawnTask = null;
		}
		if (_sailrenCube != null)
		{
			_sailrenCube.deleteMe();
			_sailrenCube = null;
		}
		if (_activeMob != null)
		{
			_activeMob.deleteMe();
			_activeMob = null;
		}
		clearLair();
		_isAlreadyEnteredOtherParty = false;
		_activityTimeEndTask = null;

	}

	// spawn teleport cube.
	public void spawnCube()
	{
		_sailrenCube = _sailrenCubeSpawn.doSpawn();
	}
}
