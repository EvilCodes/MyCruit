package com.oucre.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Announcement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "announcement", catalog = "mycruit")
public class Announcement implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private String type;
	private Date subdate;
	private Date upddate;
	private Integer eid;

	// Constructors

	/** default constructor */
	public Announcement() {
	}

	/** minimal constructor */
	public Announcement(Integer id) {
		this.id = id;
	}

	
	
	public Announcement(Integer id, String title, String type, Date subdate, Date upddate) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.subdate = subdate;
		this.upddate = upddate;
	}

	/** full constructor */
	public Announcement(Integer id, String title, String content, String type, Date subdate, Date upddate, Integer eid) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.type = type;
		this.subdate = subdate;
		this.upddate = upddate;
		this.eid = eid;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "type", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "subdate", length = 0)
	public Date getSubdate() {
		return this.subdate;
	}

	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "upddate", length = 0)
	public Date getUpddate() {
		return this.upddate;
	}

	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}

	@Column(name = "eid")
	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

}