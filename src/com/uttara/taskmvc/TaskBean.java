package com.uttara.taskmvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TaskBean implements Comparable<TaskBean> {

	String taskname;
	String description;
	String status;
	int priority;
	Date createdDate;
	Date dueDate;
	String tags;
	
	SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
	
	public TaskBean( String taskname , String description , String status ,  int priority , Date createdDate , Date dueDate , String tags ) {
		
		super();
		
		this.taskname     = taskname;
		this.description  = description;
		this.status       = status;
		this.priority     = priority;
		this.createdDate  = createdDate;
		this.dueDate      = dueDate;
		this.tags         = tags;
		
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname( String taskname ) {
		this.taskname = taskname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus( String status ) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority( int priority ) {
		this.priority = priority;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate( Date createdDate ) {
		this.createdDate = createdDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate( Date dueDate ) {
		this.dueDate = dueDate;
	}

	public String getTags() {
		return tags;
	}

	public void setTags( String tags ) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		
		return taskname + ":" + description + ":" + status + ":" + priority + ":" + sdf.format( createdDate ) + ":" + sdf.format( dueDate ) + ":" + tags;
	
	}

	@Override
	public int hashCode() {
		return Objects.hash( createdDate, description, dueDate, priority, sdf, status, tags, taskname );
	}

	@Override
	public boolean equals( Object obj ) {
		
		if ( this == obj )
			return true;
		
		if ( obj == null )
			return false;
		
		if ( getClass() != obj.getClass() )
			return false;
		
		TaskBean other = ( TaskBean ) obj;
		
		return Objects.equals( createdDate, other.createdDate ) && Objects.equals( description, other.description )
				&& Objects.equals( dueDate, other.dueDate ) && priority == other.priority
				&& Objects.equals( sdf, other.sdf ) && Objects.equals( status, other.status )
				&& Objects.equals( tags, other.tags ) && Objects.equals( taskname, other.taskname );
	}

	public int compareTo( TaskBean o ) {
		
		return this.toString().compareTo( o.toString() );
	
	}
}
