package services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import data.Product;
import dto.ProductDTO;
import mappers.ProductMapper;
import repo.GarwanProductRepository;

/**
 * Service for managing products
 * 
 * @author Doma
 *
 */
@Service
public class ProductService {
	
	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	GarwanProductRepository productRepo;

	public ProductDTO getDetails(ProductDTO product) {
		return new ProductDTO();
	}

	public ProductDTO getProductById(Long id) {
		return ProductMapper.toDTO(productRepo.findById(id).orElseGet(null));
	}

	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		
		for (Product product : productRepo.findAll()) {
			products.add(ProductMapper.toDTO(product));
		}
		
		return products;
	}

	public Page<Product> findAll(PageRequest request) {
		return productRepo.findAll(request);
	}
	
	public Page<Product> findAll(double minPrice, double maxPrice, PageRequest request) {
		return productRepo.findByPriceGreatThenAndLessThan(minPrice, maxPrice, request);
	}

	public Product create(Product p) {
		return productRepo.save(p);
	}

}
