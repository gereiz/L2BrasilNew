package com.dream.game.taskmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.dream.L2DatabaseFactory;
import com.dream.game.network.ThreadPoolManager;
import com.dream.game.util.Broadcast;

public class AutoAnnounceTaskManager
{
	private class AutoAnnouncement implements Runnable
	{
		private final int _id;
		private final long _delay;
		private int _repeat = -1;
		private final String[] _memo;
		private boolean _stopped = false;
		private Future<?> _task;

		public AutoAnnouncement(int id, long delay, int repeat, String[] memo)
		{
			_id = id;
			_delay = delay;
			_repeat = repeat;
			_memo = memo;
			if (!_announces.contains(this))
			{
				_announces.add(this);
			}
		}

		@Override
		public void run()
		{
			for (String text : _memo)
			{
				announce(text);
			}

			if (!_stopped && _repeat > 0)
			{
				_task = ThreadPoolManager.getInstance().scheduleGeneral(new AutoAnnouncement(_id, _delay, _repeat--, _memo), _delay);
			}
			else
			{
				_task = null;
			}
		}

		public void stopAnnounce()
		{
			_stopped = true;
			if (_task != null)
			{
				_task.cancel(true);
			}
		}
	}

	protected static final Logger _log = Logger.getLogger(AutoAnnounceTaskManager.class);

	private static AutoAnnounceTaskManager _instance;

	public static AutoAnnounceTaskManager getInstance()
	{
		if (_instance == null)
		{
			_instance = new AutoAnnounceTaskManager();
		}

		return _instance;
	}

	protected List<AutoAnnouncement> _announces = new ArrayList<>();

	public AutoAnnounceTaskManager()
	{
		restore();
	}

	public void announce(String text)
	{
		Broadcast.announceToOnlinePlayers(text);
	}

	public void restore()
	{
		if (!_announces.isEmpty())
		{
			for (AutoAnnouncement a : _announces)
			{
				a.stopAnnounce();
			}
		}
		_announces.clear();
		java.sql.Connection conn = null;
		int count = 0;
		try
		{
			conn = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT id, initial, delay, cycle, memo FROM auto_announcements");
			ResultSet data = statement.executeQuery();
			while (data.next())
			{
				int id = data.getInt("id");
				long initial = data.getLong("initial");
				long delay = data.getLong("delay");
				int repeat = data.getInt("cycle");
				String memo = data.getString("memo");
				String[] text = memo.split("/n");
				ThreadPoolManager.getInstance().scheduleGeneral(new AutoAnnouncement(id, delay, repeat, text), initial);
				count++;
			}
		}
		catch (Exception e)
		{
			_log.fatal("AutoAnnoucements: Fail to load announcements data.", e);
		}
		_log.info("AnnounceManager: Loaded " + count + " auto announce");
	}
}