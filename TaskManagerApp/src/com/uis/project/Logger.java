package com.uis.project;
import java.io.*;
import java.util.*;
public class Logger {
private Date dt=new Date();
public static final int CRITICAL=4;
public static final int HIGH=3;
public static final int MEDIUM=2;
public static final int LOW=1;
private static final int conf=1;
private static final File f=new File("logger.txt");
private static Logger obj=null;
private Logger() {
	
}
public static Logger getInstance() {
	if(obj==null)
		synchronized(Logger.class) {
			if(obj==null)
			obj=new Logger();
			}
	return obj;
}
public void log(String msg,int priority)
{
	new Thread(new Runnable() {
	public void run() {		
	if(priority>=conf)
	{
		BufferedWriter bw=null;

		try {
		bw=new BufferedWriter(new FileWriter(f,true));
		bw.write(msg+" "+dt);
		bw.newLine();}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(bw!=null)
			{
			try {
				bw.close();
				}
			catch(IOException x)
			{
				x.printStackTrace();
			}
		}
	}
	}
}}).start();
}

}
