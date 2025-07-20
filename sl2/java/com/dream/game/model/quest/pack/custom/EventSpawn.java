package com.dream.game.model.quest.pack.custom;

import com.dream.Config;
import com.dream.game.datatables.sql.NpcTable;
import com.dream.game.idfactory.IdFactory;
import com.dream.game.model.actor.instance.L2NpcInstance;
import com.dream.game.model.quest.Quest;
import com.dream.game.templates.chars.L2NpcTemplate;
import com.dream.tools.random.Rnd;

import org.apache.log4j.Logger;

public class EventSpawn extends Quest
{
	private static int[][] LOC1 =
	{
		{
			80807,
			149140,
			-3469
		},
		{
			148045,
			25684,
			-2013
		},
		{
			81976,
			53912,
			-1496
		},
		{
			18018,
			145348,
			-3058
		},
		{
			110561,
			219833,
			-3672
		},
		{
			-14731,
			122721,
			-3117
		},
		{
			147343,
			-56668,
			-2781
		},
		{
			86947,
			-141731,
			-1342
		},
		{
			42936,
			-47430,
			-801
		}
	};

	private static int[][] LOC2 =
	{
		{
			80807,
			149066,
			-3469
		},
		{
			148122,
			25684,
			-2013
		},
		{
			81967,
			53842,
			-1496
		},
		{
			18073,
			145284,
			-3058
		},
		{
			110561,
			219769,
			-3672
		},
		{
			-14731,
			122786,
			-3117
		},
		{
			147310,
			-56732,
			-2781
		},
		{
			86918,
			-141668,
			-1342
		},
		{
			42943,
			-47525,
			-801
		}
	};
	private static int[][] LOCL =
	{
		{
			78249,
			149122,
			-3597
		},
		{
			147296,
			29411,
			-2269
		},
		{
			79243,
			55847,
			-1564
		},
		{
			19857,
			144991,
			-3108
		},
		{
			110357,
			221201,
			-3543
		},
		{
			-15940,
			125109,
			-3133
		},
		{
			144446,
			-55333,
			-2981
		},
		{
			84064,
			-143408,
			-1542
		},
		{
			45757,
			-48676,
			-798
		}
	};
	private static Logger _log = Logger.getLogger(EventSpawn.class);

	public static void main(String[] args)
	{
		new EventSpawn();
	}

	public EventSpawn()
	{
		super(-1, "EventSpawn", "custom");

		_log.info("Spawn Events Managers");
		if (Config.BIGSQUASH_SPAWN)
		{
			_log.info(" - Big Squash");
			L2NpcTemplate template;
			template = NpcTable.getInstance().getTemplate(31255);
			L2NpcInstance santa1 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa2 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa3 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa4 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa5 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa6 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			santa1.spawnMe(148482, 27925, -2266);
			santa2.spawnMe(82207, 148713, -3460);
			santa3.spawnMe(80688, 55712, -1550);
			santa4.spawnMe(16257, 142756, -2700);
			santa5.spawnMe(-14734, 122624, -3110);
			santa6.spawnMe(-82724, 151681, -3123);

		}
		if (Config.CRISTMAS_SPAWN)
		{
			_log.info(" - Christmas event");
			L2NpcTemplate template;
			template = NpcTable.getInstance().getTemplate(31864);
			L2NpcInstance santa1 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa2 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa3 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa4 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa5 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance santa6 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			santa1.spawnMe(148482, 27861, -2266);
			santa2.spawnMe(82207, 148602, -3460);
			santa3.spawnMe(80688, 55629, -1550);
			santa4.spawnMe(16175, 142756, -2700);
			santa5.spawnMe(-14734, 122689, -3110);
			santa6.spawnMe(-82660, 151678, -3123);
		}
		if (Config.MEDAL_SPAWN)
		{
			_log.info(" - Medals event");
			L2NpcTemplate template1;
			L2NpcTemplate template2;
			L2NpcTemplate template3;
			template1 = NpcTable.getInstance().getTemplate(31228);
			template2 = NpcTable.getInstance().getTemplate(31229);
			template3 = NpcTable.getInstance().getTemplate(31230);
			for (int i = 0; i < 9; i++)
			{
				L2NpcInstance medalNpc1 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template1);
				medalNpc1.spawnMe(LOC1[i][0], LOC1[i][1], LOC1[i][2]);
				L2NpcInstance medalNpc2 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template2);
				medalNpc2.spawnMe(LOC2[i][0], LOC2[i][1], LOC2[i][2]);
			}
			L2NpcInstance medalRandomNpc = new L2NpcInstance(IdFactory.getInstance().getNextId(), template3);
			int i = Rnd.get(9);
			medalRandomNpc.spawnMe(LOCL[i][0], LOCL[i][1], LOCL[i][2]);
			L2NpcTemplate template;
			template = NpcTable.getInstance().getTemplate(32130);
			L2NpcInstance EvManager1 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager2 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager3 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager4 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager5 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager6 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			EvManager1.spawnMe(148482, 27861, -2266);
			EvManager2.spawnMe(82207, 148602, -3460);
			EvManager3.spawnMe(80688, 55629, -1550);
			EvManager4.spawnMe(16175, 142756, -2700);
			EvManager5.spawnMe(-14734, 122689, -3110);
			EvManager6.spawnMe(-82660, 151678, -3123);
		}
		if (Config.STAR_SPAWN)
		{
			_log.info(" - Starlight festival");
			L2NpcTemplate template;
			template = NpcTable.getInstance().getTemplate(31855);
			L2NpcInstance manager1 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance manager2 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance manager3 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance manager4 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance manager5 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance manager6 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			manager1.spawnMe(148482, 27861, -2266);
			manager2.spawnMe(82207, 148602, -3460);
			manager3.spawnMe(80688, 55629, -1550);
			manager4.spawnMe(16175, 142756, -2700);
			manager5.spawnMe(-14734, 122689, -3110);
			manager6.spawnMe(-82660, 151678, -3123);
		}
		if (Config.L2DAY_SPAWN)
		{
			L2NpcTemplate template;
			template = NpcTable.getInstance().getTemplate(32130);
			L2NpcInstance EvManager1 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager2 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager3 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager4 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager5 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			L2NpcInstance EvManager6 = new L2NpcInstance(IdFactory.getInstance().getNextId(), template);
			EvManager1.spawnMe(148482, 27861, -2266);
			EvManager2.spawnMe(82207, 148602, -3460);
			EvManager3.spawnMe(80688, 55629, -1550);
			EvManager4.spawnMe(16175, 142756, -2700);
			EvManager5.spawnMe(-14734, 122689, -3110);
			EvManager6.spawnMe(-82660, 151678, -3123);
		}
	}

}