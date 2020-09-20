package dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;


public class OrderDTO {
	
	private long orderId;
	@NotNull
	@Positive
	private double totalPrice;
	@NotNull
	private UserDTO user;
	private List<OrderItemDTO> list;
	
//	Date time;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime createdDate;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public List<OrderItemDTO> getList() {
		return list;
	}
	public void setList(List<OrderItemDTO> list) {
		this.list = list;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
