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
package com.dream.game.templates.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dream.game.datatables.xml.SkillTable;
import com.dream.game.model.L2Effect;
import com.dream.game.model.L2Skill;
import com.dream.game.skills.effects.EffectTemplate;
import com.dream.game.skills.funcs.Func;
import com.dream.game.skills.funcs.FuncTemplate;
import com.dream.util.StatsSet;

public abstract class L2Equip extends L2Item
{
	public static class WeaponSkill
	{
		public L2Skill skill;
		public int chance;
	}

	protected static final Logger _log = Logger.getLogger(L2Equip.class.getName());

	public static final Func[] EMPTY_FUNC_SET = new Func[0];
	public static final L2Effect[] EMPTY_EFFECT_SET = new L2Effect[0];

	private L2Skill[] _itemSkills = null;
	protected FuncTemplate[] _funcTemplates = null;

	protected EffectTemplate[] _effectTemplates = null;

	public L2Equip(AbstractL2ItemType type, StatsSet set)
	{
		super(type, set);

		String[] itemSkillDefs = set.getString("skills_item").split(";");

		List<L2Skill> itemSkills = null;

		// Item skills
		if (itemSkillDefs != null && itemSkillDefs.length > 0)
		{
			itemSkills = parseSkills(itemSkillDefs, "item", "armor");
		}

		if (itemSkills != null)
		{
			_itemSkills = itemSkills.toArray(new L2Skill[itemSkills.size()]);
		}
	}

	public L2Skill[] getSkills()
	{
		return _itemSkills;
	}

	protected List<WeaponSkill> parseChanceSkills(String[] from, String skillType, String itemType)
	{
		List<WeaponSkill> itemSkills = null;
		for (String skillStr : from)
		{
			if (skillStr.length() == 0)
			{
				continue;
			}

			int skillId = 0;
			int skillLevel = 0;
			int chance = 0;
			L2Skill skill = null;
			try
			{
				String[] skillDef = skillStr.split("-");
				skillId = Integer.parseInt(skillDef[0]);
				skillLevel = Integer.parseInt(skillDef[1]);
				chance = Integer.parseInt(skillDef[2]);
			}
			catch (Exception e)
			{
				_log.error("Cannot parse " + skillType + " skill \"" + skillStr + "\" for " + itemType + " item " + getItemId());
				continue;
			}

			skill = SkillTable.getInstance().getInfo(skillId, skillLevel);
			if (skill == null)
			{
				_log.error("Cannot find " + skillType + " skill (" + skillId + "," + skillLevel + ") for " + itemType + " item " + getItemId());
			}
			else
			{
				if (itemSkills == null)
				{
					itemSkills = new ArrayList<>();
				}
				WeaponSkill ws = new WeaponSkill();
				ws.skill = skill;
				ws.chance = chance;
				itemSkills.add(ws);
			}
		}
		return itemSkills;
	}

	protected List<Integer> parseRestriction(String[] from, String restrictType, String itemType)
	{
		List<Integer> values = null;
		for (String strVal : from)
		{
			int intVal = -1;
			try
			{
				intVal = Integer.parseInt(strVal);
			}
			catch (Exception e)
			{
				_log.error("Cannot parse " + restrictType + " restriction \"" + strVal + "\" for " + itemType + " " + getItemId());
				continue;
			}

			if (intVal < 0)
			{
				continue;
			}

			if (values == null)
			{
				values = new ArrayList<>();
			}
			values.add(intVal);
		}
		return values;
	}

	protected List<L2Skill> parseSkills(String[] from, String skillType, String itemType)
	{
		List<L2Skill> itemSkills = null;
		for (String skillStr : from)
		{
			if (skillStr.length() == 0)
			{
				continue;
			}

			int skillId = 0;
			int skillLevel = 0;
			L2Skill skill = null;
			try
			{
				String[] skillDef = skillStr.split("-");
				skillId = Integer.parseInt(skillDef[0]);
				skillLevel = Integer.parseInt(skillDef[1]);
			}
			catch (Exception e)
			{
				_log.error("Cannot parse " + skillType + " skill \"" + skillStr + "\" for " + itemType + " item " + getItemId());
				continue;
			}

			skill = SkillTable.getInstance().getInfo(skillId, skillLevel);
			if (skill == null)
			{
				_log.error("Cannot find " + skillType + " skill (" + skillId + "," + skillLevel + ") for " + itemType + " item " + getItemId());
			}
			else
			{
				if (itemSkills == null)
				{
					itemSkills = new ArrayList<>();
				}
				itemSkills.add(skill);
			}
		}
		return itemSkills;
	}
}