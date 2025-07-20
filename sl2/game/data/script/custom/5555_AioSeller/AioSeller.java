package custom.AioSeller;

import net.xline.Config;
import net.xline.gameserver.model.actor.L2Npc;
import net.xline.gameserver.model.actor.instance.L2PcInstance;
import net.xline.gameserver.model.quest.Quest;
import net.xline.gameserver.model.quest.QuestState;

/**
 * @author TiTaM
 */
public class AioSeller extends Quest
{
    //------------------------------------------------	
    // Id do Npc
	private static final int[] NPC = {214};
	//------------------------------------------------	
	//Id da moeda e quantidade para a primeira opçao.
	private static final int MOEDA1 = 57;
	private static final int COUNT1 = 30000000;
	//Dias pra a primeira opçao.
	private static final int DIAS1 = 30;
	//------------------------------------------------
	//Id da moeda e quantidade para a segunda opçao.
	private static final int MOEDA2 = 57;
	private static final int COUNT2 = 60000000;
	//Dias pra a segunda opçao.
	private static final int DIAS2 = 60;
	//------------------------------------------------
	//Id da moeda e quantidade para a terceira opçao.
	private static final int MOEDA3 = 57;
	private static final int COUNT3 = 100000000;
	//Dias pra a terceira opçao.
	private static final int DIAS3 = 90;
    //------------------------------------------------	
	
	public AioSeller(int questid, String name, String descr)
	{
		super(questid, name, descr);
		
		for(int NPC_ID : NPC)
		{
			addStartNpc(NPC_ID);
			addFirstTalkId(NPC_ID);
			addTalkId(NPC_ID);
		}
	}
	
	public String onAdvEvent (String event, L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		String htmltext = "";
		int npcId = npc.getNpcId();

		if(event.equalsIgnoreCase("1.htm"))
		{			
				return "1.htm";
		}
		if(event.equalsIgnoreCase("2.htm"))
		{			
				return "2.htm";
		}
		if(event.equalsIgnoreCase("3.htm"))
		{			
				return "3.htm";
		}
		if(event.equalsIgnoreCase("4.htm"))
		{			
				return "4.htm";
		}
		if(event.equalsIgnoreCase("5.htm"))
		{			
				return "5.htm";
		}			
		
		if(event.equalsIgnoreCase("aio_option_1"))
		{
		   if(npcId == NPC[0])
		     if(player.isAio())
		    {
			    htmltext = "already_isaio.htm";
		    }
		     else
		    {
		        if(st.getQuestItemsCount(MOEDA1)>= COUNT1)
				{
					    st.takeItems(MOEDA1,COUNT1);
				        player.setAio(true);
                        player.getInventory().destroyItemByItemId("", Config.AIO_ITEMID, 1, player, null);
                        player.getWarehouse().destroyItemByItemId("", Config.AIO_ITEMID, 1, player, null);
						player.getAppearance().setNameColor(Config.AIO_NCOLOR);
			            player.getAppearance().setTitleColor(Config.AIO_TCOLOR);
                        int daysleft = player.getAioEndTime() <= 0 ? 0 :(int) 
                          ((player.getAioEndTime()-System.currentTimeMillis())/86400000);
                        player.setEndTime("aio", daysleft + DIAS1);
                        player.getStat().addExp(player.getStat().getExpForLevel(81));
                        player.rewardAioSkills();
                        player.sendSkillList();
                        player.getInventory().addItem("", Config.AIO_ITEMID, 1, player, null);
                        player.broadcastUserInfo();
						htmltext = "win_aio.htm";
				}
		        else
				{
			    htmltext = "no_item.htm";
		        }
		    }
		}
		if(event.equalsIgnoreCase("aio_option_2"))
		{
		   if(npcId == NPC[0])
		     if(player.isAio())
		    {
			    htmltext = "already_isaio.htm";
		    }
		     else
		    {
		        if(st.getQuestItemsCount(MOEDA2)>= COUNT2)
				{
					    st.takeItems(MOEDA2,COUNT2);
				        player.setAio(true);
                        player.getInventory().destroyItemByItemId("", Config.AIO_ITEMID, 1, player, null);
                        player.getWarehouse().destroyItemByItemId("", Config.AIO_ITEMID, 1, player, null);
						player.getAppearance().setNameColor(Config.AIO_NCOLOR);
			            player.getAppearance().setTitleColor(Config.AIO_TCOLOR);
                        int daysleft = player.getAioEndTime() <= 0 ? 0 :(int) 
                          ((player.getAioEndTime()-System.currentTimeMillis())/86400000);
                        player.setEndTime("aio", daysleft + DIAS2);
                        player.getStat().addExp(player.getStat().getExpForLevel(81));
                        player.rewardAioSkills();
                        player.sendSkillList();
                        player.getInventory().addItem("", Config.AIO_ITEMID, 1, player, null);
                        player.broadcastUserInfo();
						htmltext = "win_aio.htm";
				}
		        else
				{
			    htmltext = "no_item.htm";
		        }
		    }
		}
		if(event.equalsIgnoreCase("aio_option_3"))
		{
		   if(npcId == NPC[0])
		     if(player.isAio())
		    {
			    htmltext = "already_isaio.htm";
		    }
		     else
		    {
		        if(st.getQuestItemsCount(MOEDA3)>= COUNT3)
				{
					    st.takeItems(MOEDA3,COUNT3);
				        player.setAio(true);
                        player.getInventory().destroyItemByItemId("", Config.AIO_ITEMID, 1, player, null);
                        player.getWarehouse().destroyItemByItemId("", Config.AIO_ITEMID, 1, player, null);
						player.getAppearance().setNameColor(Config.AIO_NCOLOR);
			            player.getAppearance().setTitleColor(Config.AIO_TCOLOR);
                        int daysleft = player.getAioEndTime() <= 0 ? 0 :(int) 
                          ((player.getAioEndTime()-System.currentTimeMillis())/86400000);
                        player.setEndTime("aio", daysleft + DIAS3);
                        player.getStat().addExp(player.getStat().getExpForLevel(81));
                        player.rewardAioSkills();
                        player.sendSkillList();
                        player.getInventory().addItem("", Config.AIO_ITEMID, 1, player, null);
                        player.broadcastUserInfo();
						htmltext = "win_aio.htm";
				}
		        else
				{
			    htmltext = "no_item.htm";
		        }
		    }
		}
		if(event.equalsIgnoreCase("remove_aio"))
		{
		   if(npcId == NPC[0])
		if(player.isAio())
		{
            player.getInventory().destroyItemByItemId("", Config.AIO_ITEMID, 1, player, null);
            player.getWarehouse().destroyItemByItemId("", Config.AIO_ITEMID, 1, player, null);
		    player.setAio(false);
		    player.setAioEndTime(0);
			player.lostAioSkills();
			player.getAppearance().setNameColor(0xFFFFFF);
			player.getAppearance().setTitleColor(0xFFFF77);
			player.broadcastUserInfo();
			player.sendSkillList();
			player.broadcastUserInfo();

			htmltext = "remove_aio.htm";
		}
		else
			htmltext = "none.htm";
		    return htmltext;
		}		
				st.exitQuest(true);
				return htmltext;
	}

	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		QuestState st = player.getQuestState(getName());
		if(st == null)
		{
			st = newQuestState(player);
        }			
		String htmltext = "";
		int npcId = npc.getNpcId();
		if(npcId == NPC[0])
		{
			htmltext = "1.htm";
		}
		return htmltext;
	}

	public static void main(String[] args)
	{
		new AioSeller(-1, "AioSeller", "custom");
	}
}