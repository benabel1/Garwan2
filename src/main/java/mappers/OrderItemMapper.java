package mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import api.privated_api.PrivateAdminController;
import data.OrderItem;
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
		dto.setProduct(ProductMapper.toDTO(item.getProduct()));
		dto.setOrder(OrderMapper.toDTO(item.getOrder()));
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
		item.setProduct(ProductMapper.fromDTO(dto.getProduct()));
		item.setOrder(OrderMapper.fromDTO(dto.getOrderr()));
		item.setCount(dto.getCount());
		item.setPrice(dto.getPrice());

		return item;
	}

}
