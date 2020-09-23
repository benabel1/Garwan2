package mappers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import data.Order;
import data.OrderItem;
import dto.OrderDTO;
import dto.OrderItemDTO;

/**
 * Mapper for tranforming DTO into Entity class
 * 
 * @author Doma
 *
 */
@Component
public class OrderMapper {
	
	static Logger logger = LoggerFactory.getLogger(OrderMapper.class);

	/**
	 * Transfor Order object on OrderDTO object
	 * 
	 * @param order
	 * @return
	 */
	public static OrderDTO toDTO(Order order) {
		OrderDTO dto = new OrderDTO();

		if (order == null) {
			//TODO maybe change level to DEBUG rather
			logger.info("Order was NULL at start of mappig!");
			return null;
		}

		dto.setOrderId(order.getOrderId());
		dto.setTotalPrice(order.getTotalPrice());
		dto.setCreatedDate(order.getTime());
		dto.setUser(getUserId(order));
		
		if (order.getList() == null) {
			dto.setList(null);
		} else {
			dto.setList(new ArrayList<OrderItemDTO>());
			
			for (OrderItem item: order.getList()) {
				if(item != null) {
					dto.getList().add(OrderItemMapper.toDTO(item, dto));
				}
			}
		}

		return dto;
	}

	private static String getUserId(Order order) {
		return (order != null)? order.getUserr().getUsername(): "";
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public static Order fromDTO(OrderDTO dto) {
		Order order = new Order();

		if (dto == null) {
			logger.info("OrderDTo was NULL at start of mapping");
			return null;
		}

		order.setOrderId(dto.getOrderId());
		order.setTotalPrice(dto.getTotalPrice());
		order.setTime(dto.getCreatedDate());
		order.setUserr(null);
		
		if(dto.getList() == null) {
			order.setList(null);
		} else {
			order.setList(new ArrayList<OrderItem>());
			
			for (OrderItemDTO itemDto: dto.getList()) {
				if(itemDto != null) {
					order.getList().add(OrderItemMapper.fromDTO(itemDto, order));
				}
			}
		}

		return order;
	}

}
