package com.nagarro.ImageManagement.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Images")

public class Images implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "imagePath", nullable = false)
	private String imagePath;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "size", nullable = false)
	private double size;
	@Column(name = "userId", nullable = false)
	private int userId;

	public Images() {

	}

	public Images(String imagePath, double size, int userId, String name) {

		this.imagePath = imagePath;
		this.size = size;
		this.userId = userId;
		this.name = name;
	}

	public Images(int id, String imagePath, double size, int userId, String name) {
		this.id = id;
		this.imagePath = imagePath;
		this.size = size;
		this.userId = userId;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
