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
	 * @param dto
	 * @return
	 */
	public static Product fromDTO(ProductDTO dto) {
		Product product = new Product();
		
		if (dto == null) {
			return null;
		}
		
		product.setProductID(dto.getId());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setAnimalCategory(AnimalCategoryMapper.fromDTO(dto.getAnimalCategoryDTO()));
		product.setDesription(dto.getDecription());
		
		product.setGallery(mapGalleryLinksFromDTO(dto, product));
		
		return product;
	}
	
	
	/**
	 * Transform into DTO product object 
	 * 
	 * @param user
	 * @return
	 */
	public static ProductDTO toDTO(Product product) {
		ProductDTO dto = new ProductDTO();
		
		if (product == null) {
			return null;
		}
		
		dto.setId(product.getProductID());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setAnimalCategoryDTO(AnimalCategoryMapper.toDTO(product.getAnimalCategory()));
		dto.setGallery(mapLinksToDTO(product));
		dto.setDecription(product.getDesription());
		
		return dto;
	}
	private static List<String> mapLinksToDTO(Product product) {
		List<String> urls = new ArrayList<String>();
		
		if(product == null) {
			return urls;
		}
		
		for(Links a : product.getGallery()) {
			if (a != null && a.getUrl()!= null) {
				urls.add(a.getUrl());
			}
		}
		
		return urls;
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
			newLinks.setUrl(string);
			links.add(newLinks);
		}
		
		return links;
	}

	/**
	 * Mapping into ProductOnlyFewColum that is projection only few columns
	 * 
	 * @param product
	 * @return
	 */
	public static ProductOnlyFewColumn mapFor(Product product) {
		return new ProductOnlyFewColumn(ProductMapper.toDTO(product));
	}

}
