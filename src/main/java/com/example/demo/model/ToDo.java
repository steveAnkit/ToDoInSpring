package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 
 * @author Ankit Sharma
 *
 */

@Entity
@Table(name = "todos")
public class ToDo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Size(min = 10, message = "Enter at least 10 characters...")
	private String description;

	private Date targetDate;

	private Boolean hide;

	private Date creationDate;

	private int creatorId;

	private int completedBy;
	
	@ManyToOne
	private User user;

	public ToDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToDo(long id, @Size(min = 10, message = "Enter at least 10 characters...") String description,
			String userName, Date targetDate, Boolean hide, Date creationdate, int creatorId, int completedBy) {
		super();
		this.id = id;
		this.description = description;
		this.targetDate = targetDate;
		this.hide = hide;
		this.creationDate = creationdate;
		this.creatorId = creatorId;
		this.completedBy = completedBy;
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

	public Date getCreationdate() {
		return creationDate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationDate = creationdate;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public int getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(int completedBy) {
		this.completedBy = completedBy;
	}

}
