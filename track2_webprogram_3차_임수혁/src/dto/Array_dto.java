package dto;

public class Array_dto {
	String orderNo, productName,amount, orderName, orderDate;

	public Array_dto(String orderNo, String productName, String amount, String orderName, String orderDate) {
		super();
		this.orderNo = orderNo;
		this.productName = productName;
		this.amount = amount;
		this.orderName = orderName;
		this.orderDate = orderDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public String getAmount() {
		return amount;
	}

	public String getOrderName() {
		return orderName;
	}

	public String getOrderDate() {
		return orderDate;
	}
	
}
