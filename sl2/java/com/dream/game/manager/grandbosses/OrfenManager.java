package com.dream.game.manager.grandbosses;

import com.dream.annotations.L2Properties;
import com.dream.game.datatables.sql.NpcTable;
import com.dream.game.model.L2Boss;
import com.dream.game.model.L2Spawn;
import com.dream.game.model.entity.GrandBossState;
import com.dream.game.model.entity.GrandBossState.StateEnum;
import com.dream.game.model.quest.pack.ai.Orfen;
import com.dream.game.network.ThreadPoolManager;
import com.dream.game.templates.chars.L2NpcTemplate;
import com.dream.tools.random.Rnd;

public class OrfenManager extends BossLair
{

	private static OrfenManager _instance;

	public static long MIN_RESPAWN;
	public static long MAX_RESPAWN;

	public static OrfenManager getInstance()
	{
		if (_instance == null)
		{
			_instance = new OrfenManager();
		}
		return _instance;

	}

	private OrfenManager()
	{
		super();
		_state = new GrandBossState(29014);
		_state.load();
		try
		{
			L2Properties p = new L2Properties("./config/main/bosses.properties");
			MIN_RESPAWN = Integer.parseInt(p.getProperty("OrfenMinRespawn", "1440"));
			MAX_RESPAWN = Integer.parseInt(p.getProperty("OrfenMaxRespawn", "2880"));
		}
		catch (Exception e)
		{
			_log.error("OrfenManager: Error while reading config", e);
		}
	}

	public void doSpawn()
	{
		L2Boss orfen = (L2Boss) _bossSpawn.doSpawn();
		orfen._lair = this;
		_state.setState(StateEnum.ALIVE);
		_state.update();
	}

	@Override
	public void init()
	{
		L2NpcTemplate template = NpcTable.getInstance().getTemplate(29014);
		_bossSpawn = new L2Spawn(template);
		_bossSpawn.setLocx(55024);
		_bossSpawn.setLocy(17368);
		_bossSpawn.setLocz(-5412);
		new Orfen();
		switch (_state.getState())
		{
			case DEAD:
				_state.setRespawnDate(Rnd.get(MIN_RESPAWN, MAX_RESPAWN) * 60000);
				_state.setState(StateEnum.INTERVAL);
			case SLEEP:
			case INTERVAL:
				ThreadPoolManager.getInstance().scheduleGeneral(new Runnable()
				{
					@Override
					public void run()
					{
						doSpawn();
					}
				}, _state.getInterval());
				break;
			case UNKNOWN:
				_state.setState(StateEnum.ALIVE);
			case ALIVE:
			case NOTSPAWN:
				doSpawn();
				break;
		}
		_log.info("Orfen: State is " + _state.getState() + ".");
	}

	@Override
	public void setRespawn()
	{
	}

	@Override
	public void setUnspawn()
	{
		_state.setState(StateEnum.INTERVAL);
		long interval = Rnd.get(MIN_RESPAWN, MAX_RESPAWN) * 60000;
		_state.setRespawnDate(interval);
		_state.update();
		_log.info("OrfenManager: State of Orfen is " + _state.getState());
		ThreadPoolManager.getInstance().scheduleGeneral(new Runnable()
		{
			@Override
			public void run()
			{
				doSpawn();
			}
		}, _state.getInterval());
	}

}
