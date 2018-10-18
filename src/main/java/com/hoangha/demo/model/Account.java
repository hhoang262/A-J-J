package com.hoangha.demo.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import lombok.Data;

@Document(indexName = "huyhoang", type = "accounts")
@Data
public class Account {
	
	@Id
	private String id;
	private String title;
	private String name;
	@Field(type = FieldType.Object, includeInParent = true)
	private List<Project> projects;
	
	public Account(String id, String name, String title, List<Project> projects) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.projects = projects;
	}
}
