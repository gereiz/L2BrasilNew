package com.dream.game.datatables.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dream.game.model.L2Skill;
import com.dream.game.skills.SkillsEngine;

public final class SkillTable
{
	private static final Logger _log = Logger.getLogger(SkillTable.class);

	private static SkillTable _instance;

	public static SkillTable getInstance()
	{
		if (_instance == null)
		{
			_instance = new SkillTable();
		}

		return _instance;
	}

	public static int getSkillHashCode(int skillId, int skillLevel)
	{
		return skillId * 1023 + skillLevel;
	}

	public static int getSkillHashCode(L2Skill skill)
	{
		return skill == null ? 0 : getSkillHashCode(skill.getId(), skill.getLevel());
	}

	public static void reload()
	{
		_instance = new SkillTable();
	}

	private final L2Skill[][] _skillTable;

	private final int[] _maxLevels;

	private L2Skill[] _siegeSkills;

	private SkillTable()
	{
		final List<L2Skill> skills = SkillsEngine.loadSkills();
		_log.info("Skill Engine: Loaded " + skills.size() + " skill templates from XML files.");

		int highestId = 0;
		for (L2Skill skill : skills)
			if (highestId < skill.getId())
			{
				highestId = skill.getId();
			}

		_maxLevels = new int[highestId + 1];

		int[] highestLevels = new int[highestId + 1];
		for (L2Skill skill : skills)
		{
			if (highestLevels[skill.getId()] < skill.getLevel())
			{
				highestLevels[skill.getId()] = skill.getLevel();
			}

			if (_maxLevels[skill.getId()] < skill.getLevel() && skill.getLevel() < 100)
			{
				_maxLevels[skill.getId()] = skill.getLevel();
			}
		}

		_skillTable = new L2Skill[highestId + 1][];

		for (int i = 0; i < highestLevels.length; i++)
		{
			_skillTable[i] = new L2Skill[highestLevels[i] + 1];
		}

		for (L2Skill skill : skills)
		{
			_skillTable[skill.getId()][skill.getLevel()] = skill;
		}

		int length = _skillTable.length;
		for (L2Skill[] array : _skillTable)
		{
			length += array.length;
		}

		_log.info("SkillTable: Occupying arrays for " + length + ".");
	}

	public L2Skill[][] getAll()
	{
		return _skillTable;
	}

	public L2Skill getInfo(int skillId, int level)
	{
		if (skillId < 0 || _skillTable.length <= skillId)
			return null;

		L2Skill[] array = _skillTable[skillId];

		if (array == null)
			return null;

		if (level < 0 || array.length <= level)
			return null;

		return array[level];
	}

	public int getMaxLevel(int skillId)
	{
		if (skillId < 0 || _maxLevels.length <= skillId)
			return 0;

		return _maxLevels[skillId];
	}

	public L2Skill[] getSiegeSkills(boolean addNoble)
	{
		if (_siegeSkills == null)
		{
			List<L2Skill> list = new ArrayList<>();

			list.add(getInfo(246, 1));
			list.add(getInfo(247, 1));

			if (addNoble)
			{
				list.add(getInfo(326, 1));
			}

			_siegeSkills = list.toArray(new L2Skill[list.size()]);
		}

		return _siegeSkills;
	}

	public String getSkillName(int skillId)
	{
		if (skillId < 0 || _skillTable.length <= skillId)
			return "";
		L2Skill[] array = _skillTable[skillId];
		if (array == null)
			return "";
		for (L2Skill element : array)
			if (element != null)
				return element.getName();
		return "";
	}
}