package dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import dto.OrderDTO;
import dto.ProductDTO;


public class OrderItemDTO {
	
	long orderItemID;
	
	@NotNull
	private ProductDTO product;
	@NotNull
	private OrderDTO orderr;
	@PositiveOrZero
	private long count;
	@Positive
	private double price;
	
	public long getOrderItemID() {
		return orderItemID;
	}
	public void setOrderItemID(long oooID) {
		this.orderItemID = oooID;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public OrderDTO getOrderr() {
		return orderr;
	}
	public void setOrder(OrderDTO orderr) {
		this.orderr = orderr;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
