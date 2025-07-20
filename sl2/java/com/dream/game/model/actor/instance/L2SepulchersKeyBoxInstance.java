package com.dream.game.model.actor.instance;

import com.dream.game.templates.chars.L2NpcTemplate;

public class L2SepulchersKeyBoxInstance extends L2SepulcherNpcInstance
{
	private boolean hasKey = true;
	private final Object _sync = new Object();

	public L2SepulchersKeyBoxInstance(int objectID, L2NpcTemplate template)
	{
		super(objectID, template);

	}

	@Override
	protected void doAction(L2PcInstance player)
	{
		if (isBusy())
			return;
		synchronized (_sync)
		{
			if (!hasKey)
				return;
			setBusy(true);
			player.addItem("FourSepulchers", 7260, 1, null, true);
			setIsInvul(false);
			hasKey = false;
		}
		reduceCurrentHp(getMaxHp() + 1, player);
		_mausoleum.nextStage();
	}

	@Override
	public void onSpawn()
	{
		setBusy(false);
		hasKey = true;
	}

}