package com.javaguide.task;

import java.time.LocalDate;

public class Task {

	public static enum Status{
		TO_DO,IN_PROGRESS,COMPLETED
	}

	public static enum Priority{
		LOW,MEDIUM,HIGH
	}

	
	private long id;
	private String title;
	private String description;
	private LocalDate due_date;
	private Priority priority;
	private Status status;
	
	
	
	public Task(long id, String title, String description, LocalDate due_date, Priority priority, Status status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.due_date = due_date;
		this.priority = priority;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDue_date() {
		return due_date;
	}
	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ID :" + id + "\nTitle :" + title + "\nDescription :" + description + "\nDue_date :" + due_date
				+ "\nPriority :" + priority + "\nStatus :" + status + "\n";
	}
	
	
	

}

