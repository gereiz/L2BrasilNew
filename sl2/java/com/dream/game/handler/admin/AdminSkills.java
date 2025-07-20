package com.dream.game.handler.admin;

import com.dream.game.access.gmHandler;
import com.dream.game.datatables.xml.ResetData;
import com.dream.game.datatables.xml.SkillTable;
import com.dream.game.datatables.xml.SkillTreeTable;
import com.dream.game.model.L2Object;
import com.dream.game.model.L2Skill;
import com.dream.game.model.L2SkillLearn;
import com.dream.game.model.actor.instance.L2PcInstance;
import com.dream.game.model.holders.IntIntHolder;
import com.dream.game.model.holders.ResetHolder;
import com.dream.game.network.SystemChatChannelId;
import com.dream.game.network.SystemMessageId;
import com.dream.game.network.serverpackets.ActionFailed;
import com.dream.game.network.serverpackets.MagicSkillUse;
import com.dream.game.network.serverpackets.NpcHtmlMessage;
import com.dream.game.network.serverpackets.PledgeSkillList;
import com.dream.game.network.serverpackets.SystemMessage;

public class AdminSkills extends gmHandler
{
	private static final String[] commands =
	{
		"show_skills",
		"remove_skills",
		"skill_list",
		"skill_index",
		"add_skill",
		"remove_skill",
		"get_skills",
		"reset_skills",
		"give_all_skills",
		"remove_all_skills",
		"add_clan_skill",
		"cast_skill",
		"clear_skill_reuse"
	};
	
	private static L2Skill[] adminSkills;
	
