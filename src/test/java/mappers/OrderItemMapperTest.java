package mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import data.OrderItem;
import dto.OrderItemDTO;


/**
 * J4Unit tests for one of Mappers
 * 
 * @author Doma
 */
public class OrderItemMapperTest {
	
	@Test
	public void test() {
		OrderItemDTO dto = new OrderItemDTO();
		dto.setOrderItemID(1L);
		dto.setOrder(10l);
		dto.setProduct(100l);
		dto.setCount(20);
		dto.setPrice(50.0D);
		
		OrderItem item = OrderItemMapper.fromDTO(dto);
		
		assertNotNull(item);
		assertEquals(1L, item.getOrderItemID());
		assertNull(item.getOrder());
		assertNull(item.getProduct());
		assertEquals(20, item.getCount());
		assertEquals(50.0D, item.getPrice());
	}
	
	@Test
	public void testNullOrderDTOToEntityOrder() {
		OrderItemDTO dto = null;
		
		OrderItem item = OrderItemMapper.fromDTO(dto);
		assertNull(item);
		
	}

}
