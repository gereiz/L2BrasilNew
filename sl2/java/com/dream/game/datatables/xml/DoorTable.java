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
package com.dream.game.datatables.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.dream.Config;
import com.dream.data.xml.XMLDocument;
import com.dream.game.geodata.pathfinding.AbstractNodeLoc;
import com.dream.game.idfactory.IdFactory;
import com.dream.game.manager.ClanHallManager;
import com.dream.game.model.actor.instance.L2DoorInstance;
import com.dream.game.model.entity.ClanHall;
import com.dream.game.model.mapregion.L2MapRegion;
import com.dream.game.templates.chars.L2CharTemplate;
import com.dream.util.StatsSet;

public final class DoorTable extends XMLDocument
{
	private static class SingletonHolder
	{
		protected static final DoorTable _instance = new DoorTable();
	}

	private static final Logger _log = Logger.getLogger(DoorTable.class);

	public static final DoorTable getInstance()
	{
		return SingletonHolder._instance;
	}

	public static L2DoorInstance parseLine(String line)
	{
		StringTokenizer st = new StringTokenizer(line, ";");

		String name = st.nextToken();
		int id = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int z = Integer.parseInt(st.nextToken());
		int rangeXMin = Integer.parseInt(st.nextToken());
		int rangeYMin = Integer.parseInt(st.nextToken());
		int rangeZMin = Integer.parseInt(st.nextToken());
		int rangeXMax = Integer.parseInt(st.nextToken());
		int rangeYMax = Integer.parseInt(st.nextToken());
		int rangeZMax = Integer.parseInt(st.nextToken());
		int hp = Integer.parseInt(st.nextToken());
		int pdef = Integer.parseInt(st.nextToken());
		int mdef = Integer.parseInt(st.nextToken());
		boolean unlockable = false;
		if (st.hasMoreTokens())
		{
			unlockable = Boolean.parseBoolean(st.nextToken());
		}
		boolean startOpen = false;
		if (st.hasMoreTokens())
		{
			startOpen = Boolean.parseBoolean(st.nextToken());
		}

		int collisionRadius = 0;
		if (rangeXMax - rangeXMin > rangeYMax - rangeYMin)
		{
			collisionRadius = rangeYMax - rangeYMin;
		}

		StatsSet npcDat = new StatsSet();
		npcDat.set("npcId", id);
		npcDat.set("level", 0);
		npcDat.set("jClass", "door");

		npcDat.set("baseSTR", 0);
		npcDat.set("baseCON", 0);
		npcDat.set("baseDEX", 0);
		npcDat.set("baseINT", 0);
		npcDat.set("baseWIT", 0);
		npcDat.set("baseMEN", 0);

		npcDat.set("baseShldDef", 0);
		npcDat.set("baseShldRate", 0);
		npcDat.set("baseAccCombat", 38);
		npcDat.set("baseEvasRate", 38);
		npcDat.set("baseCritRate", 38);

		npcDat.set("collision_radius", collisionRadius);
		npcDat.set("collision_height", rangeZMax - rangeZMin & 0xfff0);
		npcDat.set("fcollision_radius", collisionRadius);
		npcDat.set("fcollision_height", rangeZMax - rangeZMin & 0xfff0);
		npcDat.set("sex", "male");
		npcDat.set("type", "");
		npcDat.set("baseAtkRange", 0);
		npcDat.set("baseMpMax", 0);
		npcDat.set("baseCpMax", 0);
		npcDat.set("rewardExp", 0);
		npcDat.set("rewardSp", 0);
		npcDat.set("basePAtk", 0);
		npcDat.set("baseMAtk", 0);
		npcDat.set("basePAtkSpd", 0);
		npcDat.set("aggroRange", 0);
		npcDat.set("baseMAtkSpd", 0);
		npcDat.set("rhand", 0);
		npcDat.set("lhand", 0);
		npcDat.set("armor", 0);
		npcDat.set("baseWalkSpd", 0);
		npcDat.set("baseRunSpd", 0);
		npcDat.set("name", name);
		npcDat.set("baseHpMax", hp);
		npcDat.set("baseHpReg", 3.e-3f);
		npcDat.set("baseMpReg", 3.e-3f);
		npcDat.set("basePDef", pdef);
		npcDat.set("baseMDef", mdef);

		L2CharTemplate template = new L2CharTemplate(npcDat);
		L2DoorInstance door = new L2DoorInstance(IdFactory.getInstance().getNextId(), template, id, name, unlockable);
		door.setRange(rangeXMin, rangeYMin, rangeZMin, rangeXMax, rangeYMax, rangeZMax);
		door.setMapRegion(MapRegionTable.getInstance().getRegion(x, y, z));
		template.setCollisionRadius(Math.min(x - rangeXMin, y - rangeYMin));
		door.setCurrentHpMp(door.getMaxHp(), door.getMaxMp());
		door.setOpen(startOpen);
		door.getPosition().setXYZInvisible(x, y, z);

		return door;
	}