	private static void adminAddClanSkill(L2PcInstance activeChar, int id, int level)
	{
		L2Object target = activeChar.getTarget();
		L2PcInstance player = null;
		if (target instanceof L2PcInstance)
		{
			player = (L2PcInstance) target;
		}
		else
		{
			showMainPage(activeChar);
			activeChar.sendPacket(SystemMessageId.INCORRECT_TARGET);
			return;
		}
		if (!player.isClanLeader())
		{
			activeChar.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.S1_IS_NOT_A_CLAN_LEADER).addString(player.getName()));
			showMainPage(activeChar);
			return;
		}
		if (id < 370 || id > 391 || level < 1 || level > 3)
		{
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Usage: //add_clan_skill <id> <level>");
			showMainPage(activeChar);
			return;
		}
		
		L2Skill skill = SkillTable.getInstance().getInfo(id, level);
		if (skill != null)
		{
			String skillname = skill.getName();
			player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.CLAN_SKILL_S1_ADDED).addSkillName(skill));
			player.getClan().broadcastToOnlineMembers(SystemMessage.getSystemMessage(SystemMessageId.CLAN_SKILL_S1_ADDED).addSkillName(skill));
			player.getClan().addNewSkill(skill);
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Added skill " + skillname + " clan " + player.getClan().getName());
			
			activeChar.getClan().broadcastToOnlineMembers(new PledgeSkillList(activeChar.getClan()));
			for (L2PcInstance member : activeChar.getClan().getOnlineMembers(0))
			{
				member.sendSkillList();
			}
			
			showMainPage(activeChar);
			return;
		}
		
		activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Error. Skill does not exist");
	}
	
	private static void adminAddSkill(L2PcInstance activeChar, int id, int lvl)
	{
		L2Object target = activeChar.getTarget();
		if (target == null)
		{
			target = activeChar;
		}
		
		if (!(target instanceof L2PcInstance))
		{
			showMainPage(activeChar);
			activeChar.sendPacket(SystemMessageId.INCORRECT_TARGET);
			return;
		}
		
		L2PcInstance player = (L2PcInstance) target;
		L2Skill skill = SkillTable.getInstance().getInfo(id, lvl);
		if (skill != null)
		{
			String name = skill.getName();
			player.addSkill(skill, true);
			player.sendSkillList();
			player.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "You have studied the Skil " + name);
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "You have added the Skil " + name + " the player " + player.getName());
		}
		else
		{
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Error. What does not exist");
		}
		
		showMainPage(activeChar);
	}
	
	private static void adminGetSkills(L2PcInstance activeChar)
	{
		L2Object target = activeChar.getTarget();
		if (target == null)
		{
			target = activeChar;
		}
		
		if (!(target instanceof L2PcInstance))
		{
			activeChar.sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
			return;
		}
		
		L2PcInstance player = (L2PcInstance) target;
		if (player == activeChar)
		{
			player.sendPacket(SystemMessageId.CANNOT_USE_ON_YOURSELF);
		}
		else
		{
			L2Skill[] skills = player.getAllSkills();
			adminSkills = activeChar.getAllSkills();
			for (L2Skill element : adminSkills)
			{
				activeChar.removeSkill(element);
			}
			for (L2Skill element : skills)
			{
				activeChar.addSkill(element, true);
			}
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "You got the buffs player " + player.getName());
			activeChar.sendSkillList();
		}
		showMainPage(activeChar);
	}
	
	private static void adminGiveAllSkills(L2PcInstance activeChar)
	{
		L2Object target = activeChar.getTarget();
		L2PcInstance player = null;
		if (target instanceof L2PcInstance)
		{
			player = (L2PcInstance) target;
		}
		else
		{
			activeChar.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.INCORRECT_TARGET));
			return;
		}
		boolean countUnlearnable = true;
		int unLearnable = 0;
		int skillCounter = 0;
		L2SkillLearn[] skills = SkillTreeTable.getInstance().getAvailableSkills(player, player.getClassId());
		while (skills.length > unLearnable)
		{
			for (L2SkillLearn s : skills)
			{
				L2Skill sk = SkillTable.getInstance().getInfo(s.getId(), s.getLevel());
				if (sk == null || !sk.getCanLearn(player.getClassId()))
				{
					if (countUnlearnable)
					{
						unLearnable++;
					}
					continue;
				}
				if (player.getSkillLevel(sk.getId()) == -1)
				{
					skillCounter++;
				}
				player.addSkill(sk, true);
			}
			countUnlearnable = false;
			skills = SkillTreeTable.getInstance().getAvailableSkills(player, player.getClassId());
		}
		
		if (skillCounter > 0)
		{
			player.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "A GM gave you " + skillCounter + " skills.");
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "You gave " + skillCounter + " skills to " + player.getName());
			player.sendSkillList();
		}
	}
	
	private static void adminRemoveSkill(L2PcInstance activeChar, int idval)
	{
		L2Object target = activeChar.getTarget();
		L2PcInstance player = null;
		if (target instanceof L2PcInstance)
		{
			player = (L2PcInstance) target;
		}
		else
		{
			activeChar.sendPacket(SystemMessageId.INCORRECT_TARGET);
			return;
		}
		L2Skill skill = SkillTable.getInstance().getInfo(idval, player.getSkillLevel(idval));
		if (skill != null)
		{
			String skillname = skill.getName();
			player.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "The administrator has deleted your skill " + skillname + ".");
			player.removeSkill(skill);
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "You removed the Skil " + skillname + " the player " + player.getName() + ".");
			player.sendSkillList();
		}
		else
		{
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Error: there is no go.");
		}
		removeSkillsPage(activeChar, 0);
	}
	
	private static void adminResetSkills(L2PcInstance activeChar)
	{
		if (adminSkills == null)
		{
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "You can not do a reset now");
		}
		else
		{
			L2Skill[] skills = activeChar.getAllSkills();
			for (L2Skill skill : skills)
			{
				activeChar.removeSkill(skill);
			}
			for (L2Skill skill : adminSkills)
			{
				activeChar.addSkill(skill, true);
			}
			activeChar.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "You returned their buffs");
			adminSkills = null;
			activeChar.sendSkillList();
		}
		showMainPage(activeChar);
	}
	
	private static void removeSkillsPage(L2PcInstance activeChar, int page)
	{
		L2Object target = activeChar.getTarget();
		if (target == null)
		{
			target = activeChar;
		}
		
		if (!(target instanceof L2PcInstance))
		{
			activeChar.sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
			return;
		}
		
		L2PcInstance player = (L2PcInstance) target;
		L2Skill[] skills = player.getAllSkills();
		
		int MaxSkillsPerPage = 10;
		int MaxPages = skills.length / MaxSkillsPerPage;
		if (skills.length > MaxSkillsPerPage * MaxPages)
		{
			MaxPages++;
		}
		
		if (page > MaxPages)
		{
			page = MaxPages;
		}
		
		int SkillsStart = MaxSkillsPerPage * page;
		int SkillsEnd = skills.length;
		if (SkillsEnd - SkillsStart > MaxSkillsPerPage)
		{
			SkillsEnd = SkillsStart + MaxSkillsPerPage;
		}
		
		NpcHtmlMessage adminReply = new NpcHtmlMessage(5);
		StringBuilder replyMSG = new StringBuilder("<html><body>");
		replyMSG.append("<table width=260><tr>");
		replyMSG.append("<td width=40><button value=\"Main\" action=\"bypass -h admin_admin\" width=40 height=15 back=\"sek.cbui94\" fore=\"sek.cbui94\"></td>");
		replyMSG.append("<td width=180><center>Character Selection Menu</center></td>");
		replyMSG.append("<td width=40><button value=\"Back\" action=\"bypass -h admin_show_skills\" width=40 height=15 back=\"sek.cbui94\" fore=\"sek.cbui94\"></td>");
		replyMSG.append("</tr></table>");
		replyMSG.append("<br><br>");
		replyMSG.append("<center>Editing <font color=\"LEVEL\">" + player.getName() + "</font></center>");
		replyMSG.append("<br><table width=270><tr><td>Lv: " + player.getLevel() + " " + player.getTemplate().getClassName() + "</td></tr></table>");
		replyMSG.append("<br><table width=270><tr><td>Note: Dont forget that modifying players skills can</td></tr>");
		replyMSG.append("<tr><td>ruin the game...</td></tr></table>");
		replyMSG.append("<br><center>Click on the skill you wish to remove:</center>");
		replyMSG.append("<br>");
		String pages = "<center><table width=270><tr>";
		for (int x = 0; x < MaxPages; x++)
		{
			int pagenr = x + 1;
			pages += "<td><a action=\"bypass -h admin_remove_skills " + x + "\">Page " + pagenr + "</a></td>";
		}
		pages += "</tr></table></center>";
		replyMSG.append(pages);
		replyMSG.append("<br><table width=270>");
		replyMSG.append("<tr><td width=80>Name:</td><td width=60>Level:</td><td width=40>Id:</td></tr>");
		for (int i = SkillsStart; i < SkillsEnd; i++)
		{
			replyMSG.append("<tr><td width=80><a action=\"bypass -h admin_remove_skill " + skills[i].getId() + "\">" + skills[i].getName() + "</a></td><td width=60>" + skills[i].getLevel() + "</td><td width=40>" + skills[i].getId() + "</td></tr>");
		}
		replyMSG.append("</table>");
		replyMSG.append("<br><center><table>");
		replyMSG.append("Remove skill by ID :");
		replyMSG.append("<tr><td>Id: </td>");
		replyMSG.append("<td><edit var=\"id_to_remove\" width=110></td></tr>");
		replyMSG.append("</table></center>");
		replyMSG.append("<center><button value=\"Remove skill\" action=\"bypass -h admin_remove_skill $id_to_remove\" width=110 height=15 back=\"sek.cbui94\" fore=\"sek.cbui94\"></center>");
		replyMSG.append("<br><center><button value=\"Back\" action=\"bypass -h admin_current_player\" width=40 height=15 back=\"sek.cbui94\" fore=\"sek.cbui94\"></center>");
		replyMSG.append("</body></html>");
		adminReply.setHtml(replyMSG.toString());
		activeChar.sendPacket(adminReply);
	}
	
	private static void showMainPage(L2PcInstance activeChar)
	{
		L2Object target = activeChar.getTarget();
		if (target == null)
		{
			target = activeChar;
		}
		
		if (!(target instanceof L2PcInstance))
		{
			activeChar.sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
			return;
		}
		
		L2PcInstance player = (L2PcInstance) target;
		
		NpcHtmlMessage adminReply = new NpcHtmlMessage(5);
		adminReply.setFile("data/html/admin/menus/submenus/charskills_menu.htm");
		adminReply.replace("%name%", player.getName());
		adminReply.replace("%level%", String.valueOf(player.getLevel()));
		adminReply.replace("%class%", player.getTemplate().getClassName());
		activeChar.sendPacket(adminReply);
	}
	
	public void castSkill(L2PcInstance activeChar, String val)
	{
		int skillid = Integer.parseInt(val);
		L2Skill skill = SkillTable.getInstance().getInfo(skillid, 1);
		if (skill != null)
		{
			if (skill.getTargetType() == L2Skill.SkillTargetType.TARGET_SELF)
			{
				activeChar.setTarget(activeChar);
				activeChar.broadcastPacket(new MagicSkillUse(activeChar, activeChar, skillid, 1, skill.getHitTime(), skill.getReuseDelay(), skill.isPositive()));
			}
		}
		else
		{
			activeChar.broadcastPacket(ActionFailed.STATIC_PACKET);
		}
	}
	
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
		
		final String command = params[0];
		
		if (command.equals("show_skills"))
		{
			showMainPage(admin);
		}
		else if (command.equals("skill_list"))
		{
			AdminMethods.showSubMenuPage(admin, "skills_menu.htm");
		}
		else if (command.equals("get_skills"))
		{
			adminGetSkills(admin);
		}
		else if (command.equals("reset_skills"))
		{
			adminResetSkills(admin);
		}
		else if (command.equals("give_all_skills"))
		{
			adminGiveAllSkills(admin);
		}
		else if (command.equals("cast_skill"))
		{
			if (params.length > 1)
			{
				castSkill(admin, params[1]);
			}
			else
			{
				admin.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Invalid argument");
			}
		}
		else if (command.startsWith("remove_skills"))
		{
			try
			{
				removeSkillsPage(admin, Integer.parseInt(params[1]));
			}
			catch (Exception e)
			{
			}
		}
		else if (command.startsWith("skill_index"))
		{
			try
			{
				AdminMethods.showHelpPage(admin, "skills/" + params[1] + ".htm");
			}
			catch (Exception e)
			{
			}
		}
		else if (command.startsWith("add_skill"))
		{
			try
			{
				int id = Integer.parseInt(params[1]);
				int level = 1;
				
				if (params.length > 2)
				{
					level = Integer.parseInt(params[2]);
				}
				adminAddSkill(admin, id, level);
			}
			catch (Exception e)
			{
				admin.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Usage: //add_skill <id> <level>");
			}
		}
		else if (command.startsWith("remove_skill"))
		{
			try
			{
				adminRemoveSkill(admin, Integer.parseInt(params[1]));
			}
			catch (Exception e)
			{
				admin.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Usage: //remove_skill <id>");
			}
		}
		else if (command.equals("remove_all_skills"))
		{
			L2Object tmp = admin.getTarget();
			if (tmp == null)
			{
				tmp = admin;
			}
			
			if (tmp instanceof L2PcInstance)
			{
				int count = 0;
				L2PcInstance player = (L2PcInstance) tmp;
				for (L2Skill skill : player.getAllSkills())
				{
					boolean isRewardSkill = false;
					
					if (skill == null)
					{
						continue;
					}
					
					for (ResetHolder holder : ResetData.getInstance().getResets())
					{
						for (IntIntHolder skillReward : holder.getRewardSkills())
						{
							if (skill.getId() == skillReward.getId())
							{
								isRewardSkill = true;
								break;
							}
						}
					}
					
					if (!isRewardSkill)
					{
						player.removeSkill(skill, true, true);
					}
					
					count++;
				}
				
				if (count > 0)
				{
					admin.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", count + " skill (s) removed the player " + player.getName());
					player.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "The administrator deleted all your skills");
					player.sendSkillList();
				}
			}
		}
		else if (command.equals("add_clan_skill"))
		{
			try
			{
				adminAddClanSkill(admin, Integer.parseInt(params[1]), Integer.parseInt(params[2]));
			}
			catch (Exception e)
			{
				admin.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Usage: //add_clan_skill <id> <level>");
			}
		}
		else if (command.equals("clear_skill_reuse"))
		{
			L2Object object = admin.getTarget();
			if (object == null)
			{
				admin.sendPacket(SystemMessageId.INCORRECT_TARGET);
				return;
			}
			
			if (object instanceof L2PcInstance)
			{
				((L2PcInstance) object).resetSkillTime(true);
				((L2PcInstance) object).sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "Your skill is once again ready for use.");
				admin.sendChatMessage(0, SystemChatChannelId.Chat_None, "SYS", "The player's skills " + object.getName() + " ready to use.");
			}
			else
			{
				admin.sendPacket(SystemMessageId.INCORRECT_TARGET);
				return;
			}
		}
	}
}