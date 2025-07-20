# Created by DraX on 2005.08.08 modified by Ariakas on 2005.09.19
import sys
from com.dream.game.model.quest        import State
from com.dream.game.model.quest        import QuestState
from com.dream.game.model.quest.jython import QuestJython as JQuest

qn = "30520_reed_occupation_change"

WAREHOUSE_CHIEF_REED = 30520

class Quest (JQuest) :

 def __init__(self,id,name,descr): JQuest.__init__(self,id,name,descr)

 def onEvent (self,event,st):
   htmltext = event
   return htmltext

 def onTalk (Self,npc,player): 
   st = player.getQuestState(qn)
   npcId = npc.getNpcId()
   Race = st.getPlayer().getRace()
   ClassId = st.getPlayer().getClassId()
   # Dwarfs got accepted
   if npcId == WAREHOUSE_CHIEF_REED and Race in [Race.Dwarf]:
     if ClassId in [ClassId.dwarvenFighter]:
       htmltext = "30520-01.htm"
       return htmltext
     if ClassId in [ClassId.scavenger, ClassId.artisan]:
       htmltext = "30520-05.htm"
       st.exitQuest(1)
       return htmltext
     if ClassId in [ClassId.bountyHunter, ClassId.warsmith]:
       htmltext = "30520-06.htm"
       st.exitQuest(1)
       return htmltext
   # All other Races must be out
   if npcId == WAREHOUSE_CHIEF_REED and Race in [Race.Orc, Race.Darkelf, Race.Elf, Race.Human]:
     st.exitQuest(1)
     return "30520-07.htm"

QUEST   = Quest(30520,qn,"village_master")

QUEST.addStartNpc(30520)

QUEST.addTalkId(30520)