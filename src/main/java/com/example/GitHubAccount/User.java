package com.example.GitHubAccount;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, message = "Name must be at least 2 characters long.")
	private String name;

	@Min(value = 1, message = "Contributers must be at least 1.")
	private int contributers = 1;

	@Min(value = 1, message = "Forks must be at least 1.")
	private int forks = 1;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setContributers(int contributers){
		this.contributers = contributers;
	}

	public int getContributers(){
		return this.contributers;
	}

	public void setForks(int forks){
		this.forks = forks;
	}

	public int getForks(){
		return this.forks;
	}

}
