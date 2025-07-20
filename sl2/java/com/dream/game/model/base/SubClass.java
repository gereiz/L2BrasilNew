package com.dream.game.model.base;

import com.dream.Config;

public final class SubClass
{
	private ClassId _class;
	private long _exp = Experience.LEVEL[Config.SUBCLASS_INIT_LEVEL];
	private int _sp = 0;
	private byte _level = Config.SUBCLASS_INIT_LEVEL;
	private int _classIndex = 1;

	public SubClass()
	{

	}

	public SubClass(int classId, int classIndex)
	{
		_class = ClassId.values()[classId];
		_classIndex = classIndex;
	}

	public SubClass(int classId, long exp, int sp, byte level, int classIndex)
	{
		_class = ClassId.values()[classId];
		_exp = exp;
		_sp = sp;
		_level = level;
		_classIndex = classIndex;
	}

	public void decLevel()
	{
		if (getLevel() == 40)
			return;

		_level--;
		setExp(Experience.LEVEL[getLevel()]);
	}

	public ClassId getClassDefinition()
	{
		return _class;
	}

	public int getClassId()
	{
		return _class.ordinal();
	}

	public int getClassIndex()
	{
		return _classIndex;
	}

	public long getExp()
	{
		return _exp;
	}

	public byte getLevel()
	{
		return _level;
	}

	public int getSp()
	{
		return _sp;
	}

	public void incLevel()
	{
		if (getLevel() == Config.SUBCLASS_MAX_LEVEL)
			return;

		_level++;
		setExp(Experience.LEVEL[getLevel()]);
	}

	public void setClassId(int classId)
	{
		_class = ClassId.values()[classId];

	}

	public void setClassIndex(int classIndex)
	{
		_classIndex = classIndex;
	}

	public void setExp(long expValue)
	{
		if (expValue > Experience.LEVEL[Config.SUBCLASS_MAX_LEVEL + 1] - 1)
		{
			expValue = Experience.LEVEL[Config.SUBCLASS_MAX_LEVEL + 1] - 1;
		}

		_exp = expValue;
	}

	public void setLevel(byte levelValue)
	{
		if (levelValue > Config.SUBCLASS_MAX_LEVEL_BYTE)
		{
			levelValue = Config.SUBCLASS_MAX_LEVEL_BYTE;
		}
		else if (levelValue < 40)
		{
			levelValue = 40;
		}

		_level = levelValue;
	}

	public void setSp(int spValue)
	{
		_sp = spValue;
	}
}