package com.oucre.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sendmessage", catalog = "mycruit")
public class SendMessage implements Serializable {
	private Integer id;
	private String gnum;
	private String gname;
	private String message;
	private Timestamp mtime;
	private Integer uid;
	public SendMessage() {

	}

	public SendMessage(Integer id) {
		this.id = id;
	}

	public SendMessage(Integer id, String gnum, String gname, String message, Timestamp mtime,Integer uid) {
		this.id = id;
		this.gnum = gnum;
		this.gname = gname;
		this.message = message;
		this.mtime = mtime;
		this.uid = uid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "gnum", length = 15)
	public String getGnum() {
		return gnum;
	}

	public void setGnum(String gnum) {
		this.gnum = gnum;
	}

	@Column(name = "gname", length = 30)
	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	@Column(name = "message", length = 65535)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "mtime")
	public Timestamp getMtime() {
		return mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	@Column(name = "uid")
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}
