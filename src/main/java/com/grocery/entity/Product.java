package com.grocery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@Column(nullable = false)
	private String productName;
	@Column(nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;
	@Column(nullable = false)
	private double mrp;
	@Column(nullable = false)
	private double sellingPrice;
	@Column(nullable = false)
	private double discountAmount;
	@Column(nullable = false)
	private double finalPrice;
	
	@Column(nullable = false)
	private int totalStock;
	
	@Column(nullable = false)
	private int availableStock;
	
	@Column(nullable = false)
	private int lowStockThreshold;
	
	@Column(nullable = false)
	private boolean sponsored;
	
	@Column(nullable = false)
	private boolean todaySpecial;
	
	@Column(nullable = false)
	private boolean active;

	public Product() {
		
	}

	public Product(String productName, String description, Category category, double mrp,
			double sellingPrice, double discountAmount, double finalPrice, int totalStock, int availableStock,
			int lowStockThreshold, boolean sponsored, boolean todaySpecial, boolean active) {
		

		this.productName = productName;
		this.description = description;
		this.category = category;
		this.mrp = mrp;
		this.sellingPrice = sellingPrice;
		this.discountAmount = discountAmount;
		this.finalPrice = sellingPrice - discountAmount;
		this.totalStock = totalStock;
		this.availableStock = totalStock;
		this.lowStockThreshold = lowStockThreshold;
		this.sponsored = sponsored;
		this.todaySpecial = todaySpecial;
		this.active = active;
	}

	public Long getProductId() {
		return productId;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}
	
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}


	

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public int getLowStockThreshold() {
		return lowStockThreshold;
	}

	public void setLowStockThreshold(int lowStockThreshold) {
		this.lowStockThreshold = lowStockThreshold;
	}

	public boolean isSponsored() {
		return sponsored;
	}

	public void setSponsored(boolean sponsored) {
		this.sponsored = sponsored;
	}

	public boolean isTodaySpecial() {
		return todaySpecial;
	}

	public void setTodaySpecial(boolean todaySpecial) {
		this.todaySpecial = todaySpecial;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}
