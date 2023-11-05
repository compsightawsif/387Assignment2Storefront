package com.model;

public class Order {
	private int id;
	private int userId;
	private String orderDate;
	private String status;
	private String trackingNumber;

	public Order(){

	}

	public Order(int id, int userId, String orderDate, String status, String trackingNumber) {
		this.id = id;
		this.userId = userId;
		this.orderDate = orderDate;
		this.status = status;
		this.trackingNumber = trackingNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
}