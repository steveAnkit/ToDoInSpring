package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 
 * @author Ankit Sharma
 *
 */

@Entity
@Table(name = "todos")
public class ToDo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Size(min = 10, message = "Enter at least 10 characters..." ) 
	private String description;
	
	private String userName;
	
	private Date targetDate;
	
	private Boolean hide;

	public ToDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToDo(@Size(min = 10, message = "Enter at least 10 characters...") String description, String userName,
			Date targetDate, Boolean hide) {
		super();
		this.description = description;
		this.userName = userName;
		this.targetDate = targetDate;
		this.hide = hide;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Boolean getHide() {
		return hide;
	}

	public void setHide(Boolean hide) {
		this.hide = hide;
	}
	
	
	
	

}