	public static L2DoorInstance parseNode(Node door)
	{

		String name = door.getAttributes().getNamedItem("name").getNodeValue();
		int id = Integer.parseInt(door.getAttributes().getNamedItem("id").getNodeValue());
		int x, y, z, rangeXMin, rangeYMin, rangeZMin, rangeXMax, rangeYMax, rangeZMax, hp, pdef, mdef;
		x = y = z = rangeXMin = rangeYMin = rangeZMin = rangeXMax = rangeYMax = rangeZMax = hp = pdef = mdef = 0;
		boolean unlockable, startOpen;
		unlockable = startOpen = false;
		for (Node n = door.getFirstChild(); n != null; n = n.getNextSibling())
			if (n.getNodeName().equals("position"))
			{
				x = Integer.parseInt(n.getAttributes().getNamedItem("x").getNodeValue());
				y = Integer.parseInt(n.getAttributes().getNamedItem("y").getNodeValue());
				z = Integer.parseInt(n.getAttributes().getNamedItem("z").getNodeValue());
			}
			else if (n.getNodeName().equals("range"))
			{
				rangeXMin = Integer.parseInt(n.getAttributes().getNamedItem("XMin").getNodeValue());
				rangeYMin = Integer.parseInt(n.getAttributes().getNamedItem("YMin").getNodeValue());
				rangeZMin = Integer.parseInt(n.getAttributes().getNamedItem("ZMin").getNodeValue());
				rangeXMax = Integer.parseInt(n.getAttributes().getNamedItem("XMax").getNodeValue());
				rangeYMax = Integer.parseInt(n.getAttributes().getNamedItem("YMax").getNodeValue());
				rangeZMax = Integer.parseInt(n.getAttributes().getNamedItem("ZMax").getNodeValue());

			}
			else if (n.getNodeName().equals("stat"))
			{
				hp = Integer.parseInt(n.getAttributes().getNamedItem("hp").getNodeValue());
				pdef = Integer.parseInt(n.getAttributes().getNamedItem("pdef").getNodeValue());
				mdef = Integer.parseInt(n.getAttributes().getNamedItem("mdef").getNodeValue());
				unlockable = Boolean.parseBoolean(n.getAttributes().getNamedItem("unlockable").getNodeValue());
				startOpen = Boolean.parseBoolean(n.getAttributes().getNamedItem("isOpen").getNodeValue());
			}
		int collisionRadius = 0;
		if (rangeXMax - rangeXMin > rangeYMax - rangeYMin)
		{
			collisionRadius = rangeYMax - rangeYMin;
		}

		StatsSet npcDat = new StatsSet();

		npcDat.set("npcId", id);
		npcDat.set("level", 0);

		npcDat.set("baseSTR", 0);
		npcDat.set("baseCON", 0);
		npcDat.set("baseDEX", 0);
		npcDat.set("baseINT", 0);
		npcDat.set("baseWIT", 0);
		npcDat.set("baseMEN", 0);

		npcDat.set("baseShldDef", 0);
		npcDat.set("baseShldRate", 0);
		npcDat.set("baseAccCombat", 38);
		npcDat.set("baseEvasRate", 38);
		npcDat.set("baseCritRate", 38);

		npcDat.set("collision_radius", collisionRadius);
		npcDat.set("collision_height", rangeZMax - rangeZMin & 0xfff0);
		npcDat.set("fcollision_radius", collisionRadius);
		npcDat.set("fcollision_height", rangeZMax - rangeZMin & 0xfff0);
		npcDat.set("sex", "male");
		npcDat.set("type", "");
		npcDat.set("baseAtkRange", 0);
		npcDat.set("baseMpMax", 0);
		npcDat.set("baseCpMax", 0);
		npcDat.set("rewardExp", 0);
		npcDat.set("rewardSp", 0);
		npcDat.set("basePAtk", 0);
		npcDat.set("baseMAtk", 0);
		npcDat.set("basePAtkSpd", 0);
		npcDat.set("aggroRange", 0);
		npcDat.set("baseMAtkSpd", 0);
		npcDat.set("rhand", 0);
		npcDat.set("lhand", 0);
		npcDat.set("armor", 0);
		npcDat.set("baseWalkSpd", 0);
		npcDat.set("baseRunSpd", 0);
		npcDat.set("name", name);
		npcDat.set("baseHpMax", hp);
		npcDat.set("baseHpReg", 3.e-3f);
		npcDat.set("baseMpReg", 3.e-3f);
		npcDat.set("basePDef", pdef);
		npcDat.set("baseMDef", mdef);

		L2CharTemplate template = new L2CharTemplate(npcDat);
		L2DoorInstance doorInstance = new L2DoorInstance(IdFactory.getInstance().getNextId(), template, id, name, unlockable);
		doorInstance.setRange(rangeXMin, rangeYMin, rangeZMin, rangeXMax, rangeYMax, rangeZMax);
		doorInstance.setCurrentHpMp(doorInstance.getMaxHp(), doorInstance.getMaxMp());
		doorInstance.setMapRegion(MapRegionTable.getInstance().getRegion(x, y, z));
		template.setCollisionRadius(Math.min(x - rangeXMin, y - rangeYMin));
		doorInstance.setOpen(startOpen);
		doorInstance.getPosition().setXYZInvisible(x, y, z);

		return doorInstance;
	}

