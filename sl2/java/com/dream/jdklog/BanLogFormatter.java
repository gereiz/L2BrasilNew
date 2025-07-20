package com.dream.jdklog;

import java.util.logging.LogRecord;

import javolution.text.TextBuilder;

public final class BanLogFormatter extends L2LogFormatter
{
	@Override
	protected void format0(LogRecord record, TextBuilder tb)
	{
		appendDateLog(record, tb);
		appendParameters(record, tb, " ", false);
		appendMessage(record, tb);
	}
}
