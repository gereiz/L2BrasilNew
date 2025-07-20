package com.dream.game.model.actor.instance;

import com.dream.game.templates.chars.L2NpcTemplate;

public class L2SepulchersOpenBoxInstance extends L2SepulcherNpcInstance
{
	public L2SepulchersOpenBoxInstance(int objectID, L2NpcTemplate template)
	{
		super(objectID, template);
	}

	@Override
	protected void doAction(L2PcInstance player)
	{
		if (isBusy())
			return;
		setBusy(true);
		setIsInvul(false);
		reduceCurrentHp(getMaxHp() + 1, player);
		_mausoleum.nextStage();
	}

	@Override
	public void onSpawn()
	{
		setBusy(false);
	}
}