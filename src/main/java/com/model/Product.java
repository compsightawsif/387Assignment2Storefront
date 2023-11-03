package com.model;

public class Product {
	private int id;
	private String name;
	private String description;
	private String vendor;
	private String urlslug;
	private String sku;
	private double price;

	public Product() {
	}

	public Product(int id, String name, String description, String vendor, String urlslug, String sku, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.vendor = vendor;
		this.urlslug = urlslug;
		this.sku = sku;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getUrlslug() {
		return urlslug;
	}

	public void setUrlslug(String urlslug) {
		this.urlslug = urlslug;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}