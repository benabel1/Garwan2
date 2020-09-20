package mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.sun.tools.javac.util.List;

import data.Product;
import dto.ProductDTO;

public class ProductMapperTest {
	
	@Test
	public static void test() {
		ProductDTO dto = new ProductDTO();
		dto.setId(1);
		dto.setName("Test Product Name");
		dto.setPrice(100.0);
		dto.setDecription("Hello, I am teston Mapper");
		
		String link1 = "www.links.com\\links\\products\\1\\picture_01";
		String link2 = "www.links.com\\links\\products\\1\\picture_02";
		
		List<String> list = List.of(link1, link2);
		dto.setGallery(list);
		
		Product product = ProductMapper.code(dto);
		
		assertNotNull(product);
		assertEquals(1L, product.getProductID());
		assertEquals("Test Product Name", product.getName());
		assertEquals(100.0D, product.getPrice());
		assertEquals("Hello, I am teston Mapper", product.getDesription());
		
		assertNotNull(product.getGallery());
		assertEquals(list.size(), product.getGallery().size());
		assertEquals(link1, product.getGallery().get(0));
		assertEquals(link2, product.getGallery().get(1));
	}
	
	@Test
	public static void testForNull() {
		ProductDTO dto = null;
		
		Product product = ProductMapper.code(dto);
		
		assertNull(product);
		
	}

}
