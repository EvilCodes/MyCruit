package com.oucre.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Resource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "resource", catalog = "mycruit")
public class Resource implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String link;
	private String status;
	private Integer orderby;
	private String level;
	private Integer superid;

	
	
	// Constructors

	/** default constructor */
	public Resource() {
	}

	public Resource(Integer id) {
		super();
		this.id = id;
	}

	/** full constructor */
	public Resource(String name, String link, String status, Integer orderby,
			String level, Integer superid) {
		this.name = name;
		this.link = link;
		this.status = status;
		this.orderby = orderby;
		this.level = level;
		this.superid = superid;
	}
	
	

	public Resource(Integer id, String name, String link, String status,
			Integer orderby, String level, Integer superid) {
		super();
		this.id = id;
		this.name = name;
		this.link = link;
		this.status = status;
		this.orderby = orderby;
		this.level = level;
		this.superid = superid;
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

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "link", length = 100)
	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "orderby")
	public Integer getOrderby() {
		return this.orderby;
	}

	public void setOrderby(Integer orderby) {
		this.orderby = orderby;
	}

	@Column(name = "level", length = 1)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "superid"/*, nullable = false*/)
	public Integer getSuperid() {
		return this.superid;
	}

	public void setSuperid(Integer superid) {
		this.superid = superid;
	}

}