package com.oucre.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role", catalog = "mycruit")
public class Role implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String groups;

	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String name) {
		this.name = name;
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

	@Column(name = "name", length = 10)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role(Integer id) {
		super();
		this.id = id;
	}

	@Column(name = "groups")
	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public Role(Integer id, String name, String groups) {
		super();
		this.id = id;
		this.name = name;
		this.groups = groups;
	}

}