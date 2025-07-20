package com.dream.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class L2ThreadFactory implements ThreadFactory
{
	private final AtomicInteger _threadCount = new AtomicInteger();

	private final String _name;
	private final int _priority;
	private final ThreadGroup _threadGroup;

	public L2ThreadFactory(String name, int priority)
	{
		_name = name;
		_priority = priority;
		_threadGroup = new ThreadGroup(name);
	}

	public String getName()
	{
		return _name;
	}

	public int getPriority()
	{
		return _priority;
	}

	public int getThreadCount()
	{
		return _threadCount.get();
	}

	public ThreadGroup getThreadGroup()
	{
		return _threadGroup;
	}

	@Override
	public Thread newThread(Runnable runnable)
	{
		Thread thread = new Thread(getThreadGroup(), runnable);
		thread.setName(getName() + "-" + _threadCount.incrementAndGet());
		thread.setPriority(getPriority());
		return thread;
	}
}
