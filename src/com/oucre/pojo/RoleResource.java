package com.oucre.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RoleResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role_resource", catalog = "mycruit")
public class RoleResource implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleid;
	private Integer resourceid;

	// Constructors

	/** default constructor */
	public RoleResource() {
	}

	/** full constructor */
	public RoleResource(Integer roleid, Integer resourceid) {
		this.roleid = roleid;
		this.resourceid = resourceid;
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

	@Column(name = "roleid", nullable = false)
	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Column(name = "resourceid", nullable = false)
	public Integer getResourceid() {
		return this.resourceid;
	}

	public void setResourceid(Integer resourceid) {
		this.resourceid = resourceid;
	}

	public RoleResource(Integer id) {
		this.id = id;
	}
	
	

}