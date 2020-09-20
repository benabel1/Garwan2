package data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "order_table")
public class Order {
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private long orderId;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> list;
	
	@ManyToOne
	@JoinColumn(name = "userr")
	private Garwan_User userr;

	@Column(name = "created")
	private LocalDateTime time;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long id) {
		this.orderId = id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public Garwan_User getUserr() {
		return userr;
	}

	public void setUserr(Garwan_User user) {
		this.userr = user;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderItem> getList() {
		return list;
	}

	public void setList(List<OrderItem> list) {
		this.list = list;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
}
