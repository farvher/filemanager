package com.farvher.filemanager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private String id;

	@Column(nullable = false)
	private String user;
	
	@Column(nullable = false)
	private String pass;

	
	public User (String user , String pass){
		this.user=user;
		this.pass = pass;
	}
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
}
