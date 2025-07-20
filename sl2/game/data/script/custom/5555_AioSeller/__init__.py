#Created By Cheed!!!

import sys
from com.l2jfrozen.gameserver.model.actor.instance import L2PcInstance
from com.l2jfrozen.gameserver.model.actor.instance import L2NpcInstance
from java.util import Iterator
from com.l2jfrozen.util.database import L2DatabaseFactory
from com.l2jfrozen.gameserver.model.quest import State
from com.l2jfrozen.gameserver.model.quest import QuestState
from com.l2jfrozen import Config
from com.l2jfrozen.gameserver.model.quest.jython import QuestJython as JQuest
from com.l2jfrozen.gameserver.datatables import SkillTable
from com.l2jfrozen.gameserver.network.serverpackets import SocialAction
from com.l2jfrozen.gameserver.model.quest.jython import QuestJython as JQuest

qn = "5555_AioSeller"

NPC=[555555]

#iD do item que sera a moeda.
Moeda= 57

#Quantidade de dias e item para a opcao 1.
DIAS_AIO1= 30
ItemCount1=30000000
#Quantidade de dias e item para a opcao 2.
DIAS_AIO2= 60
ItemCount2=60000000
#Quantidade de dias e item para a opcao 3.
DIAS_AIO3= 90
ItemCount3=90000000

QuestId     = 5555
QuestName   = "AioSeller"
QuestDesc   = "custom"
InitialHtml = "1.htm"

print "INFO  AioSeller==> ON"

class Quest (JQuest) :

 def __init__(self,id,name,descr): JQuest.__init__(self,id,name,descr)
 
 def onAdvEvent (self,event,npc,player) :
    htmltext = event
    st = player.getQuestState(qn)
    if not st: return

    def doAio(player,days) :
     st.getPlayer().setAio(True)
     st.player.setEndTime("aio", days);
     st.player.getStat().addExp(player.getStat().getExpForLevel(81));
     st.player.broadcastPacket(SocialAction(st.player.getObjectId(),16))	
     st.player.getAppearance().setNameColor(Config.AIO_NCOLOR)
     st.player.getAppearance().setTitleColor(Config.AIO_TCOLOR)
     st.player.rewardAioSkills()
     st.player.sendSkillList()

	 
    if event == "1" :
     if st.getPlayer().isAio():
      return "no.htm"
     else :
        if st.getQuestItemsCount(Moeda) >= ItemCount1 :
                  st.takeItems(Moeda,ItemCount1)			  
                  doAio(st.player, DIAS_AIO1)				  
                  st.player.sendMessage("Parabens Voce Virou AioX!")
                  return "1.htm"
        else:
         st.player.sendMessage("Voce Nao Tem Itens Suficiente!")
         return "1.htm"

    elif event == "2" :
     if st.getPlayer().isAio():
      return "no.htm"
     else :
        if st.getQuestItemsCount(Moeda) >= ItemCount2 :
                  st.takeItems(Moeda,ItemCount2)				  
                  doAio(st.player, DIAS_AIO2);				  			  
                  st.player.sendMessage("Parabens Voce Virou AioX")				  
                  return "1.htm"
        else:
         st.player.sendMessage("Voce Nao Tem Itens Suficiente!")
         return "1.htm"

    elif event == "3" :
     if st.getPlayer().isAio():
      return "no.htm"
     else :
        if st.getQuestItemsCount(Moeda) >= ItemCount3 :
                  st.takeItems(Moeda,ItemCount3)				  
                  doAio(st.player, DIAS_AIO3);				  			  
                  st.player.sendMessage("Parabens Voce Virou AioX!")				  
                  return "1.htm"
        else:
         st.player.sendMessage("Voce Nao Tem Itens Suficiente!")
         return "1.htm"
		 
    return htmltext		
	
 def onTalk (self,npc,player):
		htmltext = "<html><head><body>You can't buy while you are</body></html>"
		st = player.getQuestState(qn)
		if not st : return htmltext
		npcId = npc.getNpcId()		
		if st.player.getPvpFlag() > 0 :#Player Flag
			htmltext = "<html><head><body>You can't buy while you are <font color=\"800080\">flagged!</font><br>Wait some time and try again!</body></html>"
			st.exitQuest(1)
		elif st.player.getKarma() > 0 :#Player com Karma
			st.exitQuest(1)
			htmltext = "<html><head><body>You have too much <font color=\"FF0000\">karma!</font><br>Come back,<br>when you don't have any karma!</body></html>"
		elif st.player.isInCombat() :#Player modo de Combate
			st.exitQuest(1)
			htmltext = "<html><head><body>You can't buy while you are attacking!<br>Stop your fight and try again!</body></html>"
		else:
			
			return InitialHtml
		return htmltext

QUEST = Quest(5555,qn,"custom")
CREATED     = State('Start', QUEST)
STARTING    = State('Starting', QUEST)
STARTED     = State('Started', QUEST)
COMPLETED   = State('Completed', QUEST)
QUEST.setInitialState(CREATED)

for npcId in NPC:
 QUEST.addStartNpc(npcId)
 QUEST.addTalkId(npcId)
