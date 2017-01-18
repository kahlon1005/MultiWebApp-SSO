package com.web.example;

public class Car {
	private String id;
	private String brand;
	private Integer year;
	private String color;
	private Integer price;
	private boolean solidState;
	
	public Car(String id, String brand, Integer year, String color,
			Integer price, boolean solidState) {
		this.id = id;
		this.brand = brand;
		this.year = year;
		this.color = color;
		this.price = price;
		this.solidState = solidState;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public boolean isSolidState() {
		return solidState;
	}
	public void setSolidState(boolean solidState) {
		this.solidState = solidState;
	}
	
	
	
}
