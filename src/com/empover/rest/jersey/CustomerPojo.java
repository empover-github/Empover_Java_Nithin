package com.empover.rest.jersey;

public class CustomerPojo {
	
	private String id;
	private String orderQuantity;
	private String orderCreatedDate;
	private Boolean dispatched;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getOrderCreatedDate() {
		return orderCreatedDate;
	}
	public void setOrderCreatedDate(String orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}
	public Boolean getDispatched() {
		return dispatched;
	}
	public void setDispatched(Boolean dispatched) {
		this.dispatched = dispatched;
	}
	@Override
	public String toString() {
		return "CustomerPojo [id=" + id + ", orderQuantity=" + orderQuantity + ", orderCreatedDate=" + orderCreatedDate
				+ ", dispatched=" + dispatched + "]";
	}
	
}