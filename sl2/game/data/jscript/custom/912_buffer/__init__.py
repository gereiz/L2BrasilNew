import sys
from net.sf.l2j.gameserver.model.actor.instance import L2PcInstance
from java.util import Iterator
from net.sf.l2j.gameserver.datatables import SkillTable
from net.sf.l2j			       import L2DatabaseFactory
from net.sf.l2j.gameserver.model.quest import State
from net.sf.l2j.gameserver.model.quest import QuestState
from net.sf.l2j.gameserver.model.quest.jython import QuestJython as JQuest

qn = "912_buffer"

NPC          = [40006]
min_level	 = 1
max_level	 = 90
min_prise	 = 1
prise_ID	 = 57
QuestId      = 912
QuestName    = "buffer"
QuestDesc    = "custom"
InitialHtml  = "buff.htm"
Buff_prise   = 1
Chant_prise  = 1
Song_prise	 = 1
Dance_prise	 = 1
Hero_prise	 = 1
Noble_prise	 = 1
Summon_prise = 1	
Prophecy_prise = 1
Full_buff_f1_prise = 1
Full_buff_f2_prise = 1
Full_buff_f3_prise = 1
Full_buff_m1_prise = 1
Full_buff_m2_prise = 1
Full_buff_m3_prise = 1

print "importing custom: 912_buffer    Funcionando"

class Quest (JQuest) :

	def __init__(self,id,name,descr): JQuest.__init__(self,id,name,descr)


	def onEvent(self,event,st):
		htmltext = event
		count=st.getQuestItemsCount(prise_ID)
		if count < min_prise :
			htmltext = "<html><head><body>Voce esta sem adena.</body></html>"
			if st.getPlayer().getLevel() < min_level :
				htmltext = "<html><head><body>Voce esta sem adena.</body></html>"
				if st.getPlayer().getLevel() < max_level :
					htmltext = "<html><head><body>Voce esta sem adena.</body></html>"
		else:
			st.takeItems(prise_ID,0)
			st.getPlayer().setTarget(st.getPlayer())

			#Full buff fighter 3p
			if event == "124": 
				st.takeItems(prise_ID,Full_buff_f3_prise)
				SkillTable.getInstance().getInfo(1362,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1352,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1353,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1354,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1415,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1393,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1392,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1191,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1189,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1182,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1392,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1040,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1036,2).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1062,2).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1045,6).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1068,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1035,4).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1240,3).getEffects(st.getPlayer(),st.getPlayer())			
				SkillTable.getInstance().getInfo(1242,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1077,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1086,2).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1204,2).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1268,4).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(264,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(266,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(267,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(268,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(269,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(304,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(306,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(308,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(270,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(364,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(274,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(275,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(271,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(309,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(307,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(272,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(310,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1363,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1390,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1391,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1389,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1388,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(4700,13).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(4699,13).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1323,1).getEffects(st.getPlayer(),st.getPlayer())
				st.getPlayer().restoreHPMP()
				return "buff.htm"
				st.setState(COMPLETED)

			#Full buff mage 3p
			if event == "120": 
				st.takeItems(prise_ID,Full_buff_m3_prise)
				SkillTable.getInstance().getInfo(1362,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1352,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1353,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1354,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1415,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1393,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1392,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1191,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1189,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1182,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1392,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1040,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1036,2).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1045,6).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1048,6).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1062,2).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1035,4).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1085,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1078,6).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1304,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1243,6).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1204,2).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1303,2).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1259,4).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1059,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(266,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(264,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(267,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(306,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(308,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(270,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(268,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(304,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(273,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(307,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(276,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(349,1).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(277,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(365,1).getEffects(st.getPlayer(),st.getPlayer())												
				SkillTable.getInstance().getInfo(304,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1413,1).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(1389,3).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1391,3).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(4703,13).getEffects(st.getPlayer(),st.getPlayer())				
				SkillTable.getInstance().getInfo(4702,13).getEffects(st.getPlayer(),st.getPlayer())
				SkillTable.getInstance().getInfo(1323,1).getEffects(st.getPlayer(),st.getPlayer())
				st.getPlayer().restoreHPMP()
				return "buff.htm"
				st.setState(COMPLETED)

				
			if htmltext != event:
				st.setState(COMPLETED)
				st.exitQuest(1)
		return htmltext


	def onTalk (self,npc,player):
	   st = player.getQuestState(qn)
	   htmltext = "<html><head><body>Nada a dizer a voce</body></html>"
	   st.setState(STARTED)
	   return InitialHtml

QUEST       = Quest(912,qn,"custom")
CREATED		= State('Start',QUEST)
STARTED		= State('Started',QUEST)
COMPLETED	= State('Completed',QUEST)

QUEST.setInitialState(CREATED)

for npcId in NPC:
 QUEST.addStartNpc(npcId)
 QUEST.addTalkId(npcId)
