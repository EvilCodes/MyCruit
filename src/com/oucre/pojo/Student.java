package com.oucre.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student", catalog = "mycruit")
public class Student {
	private Integer id;
	private String name;
	private String qq;
	private String mobile;
	private Integer uid;
	private Integer cuid;
	private String conver;
	private String status;
	private Integer days;
	

	public Student() {
	}
	
	public Student(Integer id) {
		this.id = id;
	}
	

	public Student(Integer id, String name, String qq, String mobile, Integer uid,String status) {
		super();
		this.id = id;
		this.name = name;
		this.qq = qq;
		this.mobile = mobile;
		this.uid = uid;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name",length=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "qq",length=20)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "mobile",length=20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "uid")
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "cuid")
	public Integer getCuid() {
		return cuid;
	}

	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}

	@Column(name = "conver", length = 1)
	public String getConver() {
		return conver;
	}

	public void setConver(String conver) {
		this.conver = conver;
	}

	@Column(name = "days")
	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", qq=" + qq + ", mobile=" + mobile + ", uid=" + uid + ", cuid="
				+ cuid + ", conver=" + conver + ", status=" + status + ", days=" + days + "]";
	}

}
