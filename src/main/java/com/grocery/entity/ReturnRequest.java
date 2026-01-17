package com.grocery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "return_requests")
public class ReturnRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long returnRequestsId;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@Column(nullable = false)
	private String reason;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private LocalDateTime requestDate;

	public ReturnRequest() {
		this.requestDate=LocalDateTime.now();
	}

	public ReturnRequest(Order order, String reason, String status) {
		super();
		this.order = order;
		this.reason = reason;
		this.status = status;
		this.requestDate = LocalDateTime.now();
	}

	public Long getReturnRequestsId() {
		return returnRequestsId;
	}


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}


	
	
	
	

}
