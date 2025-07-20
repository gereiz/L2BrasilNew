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
package com.dream.game.manager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

import com.dream.Config;
import com.dream.game.model.actor.instance.L2ItemInstance;
import com.dream.game.model.world.L2World;
import com.dream.game.network.ThreadPoolManager;
import com.dream.game.templates.item.L2EtcItemType;

public class ItemsAutoDestroy
{
	protected class CheckItemsForDestroy extends Thread
	{
		@Override
		public void run()
		{
			removeItems();
		}
	}

	protected static Logger _log = Logger.getLogger(ItemsAutoDestroy.class);

	private static ItemsAutoDestroy _instance;
	protected static long _sleep;

	public static ItemsAutoDestroy getInstance()
	{
		if (_instance == null)
		{
			_instance = new ItemsAutoDestroy();
		}
		return _instance;
	}

	protected List<L2ItemInstance> _items = null;

	private ItemsAutoDestroy()
	{
		_items = new CopyOnWriteArrayList<>();
		_sleep = Config.AUTODESTROY_ITEM_AFTER * 1000;
		if (_sleep == 0)
		{
			_sleep = 3600000;
		}
		ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new CheckItemsForDestroy(), 5000, 5000);
	}

	public synchronized void addItem(L2ItemInstance item)
	{
		item.setDropTime(System.currentTimeMillis());
		_items.add(item);
	}

	public synchronized void removeItems()
	{
		if (_items.isEmpty())
			return;

		long curtime = System.currentTimeMillis();
		for (L2ItemInstance item : _items)
			if (item == null || item.getDropTime() == 0 || item.getLocation() != L2ItemInstance.ItemLocation.VOID)
			{
				_items.remove(item);
			}
			else if (item.getItemType() == L2EtcItemType.HERB)
			{
				if (curtime - item.getDropTime() > Config.HERB_AUTO_DESTROY_TIME)
				{
					L2World.getInstance().removeVisibleObject(item, item.getWorldRegion());
					L2World.getInstance().removeObject(item);
					_items.remove(item);
					if (Config.SAVE_DROPPED_ITEM)
					{
						ItemsOnGroundManager.getInstance().removeObject(item);
					}
				}
			}
			else if (curtime - item.getDropTime() > _sleep)
			{
				L2World.getInstance().removeVisibleObject(item, item.getWorldRegion());
				L2World.getInstance().removeObject(item);
				_items.remove(item);
				if (Config.SAVE_DROPPED_ITEM)
				{
					ItemsOnGroundManager.getInstance().removeObject(item);
				}
			}
	}
}