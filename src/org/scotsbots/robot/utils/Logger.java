package org.scotsbots.robot.utils;

import java.util.Calendar;

public class Logger 
{	
	/**
	 * Writes a formatted message to the 'Riolog' in eclipse.
	 * @param contents contents to print
	 * @param isErr should this be red or not
	 */
	public static void riolog(String contents, boolean isErr)
	{
		if(isErr)
			System.err.println("[ERRLog] [" + Calendar.MINUTE + "mi " + Calendar.SECOND + "sec " + Calendar.MILLISECOND + "ms] " + contents);
		else
			System.out.println("[Log] [" + Calendar.MINUTE + "mi " + Calendar.SECOND + "sec " + Calendar.MILLISECOND + "ms] " + contents);
	}
	
	/**
	 * Writes a formatted message to the 'Riolog' in eclipse.
	 * @param contents contents to print
	 */
	public static void riolog(String contents)
	{
		riolog(contents, false);
	}
	
	/**
	 * Writes a formatted message to the 'Riolog' in eclipse.
	 * @param contents contents to print
	 * @param e stacktrace of exception to print
	 */
	public static void riolog(String contents, Exception e)
	{
		riolog(contents, true);
		e.printStackTrace();
	}
}
