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
package com.dream.game.model.restriction;

import com.dream.game.model.actor.instance.L2MonsterInstance;
import com.dream.game.model.actor.instance.L2PcInstance;

public enum AvailableRestriction
{
	PlayerUnmount(L2PcInstance.class),
	PlayerCast(L2PcInstance.class),
	PlayerTeleport(L2PcInstance.class),
	PlayerScrollTeleport(L2PcInstance.class),
	PlayerGotoLove(L2PcInstance.class),
	PlayerSummonFriend(L2PcInstance.class),
	PlayerChat(L2PcInstance.class),

	MonsterCast(L2MonsterInstance.class),
	GlobalPlayerChat(L2PcInstance.class)

	;

	public static final AvailableRestriction forId(int id)
	{
		for (AvailableRestriction restriction : AvailableRestriction.values())
			if (restriction.ordinal() == id)
				return restriction;

		return null;
	}

	public static final AvailableRestriction forName(String name)
	{
		for (AvailableRestriction restriction : AvailableRestriction.values())
			if (restriction.name().equals(name))
				return restriction;

		return null;
	}

	private final Class<?> _applyTo;

	private AvailableRestriction(Class<?> applyTo)
	{
		_applyTo = applyTo;
	}

	public Class<?> getApplyableTo()
	{
		return _applyTo;
	}
}