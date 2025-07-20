/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.dream.game.communitybbs.Manager;

import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.network.serverpackets.ShowBoard;

public class AdminBBSManager extends BaseBBSManager
{
	private static class SingletonHolder
	{
		protected static final AdminBBSManager _instance = new AdminBBSManager();
	}

	public static AdminBBSManager getInstance()
	{
		return SingletonHolder._instance;
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (activeChar.isGM())
			return;

		if (command.startsWith("admin_bbs"))
		{
			separateAndSend("<html><body><br><br><center>This Page is only an exemple :)<br><br>command=" + command + "</center></body></html>", activeChar);
		}
		else
		{

			activeChar.sendPacket(new ShowBoard("<html><body><br><br><center>the command: " + command + " is not implemented yet</center><br><br></body></html>", "101"));
			activeChar.sendPacket(new ShowBoard(null, "102"));
			activeChar.sendPacket(new ShowBoard(null, "103"));
		}

	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		if (activeChar.isGM())
			return;
	}
}