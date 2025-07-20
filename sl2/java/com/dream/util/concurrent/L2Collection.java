/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.dream.util.concurrent;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

import com.dream.lang.L2Entity;
import com.dream.util.SingletonMap;

import javolution.util.FastCollection;
import javolution.util.FastCollection.Record;

public abstract class L2Collection<T extends L2Entity>
{
	private final SingletonMap<Integer, T> _map = new SingletonMap<>();

	protected L2Collection()
	{
		if (this instanceof L2SharedCollection)
		{
			_map.setShared();
		}
	}

	public void add(T obj)
	{
		_map.put(obj.getObjectId(), obj);
	}

	public void clear()
	{
		_map.clear();
	}

	public boolean contains(T obj)
	{
		return _map.containsKey(obj.getObjectId());
	}

	protected void executeForEach(Executable<T> executable)
	{
		if (_map.isEmpty())
			return;

		Collection<T> values = _map.values();

		if (values instanceof FastCollection)
		{
			FastCollection<T> values2 = (FastCollection<T>) values;

			for (Record r = values2.head(), end = values2.tail(); (r = r.getNext()) != end;)
			{
				executable.execute(values2.valueOf(r));
			}
		}
		else
			throw new RuntimeException("Shouldn't happen!");
	}

	public T get(Integer id)
	{
		return _map.get(id);
	}

	public boolean isEmpty()
	{
		return _map.isEmpty();
	}

	protected Iterator<T> iterator()
	{
		return _map.values().iterator();
	}

	public void remove(T obj)
	{
		_map.remove(obj.getObjectId());
	}

	public int size()
	{
		return _map.size();
	}

	@SuppressWarnings("unchecked")
	public T[] toArray(T[] array)
	{
		if (array.length != _map.size())
		{
			array = (T[]) Array.newInstance(array.getClass().getComponentType(), _map.size());
		}

		if (_map.isEmpty() && array.length == 0)
			return array;
		return _map.values().toArray(array);
	}
}