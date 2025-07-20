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
package com.dream.game.skills;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.dream.Config;
import com.dream.game.model.Item;
import com.dream.game.model.L2Skill;
import com.dream.game.templates.item.L2EtcItem;
import com.dream.game.templates.item.L2EtcItemType;
import com.dream.game.templates.item.L2Item;

public final class SkillsEngine
{
	private static final Logger _log = Logger.getLogger(SkillsEngine.class);

	private static final FileFilter XML_FILTER = new FileFilter()
	{
		@Override
		public boolean accept(File f)
		{
			return f.getName().endsWith(".xml");
		}
	};

	private static File[] listFiles(String dirname)
	{
		return new File(Config.DATAPACK_ROOT, dirname).listFiles(XML_FILTER);
	}

	public static List<L2Item> loadArmors(Map<Integer, Item> armorData)
	{
		final List<L2Item> list = loadData(armorData, listFiles("data/xml/stats/armor"));

		Set<Integer> xmlItem = new HashSet<>();

		for (L2Item item : list)
		{
			xmlItem.add(item.getItemId());
		}

		for (Item item : armorData.values())
			if (!xmlItem.contains(item.id))
			{
				_log.warn("SkillsEngine: Missing XML side for L2Armor - id: " + item.id);
			}
		return list;
	}

	private static List<L2Item> loadData(Map<Integer, Item> itemData, File[] files)
	{
		final List<L2Item> list = new ArrayList<>();

		for (File f : files)
		{
			DocumentItem document = new DocumentItem(itemData, f);
			document.parse();
			list.addAll(document.getItemList());
		}
		return list;
	}

	public static List<L2Item> loadItems(Map<Integer, Item> itemData)
	{
		final List<L2Item> list = loadData(itemData, listFiles("data/xml/stats/etcitem"));

		Set<Integer> xmlItem = new HashSet<>();

		for (L2Item item : list)
		{
			xmlItem.add(item.getItemId());
		}

		for (Item item : itemData.values())
			if (!xmlItem.contains(item.id))
			{
				list.add(new L2EtcItem((L2EtcItemType) item.type, item.set));
			}
		return list;
	}

	public static List<L2Skill> loadSkills()
	{
		final List<L2Skill> list = new ArrayList<>();

		for (File file : listFiles("data/xml/stats/skills"))
		{
			DocumentSkill doc = new DocumentSkill(file);
			doc.parse();
			list.addAll(doc.getSkills());
		}

		return list;
	}

	public static List<L2Item> loadWeapons(Map<Integer, Item> weaponData)
	{
		final List<L2Item> list = loadData(weaponData, listFiles("data/xml/stats/weapon"));

		Set<Integer> xmlItem = new HashSet<>();

		for (L2Item item : list)
		{
			xmlItem.add(item.getItemId());
		}

		for (Item item : weaponData.values())
			if (!xmlItem.contains(item.id))
			{
				_log.warn("SkillsEngine: Missing XML side for L2Weapon - id: " + item.id);
			}
		return list;
	}

	private SkillsEngine()
	{
	}
}
