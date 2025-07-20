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
package com.dream.game.manager;

import org.apache.log4j.Logger;

import com.dream.Message;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.model.entity.Duel;
import com.dream.game.network.serverpackets.L2GameServerPacket;

import javolution.util.FastList;

public class DuelManager
{
	private final static Logger _log = Logger.getLogger(DuelManager.class.getName());

	private static DuelManager _instance;

	public static final DuelManager getInstance()
	{
		if (_instance == null)
		{
			_instance = new DuelManager();
		}
		return _instance;
	}

	private final FastList<Duel> _duels;
	private int _currentDuelId = 0x90;

	private DuelManager()
	{
		_log.info("Initializing DuelManager");
		_duels = new FastList<>();
	}

	public void addDuel(L2PcInstance playerA, L2PcInstance playerB, int partyDuel)
	{
		if (playerA == null || playerB == null)
			return;

		if (partyDuel == 1)
		{
			boolean playerInPvP = false;
			for (L2PcInstance temp : playerA.getParty().getPartyMembers())
				if (temp.getPvpFlag() != 0)
				{
					playerInPvP = true;
					break;
				}
			if (!playerInPvP)
			{
				for (L2PcInstance temp : playerB.getParty().getPartyMembers())
					if (temp.getPvpFlag() != 0)
					{
						playerInPvP = true;
						break;
					}
			}
			if (playerInPvP)
			{
				for (L2PcInstance temp : playerA.getParty().getPartyMembers())
				{
					temp.sendMessage(Message.getMessage(temp, Message.MessageId.MSG_CURRENT_IN_COMBAT));
				}
				for (L2PcInstance temp : playerB.getParty().getPartyMembers())
				{
					temp.sendMessage(Message.getMessage(temp, Message.MessageId.MSG_CURRENT_IN_COMBAT));
				}
				return;
			}
		}
		else if (playerA.getPvpFlag() != 0 || playerB.getPvpFlag() != 0)
		{
			playerA.sendMessage(Message.getMessage(playerA, Message.MessageId.MSG_CURRENT_IN_COMBAT));
			playerB.sendMessage(Message.getMessage(playerB, Message.MessageId.MSG_CURRENT_IN_COMBAT));
			return;
		}
		Duel duel = new Duel(playerA, playerB, partyDuel, getNextDuelId());
		_duels.add(duel);
	}

	public void broadcastToOppositTeam(L2PcInstance player, L2GameServerPacket packet)
	{
		if (player == null || !player.isInDuel())
			return;
		Duel duel = getDuel(player.getDuelId());
		if (duel == null)
			return;

		if (duel.getPlayerA() == null || duel.getPlayerB() == null)
			return;

		if (duel.getPlayerA() == player)
		{
			duel.broadcastToTeam2(packet);
		}
		else if (duel.getPlayerB() == player)
		{
			duel.broadcastToTeam1(packet);
		}
		else if (duel.isPartyDuel())
			if (duel.getPlayerA().getParty() != null && duel.getPlayerA().getParty().getPartyMembers().contains(player))
			{
				duel.broadcastToTeam2(packet);
			}
			else if (duel.getPlayerB().getParty() != null && duel.getPlayerB().getParty().getPartyMembers().contains(player))
			{
				duel.broadcastToTeam1(packet);
			}
	}

	public void doSurrender(L2PcInstance player)
	{
		if (player == null || !player.isInDuel())
			return;
		Duel duel = getDuel(player.getDuelId());
		duel.doSurrender(player);
	}

	public Duel getDuel(int duelId)
	{
		for (FastList.Node<Duel> e = _duels.head(), end = _duels.tail(); (e = e.getNext()) != end;)
			if (e.getValue().getId() == duelId)
				return e.getValue();
		return null;
	}

	private int getNextDuelId()
	{
		if (++_currentDuelId >= 2147483640)
		{
			_currentDuelId = 1;
		}
		return _currentDuelId;
	}

	public void onPlayerDefeat(L2PcInstance player)
	{
		if (player == null || !player.isInDuel())
			return;
		Duel duel = getDuel(player.getDuelId());
		if (duel != null)
		{
			duel.onPlayerDefeat(player);
		}
	}

	public void onRemoveFromParty(L2PcInstance player)
	{
		if (player == null || !player.isInDuel())
			return;
		Duel duel = getDuel(player.getDuelId());
		if (duel != null)
		{
			duel.onRemoveFromParty(player);
		}
	}

	public void removeDuel(Duel duel)
	{
		_duels.remove(duel);
	}
}