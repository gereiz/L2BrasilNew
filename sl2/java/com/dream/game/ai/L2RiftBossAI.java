package com.dream.game.ai;

import com.dream.game.model.actor.L2Character.AIAccessor;
import com.dream.game.model.actor.instance.L2RiftBossInstance;

public class L2RiftBossAI extends L2AttackableAI
{
	private final L2RiftBossInstance _boss;
	private boolean _isAttacked = false;

	public L2RiftBossAI(AIAccessor accessor)
	{
		super(accessor);
		_boss = (L2RiftBossInstance) _actor;
	}

	@Override
	synchronized void changeIntention(CtrlIntention intention, Object arg0, Object arg1)
	{
		super.changeIntention(intention, arg0, arg1);
		if (intention != CtrlIntention.ACTIVE && intention != CtrlIntention.IDLE)
		{
			_isAttacked = true;
			if (_boss.getDimensionalRift().getTeleportTimerTask() != null)
			{
				_boss.getDimensionalRift().getTeleportTimerTask().cancel();
			}
		}
		else if (_isAttacked)
		{
			_isAttacked = false;
			if (_boss.getDimensionalRift().getTeleportTimerTask() != null)
			{
				_boss.getDimensionalRift().getTeleportTimerTask().schedule(1000);
			}
		}

	}
}