#
# Criado By Felipe Franz
#
import sys
from net.sf.l2j.gameserver.model.actor.instance import L2PcInstance
from java.util import Iterator
from net.sf.l2j.gameserver.datatables import SkillTable
from net.sf.l2j			       import L2DatabaseFactory
from net.sf.l2j.gameserver.model.quest import State
from net.sf.l2j.gameserver.model.quest import QuestState
from net.sf.l2j.gameserver.model.quest.jython import QuestJython as JQuest

qn = "989_NPCBuffer"

NPC=[40006]
ADENA_ID=57
QuestId     = 989
QuestName   = "NPCBuffer"
QuestDesc   = "custom"
InitialHtml = "1.htm"

print "importing custom: 989: NPCBuffer"

class Quest (JQuest) :

	def __init__(self,id,name,descr): JQuest.__init__(self,id,name,descr)


	def onEvent(self,event,st):
		htmltext = event
		count=st.getQuestItemsCount(ADENA_ID)
		if count < 1000000  or st.getPlayer().getLevel() < 1 :
			htmltext = "<html><head><body>Tu nao possui adenas suficientes,<br> ou esta abaixo do level necessario (40).</body></html>"
		else:
			st.takeItems(ADENA_ID,0)
			st.getPlayer().setTarget(st.getPlayer())
			
			#Might
			if event == "2":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1068,3),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Shield
			if event == "3":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1040,3),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Focus
			if event == "4":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1077,3),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Death Whisper
			if event == "5":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1242,3),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Bless the Body
			if event == "6":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1045,6),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Bless the Soul
			if event == "7":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1048,6),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Haste
			if event == "8":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1086,2),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Guidance
			if event == "9":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1240,3),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Acumen
			if event == "10":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1085,3),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Concentration
			if event == "11":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1078,6),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Magic Barrier
			if event == "12":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1036,2),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Wind Walk
			if event == "13":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1204,2),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Berserker Spirit
			if event == "14":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1062,2),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Bless Shield
			if event == "15":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1243,6),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Mental Shield
			if event == "16":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1035,4),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Empower
			if event == "17":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1059,3),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Wild Magic
			if event == "18":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1303,2),False,False)
				st.getPlayer().restoreHPMP()
				return "2.htm"		
				st.setState(COMPLETED)

			#Agility
			if event == "19":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1087,3),False,False)
				st.getPlayer().restoreHPMP()
				return "6.htm"		
				st.setState(COMPLETED)			

			#Resist Shock
			if event == "20":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1259,4),False,False)
				st.getPlayer().restoreHPMP()
				return "9.htm"		
				st.setState(COMPLETED)

			#Decrease Weight
			if event == "21":
				st.takeItems(ADENA_ID,1)	
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1257,3),False,False)
				st.getPlayer().restoreHPMP()
				return "6.htm"

			#Arcane Protection
			if event == "22":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1354,1),False,False)	
				st.getPlayer().restoreHPMP()
				return "9.htm"

			#Divine Protection
			if event == "23":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1353,1),False,False)
				st.getPlayer().restoreHPMP()
				return "9.htm"

			#Elemental Protection
			if event == "24":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1352,1),False,False)
				st.getPlayer().restoreHPMP()
				return "9.htm"		
				st.setState(COMPLETED)

			#Holy Resistance
			if event == "25":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1392,3),False,False)
				st.getPlayer().restoreHPMP()
				return "9.htm"

			#Unholy Resistance
			if event == "26":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1393,3),False,False)
				st.getPlayer().restoreHPMP()
				return "9.htm"

			#Kiss of Eva
			if event == "27":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1073,2),False,False)
				st.getPlayer().restoreHPMP()
				return "6.htm"

			#Regeneration
			if event == "28":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1044,3),False,False)
				st.getPlayer().restoreHPMP()
				return "6.htm"

			#Resist Poison
			if event == "29":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1033,3),False,False)
				st.getPlayer().restoreHPMP()
				return "6.htm"

			#Invigor
			if event == "30":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1032,3),False,False)
				st.getPlayer().restoreHPMP()
				return "6.htm"
			
			#Vampiric Rage
			if event == "31":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1268,4),False,False)
				st.getPlayer().restoreHPMP()
				return "6.htm"		
				st.setState(COMPLETED)

			#Prophecy of Fire
			if event == "32":
				st.takeItems(ADENA_ID,1)		
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1356,1),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"

			#Prophecy of Water
			if event == "33":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1355,1),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"

			#Prophecy of Wind
			if event == "34":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1357,1),False,False)
				st.getPlayer().restoreHPMP()			
				return "5.htm"

			#Greater Might
			if event == "35":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1388,3),False,False)	
				st.getPlayer().restoreHPMP()
				return "5.htm"

			#Greater Shield
			if event == "36":
				st.takeItems(ADENA_ID,1)	
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1389,3),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"

			#War Chant
			if event == "37":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1390,3),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"

			#Earth Shield
			if event == "38":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1391,3),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"
			
			#Chant of Spirit
			if event == "39":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1362,1),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"
				st.setState(COMPLETED)
				
			#Chant of Victory
			if event == "40":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1363,1),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"
				st.setState(COMPLETED)
				
			#Chant of Magnus
			if event == "41":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1413,1),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"
				st.setState(COMPLETED)		

			#Ritual of Life
			if event == "42":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1306,6),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"

			#Nobless Blessing
			if event == "43":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1323,1),False,False)
				st.getPlayer().restoreHPMP()
				return "8.htm"
				st.setState(COMPLETED)				

			#Cancellation
			if event == "44":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(9923,1),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"
			
			#Dance of Fire
			if event == "45":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(274,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)	

			#Dance of Fury
			if event == "46":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(275,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Warrior
			if event == "47":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(271,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Inspiration
			if event == "48":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(272,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Mystic
			if event == "49":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(273,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Concentration
			if event == "50":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(276,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Siren
			if event == "51":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(365,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Light
			if event == "52":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(277,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Aqua Guard
			if event == "53":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(307,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Earth Guard
			if event == "54":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(309,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Vampire
			if event == "55":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(310,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Protection
			if event == "56":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(311,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Dance of Shadow
			if event == "57":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(366,1),False,False)
				st.getPlayer().restoreHPMP()
				return "4.htm"		
				st.setState(COMPLETED)

			#Song of Earth
			if event == "58":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(264,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Life
			if event == "59":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(265,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Water
			if event == "60":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(266,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Warding
			if event == "61":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(267,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Wind
			if event == "62":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(268,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Hunter
			if event == "63":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(269,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Invocation
			if event == "64":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(270,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Vitality
			if event == "65":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(304,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Vengeance
			if event == "66":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(305,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Flame Guard
			if event == "67":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(306,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Storm Guard
			if event == "68":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(308,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Meditation
			if event == "69":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(363,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Champion
			if event == "70":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(364,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Song of Renewal
			if event == "71":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(349,1),False,False)
				st.getPlayer().restoreHPMP()
				return "3.htm"		
				st.setState(COMPLETED)

			#Gift of Queen
			if event == "72":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4700,13),False,False)
				st.getPlayer().restoreHPMP()
				return "7.htm"		
				st.setState(COMPLETED)

			#Blessing of Queen
			if event == "73":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4699,13),False,False)
				st.getPlayer().restoreHPMP()
				return "7.htm"		
				st.setState(COMPLETED)

			#Gift of Seraphim
			if event == "74":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4703,13),False,False)
				st.getPlayer().restoreHPMP()
				return "7.htm"		
				st.setState(COMPLETED)

			#Blessing of Seraphim
			if event == "75":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4702,13),False,False)
				st.getPlayer().restoreHPMP()
				return "7.htm"		
				st.setState(COMPLETED)

			#Earth Chant
			if event == "76":
				st.takeItems(ADENA_ID,1)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1391,3),False,False)
				st.getPlayer().restoreHPMP()
				return "5.htm"

			if htmltext != event:
				st.setState(COMPLETED)
				st.exitQuest(1)
		return htmltext


	def onTalk (self,npc,player):
	   st = player.getQuestState(qn)
	   htmltext = "<html><head><body>Eu nao tenho nada para ti!</body></html>"
	   st.setState(STARTED)
	   return InitialHtml

QUEST       = Quest(QuestId,str(QuestId) + "_" + QuestName,QuestDesc)
CREATED=State('Start',QUEST)
STARTED=State('Started',QUEST)
COMPLETED=State('Completed',QUEST)

QUEST.setInitialState(CREATED)

for npcId in NPC:
 QUEST.addStartNpc(npcId)
 QUEST.addTalkId(npcId)
