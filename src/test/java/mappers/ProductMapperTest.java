package mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import data.Product;
import dto.ProductDTO;

/**
 * J4Unit tests for one of Mappers
 * 
 * @author Doma
 *
 */
public class ProductMapperTest {
	
	@Test
	public  void test() {
		ProductDTO dto = new ProductDTO();
		dto.setId(1);
		dto.setName("Test Product Name");
		dto.setPrice(new BigDecimal(100d));
		dto.setDecription("Hello, I am teston Mapper");
		
		String link1 = "www.links.com\\links\\products\\1\\picture_01";
		String link2 = "www.links.com\\links\\products\\1\\picture_02";
		
		List<String> list = List.of(link1, link2);
		dto.setGallery(list);
		
		Product product = ProductMapper.fromDTO(dto);
		
		assertNotNull(product);
		assertEquals(1L, product.getProductID());
		assertEquals("Test Product Name", product.getName());
		assertEquals(new BigDecimal(100.0d), product.getPrice());
		assertEquals("Hello, I am teston Mapper", product.getDesription());
		
		assertNotNull(product.getGallery());
		assertEquals(list.size(), product.getGallery().size());
		assertEquals(link1, product.getGallery().get(0).getUrl());
		assertEquals(link2, product.getGallery().get(1).getUrl());
	}
	
	@Test
	public void testForNull() {
		ProductDTO dto = null;
		
		Product product = ProductMapper.fromDTO(dto);
		
		assertNull(product);
		
	}

}
