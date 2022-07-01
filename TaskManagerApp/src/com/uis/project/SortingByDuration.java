package com.uis.project;
import java.util.*;
public class SortingByDuration implements Comparator<JavaBean> {

	public int compare(JavaBean j1, JavaBean j2) {
		
		Date d1=j1.getCreatedDate();
		Date d2=j1.getEndDate();
		long differenceInMs1=d1.getTime()-d2.getTime();
		long differenceInDays1 = (differenceInMs1 / (1000 * 60 * 60 * 24)) % 365;
		Date d3=j1.getCreatedDate();
		Date d4=j1.getEndDate();
		long differenceInMs2=d3.getTime()-d4.getTime();
		long differenceInDays2 = (differenceInMs2 / (1000 * 60 * 60 * 24)) % 365;
		int duration=(int)(differenceInDays1-differenceInDays2);
		return duration;
	}

}
