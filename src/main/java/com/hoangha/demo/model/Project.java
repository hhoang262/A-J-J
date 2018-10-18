package com.hoangha.demo.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Project {

	@Id
	private String id;
	
	private String name;
	
}
