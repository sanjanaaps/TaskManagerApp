package com.uis.project;

import java.io.*;
import java.util.*;

public class InputLogic {
	//input logic class
	public boolean checkCategoryExists(String categoryName) throws IOException 
	{
		return new File(CONSTANTS.path+categoryName+".txt").exists();
	}
	public boolean isValidPath(String exportPath)
	{
		return new File(exportPath).isDirectory();
	}
	public boolean validateName(String str)
	{
		if(str==null)
			return false;
		if(str.trim().equals(""))
			return false;
		if(!Character.isLetter(str.charAt(0)))
			return false;
		if(str.split(" ").length>1)
			return false;
		for(int i=0;i<str.length();i++)
			{if(Character.isDigit(str.charAt(i))==true)
					return false;}
		return true;
	}
	public boolean validString(String str)
	{
		if(str.contains(":|//."))
			return true;
		else return false;
	}
	public boolean taskNameExists(String taskName, String categoryName)
	{
		ProjectModel utility =new ProjectModel();
		List<JavaBean> l1=utility.getTask(categoryName);
		if(l1.size()==0)
			return true;
		Iterator<JavaBean> i=l1.iterator();
		while(i.hasNext())
		{
			String str=i.next().getTaskName();
			if(str.equals(taskName))
				return true;
		}
		return false;
	}
	public boolean isFileEmpty(String categoryName) 
	{
		File f1=new File(CONSTANTS.path+categoryName+".txt");
		BufferedReader br=null;
		BufferedWriter bw =null;
		try {
			 bw=new BufferedWriter(new FileWriter(f1));
			 bw.write("");
			 bw.close();
			 br=new BufferedReader(new FileReader(f1));
			if(br.readLine()!=null)
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(bw!=null)
				try {
					bw.close();
				}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}
}
