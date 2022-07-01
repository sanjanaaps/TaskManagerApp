package com.uis.project;
import java.io.*;
import java.text.*;
import java.util.*;
public class ProjectModel {
	//Business logic class
	public String addTask(JavaBean j,String categoryName)	
	{
		BufferedWriter bw=null;
		FileWriter fw=null;
		try {	
			Date date=new Date();
			j.setCreatedDate(date);
			 fw=new FileWriter(CONSTANTS.path+categoryName+".txt",true);
					bw=new BufferedWriter(fw);
					bw.write("TaskName: "+j.getTaskName()+". Description: "+j.getDescription()+". Tags: "+j.getTags()+". Planned end date:"+j.getEndDate()+". Current date:"+j.getCreatedDate());
					bw.newLine();
			}
		 catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(bw!=null)
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(fw!=null)
				try {
					fw.close();
				}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return CONSTANTS.sucess;
		
	}
	
	public List<JavaBean> getTask(String categoryName)
	{
		List<JavaBean> tasks=new ArrayList<JavaBean>();
		
		BufferedReader br=null;
		FileReader fr=null;
		try {
			fr=new FileReader(CONSTANTS.path+categoryName+".txt");
			br=new BufferedReader(fr);
			String line="";
			while((line=br.readLine())!=null)
			{
				String sa[]=line.split(":|\\.");
				JavaBean jb=new JavaBean();
				jb.setTaskName(sa[1]);
				jb.setDescription(sa[3]);
				jb.setTags(sa[5]);
				jb.setEndDate(parseDate(sa[7]+" "+sa[8]+" "+sa[9]));
				jb.setCreatedDate(parseDate(sa[11]+" "+sa[12]+" "+sa[13]));
				tasks.add(jb);
			}
		}
		catch(IOException | ParseException p)
		{
			p.printStackTrace();
		}
		finally {
			if(fr!=null)
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return tasks;
	}
	
	public int getPosition(List<JavaBean> l1,String taskName)
	{
		int count=0;
		Iterator<JavaBean> i=l1.iterator();
		JavaBean jb=new JavaBean();
		while(i.hasNext())
		{
			jb=(JavaBean)i.next();
			if(jb.getTaskName().trim().equals(taskName))
				break;
			count++;
		}
		return count;
	}
	public boolean delete(String taskName,String categoryName)		
	    {BufferedReader reader=null;
		BufferedWriter writer=null;
		BufferedWriter writer1=null;
		File outputFile=new File(CONSTANTS.path+"tempFile.txt");
		try {
		File inputFile = new File(CONSTANTS.path+categoryName+".txt");
		File deletedLines=new File("deletedLines.txt");
		reader = new BufferedReader(new FileReader(inputFile));
		writer = new BufferedWriter(new FileWriter(outputFile));
		writer1=new BufferedWriter(new FileWriter(deletedLines,true));
		String currentLine;
		String deletedLine="";
		while((currentLine = reader.readLine()) != null ) {
		    if(currentLine.contains(taskName))
		    	continue;
		    writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer1.write(deletedLine);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {
			if(writer!=null)
		{try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} }
			if(reader!=null) {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(writer1!=null) {
		try {
			writer1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}}}
		File f1=new File(CONSTANTS.path+categoryName+".txt");
		f1.delete();
		outputFile.renameTo(new File(CONSTANTS.path+categoryName+".txt"));
		return true;
	}

public int search(String categoryName, String str)
{
	FileReader fr=null;
	BufferedReader br=null;
	int count=0;
	try {
		String line="";
		fr=new FileReader(CONSTANTS.path+categoryName+".txt");
		br=new BufferedReader(fr);
		while((line=br.readLine())!=null)
		{
			String sa[]=line.split(" ");
			for(int i=0;i<sa.length;i++)
			{
				if(sa[i].contains(str))
				count++;
			}
		}
		
		}
	catch(IOException e) {
		e.printStackTrace();
	}
	finally {
		if(fr!=null)
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if(br!=null)
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	return count;
}
public void searchBySection(List<JavaBean> l1,String str)
{
	Iterator<JavaBean> i=l1.iterator();
	if(i.hasNext())
	{
		JavaBean jb=(JavaBean)i.next();	
		if(jb.getTaskName().contains(str))
			System.out.println(str+" occures in task name :"+jb.getTaskName());
		if(jb.getDescription().contains(str))
			System.out.println(str+" occures in description :"+jb.getTaskName());
		if(jb.getTags().contains(str))
			System.out.println(str+" occures in tags :"+jb.getTaskName());
	}
}
public Date parseDate(String str) throws ParseException {
	String sa[]=str.split(" ");
	String month="";
	if(sa.length==9)
	  month=sa[2];
	if(sa.length==8)
		month=sa[1];
	String monthnum="";
	switch(month)
	{
	case "Jan":
		monthnum="01";
	case "Feb":
		monthnum="02";
	case "Mar":
		monthnum="03";
	case "Apr":
		monthnum="04";
	case "May":
		monthnum="05";
	case "Jun":
		monthnum="06";
	case "Jul":
		monthnum="07";
	case "Aug":
		monthnum="08";
	case "Sep":
		monthnum="09";
	case "Oct":
		monthnum="10";
	case "Nov":
		monthnum="11";
	case "Dec":
		monthnum="12";
	}
	Date dt=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	if(sa.length==9)
	dt=sdf.parse(sa[3]+"/"+monthnum+"/"+sa[8]);
	if(sa.length==8)
		dt=sdf.parse(sa[2]+"/"+monthnum+"/"+sa[7]);
	return dt;

}	
public List<JavaBean> sorting(List<JavaBean> tasks,int ch3)
{
	if(ch3==1)
		Collections.sort(tasks,new Comparator<JavaBean>()
				{
					public int compare(JavaBean o1, JavaBean o2) {
						return o1.getTaskName().trim().compareTo(o2.getTaskName().trim());
					}
				});	
	else if(ch3==2)
		Collections.sort(tasks,new Comparator<JavaBean>()
				{
					public int compare(JavaBean o1, JavaBean o2) {
						return o1.getEndDate().compareTo(o2.getEndDate());
					}
				});
	else if(ch3==3)
		Collections.sort(tasks,new Comparator<JavaBean>() {
			public int compare(JavaBean o1, JavaBean o2) {
				return o1.getCreatedDate().compareTo(o2.getCreatedDate());
			}
		});
	else if(ch3==4)
		Collections.sort(tasks,new SortingByDuration());
	 return tasks;	
	
	}

public List<JavaBean> sorting(int ch3)
{
	File f1=new File(CONSTANTS.path);
	File f2[]=f1.listFiles();
	List<JavaBean> tasks=new ArrayList<JavaBean>();
	String f[]=new String[f2.length];
	for(int i=0;i<f2.length;i++)
		f[i]=f2[i].getName().toString();
	for(int i=0;i<f.length;i++)
		{ 
			f[i]=f[i].replace(".txt","");
			tasks.addAll(this.getTask(f[i]));
		}
	if(ch3==5)
		Collections.sort(tasks,new Comparator<JavaBean>()
				{
					public int compare(JavaBean o1, JavaBean o2) {
						return o1.getTaskName().trim().compareTo(o2.getTaskName().trim());
					}
				});	
	else if(ch3==6)
		Collections.sort(tasks,new Comparator<JavaBean>()
				{
					public int compare(JavaBean o1, JavaBean o2) {
						return o1.getEndDate().compareTo(o2.getEndDate());
					}
				});
	else if(ch3==7)
		Collections.sort(tasks,new Comparator<JavaBean>() {
			public int compare(JavaBean o1, JavaBean o2) {
				return o1.getCreatedDate().compareTo(o2.getCreatedDate());
			}
		});
	else if(ch3==8)
		Collections.sort(tasks,new SortingByDuration());
	 return tasks;	
	
	}
}
	

