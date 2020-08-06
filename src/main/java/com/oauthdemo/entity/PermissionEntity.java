package com.oauthdemo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class PermissionEntity extends BaseIdEntity {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
