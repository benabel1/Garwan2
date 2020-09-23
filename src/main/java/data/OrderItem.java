package data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Entity
@Table(name = "order_item")
public class OrderItem {

	@Id
	@Column(name = "order_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_ID_SEQ")
//	@SequenceGenerator(sequenceName = "ORDER_ITEM_ID_SEQ", allocationSize = 1, name = "ORDER_ITEM_ID_SEQ", initialValue = 1)
	private long order_item_ID;
	
	@ManyToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	@NotNull
	private Product product;
	
	@ManyToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	@NotNull
	private Order order;
	
	@Column
	@PositiveOrZero
	private long count;
	
	@Column
	@Positive
	private double price;

	public long getOrderItemID() {
		return order_item_ID;
	}

	public void setOooID(long oooID) {
		this.order_item_ID = oooID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
