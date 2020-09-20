package mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import data.OrderItem;
import dto.OrderDTO;
import dto.OrderItemDTO;
import dto.ProductDTO;

public class OrderItemMapperTest {
	
	@Test
	public void test() {
		OrderItemDTO dto = new OrderItemDTO();
		dto.setOrderItemID(1L);
		dto.setOrder(new OrderDTO());
		dto.setProduct(new ProductDTO());
		dto.setCount(20);
		dto.setPrice(50.0D);
		
		OrderItem item = OrderItemMapper.code(dto);
		
		assertNotNull(item);
		assertEquals(1L, item.getOrderItemID());
		assertNotNull(item.getOrder());
		assertNotNull(item.getProduct());
		assertEquals(20, item.getCount());
		assertEquals(50.0D, item.getPrice());
	}
	
	@Test
	public void testForNull() {
		OrderItemDTO dto = null;
		
		OrderItem item = OrderItemMapper.code(dto);
		
		assertNull(item);
		
	}

}
