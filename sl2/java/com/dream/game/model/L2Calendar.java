package com.dream.game.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import com.dream.game.GameTimeController;

public class L2Calendar implements Serializable
{
	private static final long serialVersionUID = 3020475454828258247L;

	private final GregorianCalendar cal = new GregorianCalendar();
	public int gameTicks = 3600000 / GameTimeController.MILLIS_IN_TICK;
	private long gameStarted;

	public GregorianCalendar getDate()
	{
		return cal;
	}

	public long getGameStarted()
	{
		return gameStarted;
	}

	public void setGameStarted(long started)
	{
		gameStarted = started;
	}
}