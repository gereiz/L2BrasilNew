print "Loading auto announcements..." 
import sys 
from net.sf.l2j.gameserver import Announcements 
from net.sf.l2j.gameserver import ThreadPoolManager 
from java.lang import Runnable 

INTERVAL = 20
ANNOUNCEMENT_1="Obtenha Esenn Medal PvP e Pk em PvP e Pk ."
ANNOUNCEMENT_2="But PvP ou Pk , sera Banido ."
ANNOUNCEMENT_3="GK BOSS Vocês Podem Viajar Para Matar Os Boss e Obter As Joias."
ANNOUNCEMENT_4="L2 Esenn ."

class myTask( Runnable ): 
    def __init__( self ): 
        self.name = "autoAnnouncer" 
    def run( self ): 
        Announcements.getInstance().announceToAll(ANNOUNCEMENT_1)
        Announcements.getInstance().announceToAll(ANNOUNCEMENT_2)
        Announcements.getInstance().announceToAll(ANNOUNCEMENT_3)

startInstance = myTask() 
ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(startInstance,INTERVAL*60000,INTERVAL*60000) 