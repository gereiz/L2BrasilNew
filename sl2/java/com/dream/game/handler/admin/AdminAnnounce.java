package com.dream.game.handler.admin;

import com.dream.Config;
import com.dream.game.Announcements;
import com.dream.game.access.gmHandler;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.taskmanager.AutoAnnounceTaskManager;

public class AdminAnnounce extends gmHandler
{
	private static final String[] commands =
	{
		"announce_menu",
		"announce_refresh",
		"announce_add",
		"announce_del",
		"announce",
		"announce_critical",
		"announce_reloadfile",
		"announce_reloaddb"
	};

	@Override
	public String[] getCommandList()
	{
		return commands;
	}

	@Override
	public void runCommand(L2PcInstance admin, String... params)
	{
		if (admin == null)
			return;

		String command = params[0];
		Announcements ann = Announcements.getInstance();

		if (command.equals("announce_reloadfile"))
		{
			ann.loadAnnouncements();
		}
		else if (command.equals("announce_reloaddb"))
		{
			AutoAnnounceTaskManager.getInstance().restore();
		}
		else if (command.equals("announce_menu"))
		{
			ann.listAnnouncements(admin);
			return;
		}
		else if (command.equals("announce_refresh"))
		{
			ann.showAnnouncements();
			ann.listAnnouncements(admin);
			return;
		}
		else if (command.equals("announce_add"))
		{
			try
			{
				String text = "";
				if (params.length > 1)
				{
					text = params[1];
					for (int i = 2; i < params.length; i++)
					{
						text += " " + params[i];
					}
				}

				if (text.length() > 0)
				{
					ann.addAnnouncement(text);
					ann.listAnnouncements(admin);
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
			}
			return;
		}
		else if (command.equals("announce_del"))
		{
			try
			{
				if (params.length > 1)
				{
					ann.delAnnouncement(Integer.parseInt(params[1]));
					ann.listAnnouncements(admin);
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
			}
			return;
		}
		else if (command.equals("announce"))
		{
			String text = "";
			if (params.length > 1)
			{
				text = params[1];
				for (int i = 2; i < params.length; i++)
				{
					text += " " + params[i];
				}
			}
			if (text.length() > 0)
			{
				if (!Config.GM_ANNOUNCER_NAME)
				{
				ann.handleAnnounce(text);
				}
				if (Config.GM_ANNOUNCER_NAME)
				{
					ann.handleAnnounce(text + " [" + admin.getName() + "]");
				}
			}
			return;
		}
		else if (command.equals("announce_critical"))
		{
			String text = "";
			if (params.length > 1)
			{
				text = params[1];
				for (int i = 2; i < params.length; i++)
				{
					text += " " + params[i];
				}
			}
			if (text.length() > 0)
			{
				ann.handleCriticalAnnounce(text);
			}
		}
	}
}