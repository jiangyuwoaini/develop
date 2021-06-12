package com.jiangyu.pojo;
/**  
 * @ClassName: Order
 * @Description: 订单实体类 简易版 真正的订单比这复杂多了
 * @author Jy
 * @date 2021-01-31 12:03:16 
*/  
public class Order {
	private long id;
	private String orderNo;
	private String address;
	private float price;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNo=" + orderNo + ", address=" + address + ", price=" + price + "]";
	}
}
