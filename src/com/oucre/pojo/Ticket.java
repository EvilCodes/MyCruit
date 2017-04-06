package com.oucre.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket", catalog = "mycruit")
public class Ticket {
	private Integer id;
	private String tfrom;
	private String tto;
	private String ttime;

	public Ticket() {
	}
	
	public Ticket(Integer id) {
		this.id = id;
	}

	public Ticket(Integer id, String tfrom, String tto, String ttime) {
		super();
		this.id = id;
		this.tfrom = tfrom;
		this.tto = tto;
		this.ttime = ttime;
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

	@Column(name = "tfrom",length=20)
	public String getTfrom() {
		return tfrom;
	}

	public void setTfrom(String tfrom) {
		this.tfrom = tfrom;
	}

	@Column(name = "tto",length=20)
	public String getTto() {
		return tto;
	}

	public void setTto(String tto) {
		this.tto = tto;
	}

	@Column(name = "ttime",length=20)
	public String getTtime() {
		return ttime;
	}

	public void setTtime(String ttime) {
		this.ttime = ttime;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", tfrom=" + tfrom + ", tto=" + tto + ", ttime=" + ttime + "]";
	}

}
