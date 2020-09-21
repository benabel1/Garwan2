package mappers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import data.Links;
import data.Product;
import dto.ProductDTO;
import dto.ProductOnlyFewColumn;

@Component
public class ProductMapper {
	
	static Logger logger = LoggerFactory.getLogger(ProductMapper.class);

	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	public static Product fromDTO(ProductDTO userDTO) {
		Product product = new Product();
		
		product.setPrice(userDTO.getPrice());
		product.setAnimalCategory(AnimalCategoryMapper.fromDTO(userDTO.getAnimalCategoryDTO()));
		product.setDesription(userDTO.getDecription());
		
		product.setGallery(mapGalleryLinksFromDTO(userDTO, product));
		
		return product;
	}
	
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public static ProductDTO toDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		
		dto.setId(product.getProductID());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setAnimalCategoryDTO(AnimalCategoryMapper.toDTO(product.getAnimalCategory()));
		dto.setDecription(product.getDesription());
		
		return dto;
	}
	/**
	 * Mapping product links
	 * 
	 * @param userDTO
	 * @param product
	 * @return
	 */
	private static List<Links> mapGalleryLinksFromDTO(ProductDTO userDTO, Product product) {
		List<Links> links = new ArrayList<Links>();
		
		if (userDTO == null) {
			return links;
		}
		
		if (userDTO.getGallery() == null) {
			return null;
		}
		
		for (String string: userDTO.getGallery()) {
			
			if (string == null || string.isEmpty()) {
				continue;
			}
			
			Links newLinks = new Links();
			newLinks.setProduct_image(product);
			links.add(newLinks);
		}
		
		return links;
	}


	public static ProductOnlyFewColumn mapFor(Product product) {
		
		return new ProductOnlyFewColumn(ProductMapper.toDTO(product));
	}

}