	private final Map<Integer, L2DoorInstance> _doors = new HashMap<>();

	public DoorTable()
	{
		reloadAll();
	}

	public boolean checkIfDoorsBetween(AbstractNodeLoc start, AbstractNodeLoc end)
	{
		return checkIfDoorsBetween(start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ());
	}

	public boolean checkIfDoorsBetween(int x, int y, int z, int tx, int ty, int tz)
	{
		L2MapRegion region = MapRegionTable.getInstance().getRegion(x, y, z);

		Collection<L2DoorInstance> checkDoors = new ArrayList<>();
		checkDoors = getDoors();

		for (L2DoorInstance doorInst : checkDoors)
		{
			if (doorInst.getMapRegion() != region)
			{
				continue;
			}

			if (doorInst.getXMax() == 0)
			{
				continue;
			}
			if (x <= doorInst.getXMax() && tx >= doorInst.getXMin() || tx <= doorInst.getXMax() && x >= doorInst.getXMin())
				if (y <= doorInst.getYMax() && ty >= doorInst.getYMin() || ty <= doorInst.getYMax() && y >= doorInst.getYMin())
					if (doorInst.getStatus().getCurrentHp() > 0 && !doorInst.getOpen())
					{
						int px1 = doorInst.getXMin();
						int py1 = doorInst.getYMin();
						int pz1 = doorInst.getZMin();
						int px2 = doorInst.getXMax();
						int py2 = doorInst.getYMax();
						int pz2 = doorInst.getZMax();

						int l = tx - x;
						int m = ty - y;
						int n = tz - z;

						int dk;

						if ((dk = doorInst.getA() * l + doorInst.getB() * m + doorInst.getC() * n) == 0)
						{
							continue;
						}

						float p = (float) (doorInst.getA() * x + doorInst.getB() * y + doorInst.getC() * z + doorInst.getD()) / (float) dk;

						int fx = (int) (x - l * p);
						int fy = (int) (y - m * p);
						int fz = (int) (z - n * p);

						if (Math.min(x, tx) <= fx && fx <= Math.max(x, tx) && Math.min(y, ty) <= fy && fy <= Math.max(y, ty) && Math.min(z, tz) <= fz && fz <= Math.max(z, tz))
							if ((fx >= px1 && fx <= px2 || fx >= px2 && fx <= px1) && (fy >= py1 && fy <= py2 || fy >= py2 && fy <= py1) && (fz >= pz1 && fz <= pz2 || fz >= pz2 && fz <= pz1))
								return true;
					}
		}

		return false;
	}

	public L2DoorInstance getDoor(Integer id)
	{
		return _doors.get(id);
	}

	public Collection<L2DoorInstance> getDoors()
	{
		return _doors.values();
	}

	@Override
	protected void parseDocument(Document doc)
	{
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
			if (n.getNodeName().equals("list"))
			{
				for (Node doorNode = n.getFirstChild(); doorNode != null; doorNode = doorNode.getNextSibling())
					if (doorNode.getNodeName().equals("door"))
					{
						L2DoorInstance door = parseNode(doorNode);
						putDoor(door);
						door.spawnMe(door.getX(), door.getY(), door.getZ());
						if (door.getDoorName().startsWith("goe"))
						{
							door.setAutoActionDelay(420000);
						}
						else if (door.getDoorName().startsWith("aden_tower"))
						{
							door.setAutoActionDelay(300000);
						}
					}
			}
	}

	public void putDoor(L2DoorInstance door)
	{
		_doors.put(door.getDoorId(), door);
	}

	public void registerToClanHalls()
	{
		for (L2DoorInstance door : getDoors())
		{
			ClanHall clanhall = ClanHallManager.getInstance().getNearbyClanHall(door.getX(), door.getY(), 700);
			if (clanhall != null)
			{
				clanhall.getDoors().add(door);
				door.setClanHall(clanhall);
			}
		}
	}

	public void reloadAll()
	{
		_doors.clear();
		try
		{
			load(new File(Config.DATAPACK_ROOT, "data/xml/world/door.xml"));
			_log.info("Door Data: Loaded " + _doors.size() + " Door Templates.");
		}
		catch (Exception e)
		{
			_log.warn("DoorTable: Error while loading doors", e);
		}

	}

	public void setCommanderDoors()
	{
		for (L2DoorInstance door : getDoors())
			if (door.getFort() != null && door.getOpen())
			{
				door.setOpen(false);
				door.setIsCommanderDoor(true);
			}
	}
}