package com.uis.project;
import java.util.*;
import java.text.*;
import java.io.*;
public class StartApp {

	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			InputLogic check=new InputLogic();
			ProjectModel utility = new ProjectModel();
			String categoryName = "",exportPath="",taskName="",desc="",tags="";
			int ch=0,ch1=0,ch2=0,pos = 0;
			Logger.getInstance().log("Starting task manager", 1);
			while (ch <= 0 || ch > 7) {
				System.out.println();
				System.out.println("press 1 to create category");
				System.out.println("press 2 to load category");
				System.out.println("press 3 to delete catergory");
				System.out.println("press 4 to list category");
				System.out.println("press 5 to search category");
				System.out.println("press 6 to export");
				System.out.println("press 7 to exit");
				ch = sc1.nextInt();
			category: switch (ch) {
			case 1: // create category case
				JavaBean jb1=new JavaBean();
				ch=0;
				System.out.println("enter category name");
				categoryName = sc2.nextLine();
				boolean x = check.validateName(categoryName);
				while (x == false) {
					System.out.println("category name must be one word. It cannot contain a numbers,spaces and Alphanumerics.");
					categoryName = sc2.nextLine();
					x = check.checkCategoryExists(categoryName);
				}
				while (check.checkCategoryExists(categoryName) == true) {
					System.out.println("category name already exists. Please provide a unique category name");
					categoryName = sc2.nextLine();
				}
				Logger.getInstance().log("category name created:" + jb1.getCategoryName(), 2);
				while (ch1 <=0|| ch1 >  6) {
					System.out.println();
					System.out.println("press 1 to create task");
					System.out.println("press 2 to update task");
					System.out.println("press 3 to delete task");
					System.out.println("press 4 to list task");
					System.out.println("press 5 to search task");
					System.out.println("press 6 to go back");
					ch1 = sc1.nextInt();
				task: switch (ch1) {
				
				case 1:// create task case.
					JavaBean jb = new JavaBean();
					ch1=0;
					System.out.println("enter task name (must not contain : or .)");
					taskName=sc2.nextLine();
					if(check.isFileEmpty(categoryName)==true)
						taskName=sc2.nextLine();
					else {
					while(check.taskNameExists(taskName, categoryName)==false && check.validString(taskName)==true)
					{
						System.out.println("Invalid String: task name must be unique and cannot contain : or .");
						taskName=sc2.nextLine();
					}
					}
					jb.setTaskName(taskName);
					System.out.println("enter description (must not contain : or .)");
					desc=sc2.nextLine();
					while(check.validString(desc)==true)
					{
						System.out.println("Invalid string : decsription cannot contain : or .");
						desc=sc2.nextLine();
					}
					jb.setDescription(desc);
					System.out.println("enter tags: seperated by commas (must not contain : or .)");
					tags=sc2.nextLine();
					while(check.validString(desc)==true)
					{
						System.out.println("Invalid string : tags cannot contain : or .");
						tags=sc2.nextLine();
					}
					jb.setDescription(tags);
					System.out.println("enter planned end date.(dd/MM/yyyy)");
					jb.setEndDate(sdf.parse(sc2.nextLine()));
					System.out.println(utility.addTask(jb, categoryName));
					Logger.getInstance().log("task created :"+jb.getTaskName(), 2);
					break task;
				case 2: //update task case
					ch1=0;
					System.out.println(categoryName);
					List<JavaBean> a1=utility.getTask(categoryName);
					System.out.println("enter task name to be updated");
					taskName=sc2.nextLine();
					pos=utility.getPosition(a1, taskName);
					JavaBean jb2=a1.get(pos);
					while (ch2<=0||ch2>5) {
						System.out.println();
						System.out.println("press 1 to update task name");
						System.out.println("press 2 to update description");
						System.out.println("press 3 to update tags");
						System.out.println("press 4 to update planned end date");
						System.out.println("press 5 to exit update manager");
						ch2=sc1.nextInt();
					update: switch(ch2)
					{
					case 1:ch2=0;
						System.out.println("enter task name to be updated (must not contain : or .)");
						taskName=sc2.nextLine();
						while(check.taskNameExists(taskName, categoryName)==false && check.validString(taskName)==true)
						{
							System.out.println("Invalid string : task name must be unique and cannot contain : or .");
							taskName=sc2.nextLine();
						}
						jb2.setTaskName(taskName);
						Logger.getInstance().log("task name updated:"+jb2.getTaskName(), 2);
						break update;
					case 2:ch2=0;
						System.out.println("enter description to be updated (must not contain : or .)");
						desc=sc2.nextLine();
						while(check.validString(desc)==true)
						{
							System.out.println("Invalid string : decsription cannot contain : or .");
							desc=sc2.nextLine();
						}
						jb2.setDescription(desc);
						break update;
					case 3: ch2=0; 
						System.out.println("enter tags to be updated (must not contain : or .)");
						tags=sc2.nextLine();
						while(check.validString(tags)==true)
						{
							System.out.println("Invalid string : tags cannot contain : or .");
							tags=sc2.nextLine();
						}
						jb2.setTags(tags);
						break update;
					case 4: ch2=0;
						System.out.println("enter planned end date to be updated dd/MM/yyyy");
						jb2.setEndDate(sdf.parse(sc2.nextLine()));
						break update;
					case 5: ch2=0;
						System.out.println("exiting update manager");
						break update;
					default:ch2=0; 
						System.out.println("option not supported");
						break update;
					}
					}
					utility.delete(taskName,categoryName);
					utility.addTask(jb2, categoryName);
					break task;
				case 3://delete task case
					ch1=0;
					List<JavaBean> a5=utility.getTask(categoryName);
					System.out.println("enter task name to be deleted");
					taskName=sc2.nextLine();
					pos=utility.getPosition(a5, taskName);
					utility.delete(taskName,categoryName);
					break task;
				case 4://list task case 
					ch1=0;
					int ch3=0;
					while(ch3<=0||ch3>5)
					{
						System.out.println();
						System.out.println("press 1 to list tasks by name");
						System.out.println("press 2 to list tasks by due date");
						System.out.println("press 3 to list tasks by created date");
						System.out.println("press 4 to list tasks by longest time");
						System.out.println("press 5 to list all tasks by name");
						System.out.println("press 6 to list all tasks by due date");
						System.out.println("press 7 to list all tasks by created date");
						System.out.println("press 8 to list all tasks by longest time");
						System.out.println("press 9 to go back");
						ch3=sc1.nextInt();
					List<JavaBean> l1=utility.getTask(categoryName);
					List<JavaBean> l2=utility.sorting(l1, ch3);
					List<JavaBean> l3=utility.sorting(ch3);
					Iterator<JavaBean> i1=null;
					if(ch3>0 && ch3<=4)
					 	i1=l2.iterator();
					else if(ch3>4 && ch3<=8)
					 i1=l3.iterator();
					else
						 i1=null;
					JavaBean jb3=null;
					if(i1.hasNext())
					{
						jb3=(JavaBean)i1.next();
						System.out.println("# TaskName = "+jb3.getTaskName() );
						System.out.println("# Description = "+jb3.getDescription());
						System.out.println("# Tags = "+jb3.getTags());
						System.out.println("# Planned end date = "+sdf.format(jb3.getEndDate()));
						System.out.println();
					}
					}
					break task;
				case 5://search task case
					ch1=0;
					System.out.println("enter the string to be searched");
					String str=sc2.nextLine();
					System.out.println("total number of occurences = "+utility.search(categoryName, str));
					List<JavaBean> a4=utility.getTask(categoryName);
					utility.searchBySection(a4, str);
					break task;
				case 6:
					System.out.println("exiting task manager");
					break task;
				default: System.out.println("option not supported");
					break task;
				}
				}
				break category;
			case 2:// load category case 
				ch=0;
				System.out.println("enter category name");
				categoryName=sc2.nextLine();
				while(check.checkCategoryExists(categoryName)==false)
				{
					System.out.println("enter a category name that exists ");
					categoryName=sc2.nextLine();
				}
					while (ch1 <= 0 || ch1 > 6) {
						System.out.println();
						System.out.println("press 1 to create task");
						System.out.println("press 2 to update task");
						System.out.println("press 3 to remove task");
						System.out.println("press 4 to list task");
						System.out.println("press 5 to search task");
						System.out.println("press 6 to go back");
						ch1 = sc1.nextInt();
					task: switch (ch1) {
					case 1: //create task case
						ch1=0;
						JavaBean jb=new JavaBean();
						System.out.println("enter task name. (must not contain : or .)");
						taskName=sc2.nextLine();
						while(check.taskNameExists(taskName, categoryName)==false && check.validString(taskName)==true)
						{
							System.out.println("Invalid string : task name must be unique and cannot contain : or .");
							taskName=sc2.nextLine();
						}
						jb.setTaskName(taskName);
						System.out.println("enter description. (must not contain : or .)");
						desc=sc2.nextLine();
						while(check.validString(desc)==true)
						{
							System.out.println("Invalid string : decsription cannot contain : or .");
							desc=sc2.nextLine();
						}
						jb.setDescription(desc);
						System.out.println("enter tags: seperated by commas (must not contain : or .)");
						tags=sc2.nextLine();
						while(check.validString(tags)==true)
						{
							System.out.println("Invalid string : tags cannot contain : or .");
							desc=sc2.nextLine();
						}
						jb.setDescription(tags);
						System.out.println("enter planned end date.(dd/MM/yyyy)");
						jb.setEndDate(sdf.parse(sc2.nextLine()));
						System.out.println(utility.addTask(jb, categoryName));
						Logger.getInstance().log("task name created :"+jb.getTaskName(), 2);
						break task;
					case 2:// update task case
						ch1=0;
						List<JavaBean> a1=utility.getTask(categoryName);
						System.out.println("enter task name to be updated");
						taskName=sc2.nextLine();
						pos=utility.getPosition(a1, taskName);
						JavaBean jb2=a1.get(pos);
						while (ch2<=0||ch2>5) {
							System.out.println();
							System.out.println("press 1 to update task name");
							System.out.println("press 2 to update description");
							System.out.println("press 3 to update tags");
							System.out.println("press 4 to update planned end date");
							System.out.println("press 5 to exit update manager");
							ch2=sc1.nextInt();
						}
						update: switch(ch2)
						{
						case 1:ch2=0;
							System.out.println("enter task name to be updated (must not contain : or .)");
							taskName=sc2.nextLine();
							while(check.taskNameExists(taskName, categoryName)==false && check.validString(taskName)==true)
							{
								System.out.println("invalid string: task name must be unique and cannot contain : or .");
								taskName=sc2.nextLine();
							}
							jb2.setTaskName(taskName);
							break update;
						case 2:ch2=0;
							System.out.println("enter description to be updated (must not contain : or .)");
							desc=sc2.nextLine();
							while(check.validString(desc)==true)
							{
								System.out.println("Invalid string : decsription cannot contain : or .");
								desc=sc2.nextLine();
							}
							jb2.setDescription(desc);
							break update;
						case 3:ch2=0; 
							System.out.println("enter tags to be updated (must not contain : or .)");
							tags=sc2.nextLine();
							while(check.validString(desc)==true)
							{
								System.out.println("Invalid string : tags cannot contain : or .");
								tags=sc2.nextLine();
							}
							jb2.setDescription(tags);
							break update;
						case 4:ch2=0;
							System.out.println("enter planned end date to be updated");
							jb2.setEndDate(sc2.nextLine());
							break update;
						case 5: ch2=0;
							System.out.println("exiting update manager");
							break update;
						default: System.out.println("option not supported");
							break update;
						}
						utility.delete(taskName,categoryName);
						utility.addTask(jb2, categoryName);
						break task;
					case 3: //delete task case
						ch1=0;
						List<JavaBean> a2=utility.getTask(categoryName);
						System.out.println("enter task name to be deleted");
						taskName=sc2.nextLine();
						pos=utility.getPosition(a2, taskName);
						utility.delete(taskName,categoryName);
						break task;
					case 4: //list task case 
						ch1=0;
						int ch3=0;
						while(ch3<=0||ch3>9)
						{
							System.out.println();
							System.out.println("press 1 to list tasks by name");
							System.out.println("press 2 to list tasks by due date");
							System.out.println("press 3 to list tasks by created date");
							System.out.println("press 4 to list tasks by longest time");
							System.out.println("press 5 to list all tasks by name");
							System.out.println("press 6 to list all tasks by due date");
							System.out.println("press 7 to list all tasks by created date");
							System.out.println("press 8 to list all tasks by longest time");
							System.out.println("press 9 to go back");
							ch3=sc1.nextInt();
						List<JavaBean> l1=utility.getTask(categoryName);
						List<JavaBean> l2=utility.sorting(l1, ch3);
						List<JavaBean> l3=utility.sorting(ch3);
						Iterator<JavaBean> i1=null;
						if(ch3>0 && ch3<=4)
						 	i1=l2.iterator();
						else if(ch3>4 && ch3<=8)
						 i1=l3.iterator();
						else
							 i1=null;
						JavaBean jb3=null;
						while(i1.hasNext())
						{
							jb3=(JavaBean)i1.next();
							System.out.println("# TaskName = "+jb3.getTaskName() );
							System.out.println("# Description = "+jb3.getDescription());
							System.out.println("# Tags = "+jb3.getTags());
							System.out.println("# Planned end date = "+sdf.format(jb3.getEndDate()));
							System.out.println("# task created date = "+sdf.format(jb3.getCreatedDate()));
							System.out.println();
						}
						}
						break task;
					case 5://search task case
						ch1=0;
						System.out.println("enter the string to be searched");
						String str=sc2.nextLine();
						System.out.println("total number of occurences = "+utility.search(categoryName, str));
						List<JavaBean> a3=utility.getTask(categoryName);
						utility.searchBySection(a3, str);	
					break task;	
					case 6:
						System.out.println("exiting task manager");
						break task;
					default:
						System.out.println("option not supported");
						break task;
					}
					}
				break category;
			case 3: // delete category case 
				ch=0;
				System.out.println("enter the category name that should be deleted");
				categoryName=sc2.nextLine();
				File f1=new File(CONSTANTS.path+categoryName+".txt");
				if(f1.delete()==true)
					System.out.println("category deleted successfully");
				else
					System.out.println("error has occured. Enter an existing category name to delete");
				break category;			
			case 4://list category case 
				ch=0;
				File f2=new File(CONSTANTS.path);
				File fl[]=f2.listFiles();
				System.out.println("categories");
				for(int i=0;i<fl.length;i++)
					System.out.println("# "+fl[i].getName());
				break category;
			case 5://search category case
				ch=0;
				System.out.println("enter category to be searched");
				categoryName=sc2.nextLine();
				if(check.checkCategoryExists(categoryName)==true)
					System.out.println("category exists");
				else
					System.out.println("category dosent exist");
				break category;
			case 6: //export category case
				ch=0;
				System.out.println("enter absolute path of the location to be exported");
				exportPath=sc2.nextLine();
				while(check.isValidPath(exportPath)==false)
				{
					System.out.println("enter a valid path that is a directory");
					exportPath=sc2.nextLine();
					
				}
				System.out.println("enter category name to be exported");
				categoryName=sc2.nextLine();
				File export=new File(exportPath);
				export.renameTo(new File(exportPath+categoryName+".txt"));
				break category;
			case 7:
				System.out.println("exiting category manager");
				break category;
			default:
				System.out.println("option not supported");
				break category;
			}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		finally {
			if(sc1!=null)
			sc1.close();
			if(sc2!=null)
			sc2.close();
		}
	}
}
