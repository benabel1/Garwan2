package dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class OrderItemDTO {
	
	long orderItemID;
	
	@NotNull
	private long product;
	@NotNull
	private long orderrId;
	@PositiveOrZero
	private long count;
	@Positive
	private double price;	
	
	public OrderItemDTO() {
		super();
	}
	
	
	public OrderItemDTO(long orderItemID, @NotNull long product, @NotNull long orderrId, @PositiveOrZero long count, @Positive double price) {
		super();
		this.orderItemID = orderItemID;
		this.product = product;
		this.orderrId = orderrId;
		this.count = count;
		this.price = price;
	}

	public void setOrderrId(long orderrId) {
		this.orderrId = orderrId;
	}

	public long getOrderItemID() {
		return orderItemID;
	}
	public void setOrderItemID(long oooID) {
		this.orderItemID = oooID;
	}
	public long getProduct() {
		return product;
	}
	public void setProduct(long product) {
		this.product = product;
	}

	public long getOrderrId() {
		return orderrId;
	}
	public void setOrder(long orderrId) {
		this.orderrId = orderrId;
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
