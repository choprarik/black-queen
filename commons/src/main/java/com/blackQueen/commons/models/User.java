package com.blackQueen.commons.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document(collection = "user")
public class User {
	
	@Id
	private String id = "";
	@NonNull
	private String name = "";
	@NonNull
	private String email =  "";
	private int age = 0;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * 
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
}
