package mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import api.privated_api.PrivateAdminController;
import data.Order;
import data.OrderItem;
import dto.OrderDTO;
import dto.OrderItemDTO;

@Component
public class OrderItemMapper {
	
	static Logger logger = LoggerFactory.getLogger(PrivateAdminController.class);

	public static OrderItemDTO toDTO(OrderItem item) {
		OrderItemDTO dto = new OrderItemDTO();
		
		if(item == null) {
			logger.info("OrderItem was NULL at start of mapping!");
			return null;
		}
		
		dto.setOrderItemID(item.getOrderItemID());
		dto.setProduct(getProduct(item));
		dto.setOrder(getOrder(item));
		dto.setCount(item.getCount());
		dto.setPrice(item.getPrice());

		return dto ;
	}
	
	private static long getOrder(OrderItem item) {
		return (item != null)? item.getOrder().getOrderId(): -1;
	}

	private static long getProduct(OrderItem item) {
		return (item != null)? item.getOrderItemID(): -1; 
	}

	public static OrderItemDTO toDTO(OrderItem item, OrderDTO orderDTO) {
		OrderItemDTO dto = new OrderItemDTO();
		
		if(item == null) {
			logger.info("OrderItem was NULL at start of mapping!");
			return null;
		}
		
		dto.setOrderItemID(item.getOrderItemID());
		dto.setProduct(getProduct(item));
		dto.setOrder(getOrder(item));
		dto.setCount(item.getCount());
		dto.setPrice(item.getPrice());

		return dto ;
	}

	public static OrderItem fromDTO(OrderItemDTO dto) {
		OrderItem item = new OrderItem();
		
		if (dto == null) {
			return null;
		}
		
		item.setOooID(dto.getOrderItemID());
		//remove cycle dependecy
		item.setProduct(null);
		//remove cycle dependecy
		item.setOrder(null);
		item.setCount(dto.getCount());
		item.setPrice(dto.getPrice());

		return item;
	}
	
	public static OrderItem fromDTO(OrderItemDTO dto, Order order) {
		OrderItem item = new OrderItem();
		
		if (dto == null) {
			return null;
		}
		
		item.setOooID(dto.getOrderItemID());
		item.setProduct(null);
		item.setOrder(order);
		item.setCount(dto.getCount());
		item.setPrice(dto.getPrice());

		return item;
	}

}
