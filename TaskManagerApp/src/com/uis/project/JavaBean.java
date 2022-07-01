package com.uis.project;
import java.util.*;
public class JavaBean  {
	private String categoryName;
	private String taskName;
	private String description;
	private String tags;
	private Date createdDate;
	private Date endDate;
	private String date;
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate=createdDate;
	}
	public Date getCreatedDate()
	{
		return createdDate;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setEndDate(String str)
	{
		this.date=str;
	}
	public String getDate() {
		return this.date;
	}
	public int hashCode() {
	return this.toString().hashCode();
	}
	@Override
	public String toString() {
		return "JavaBean [categoryName=" + categoryName + ", taskName=" + taskName + ", description=" + description
				+ ", tags=" + tags + ", createdDate=" + createdDate.toString() + ", endDate=" + endDate.toString() + ", date=" + date + "]";
	}
	public boolean equals(Object obj) {
		if(obj instanceof JavaBean)
		{JavaBean other = (JavaBean) obj;
	       if (categoryName.equals(other.categoryName) && (createdDate.equals(other.createdDate))
				&& (date.equals(other.date)) && (description.equals(other.description))
				&& (endDate.equals(other.endDate)) && (tags.equals(other.tags))
				&& (taskName.equals(other.taskName)) )
		return true;
		}
		return false;
	}
	
}