package services;

import java.math.BigDecimal;
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
 * Service for products entity managment
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
		
		logger.debug("GetAllProducts was called");
		
		for (Product product : productRepo.findAll()) {
			products.add(ProductMapper.toDTO(product));
		}
		
		return products;
	}

	public Page<Product> findAll(PageRequest request) {
		logger.debug("Info method was called");
		return productRepo.findAll(request);
	}
	
	public Page<Product> findAll(BigDecimal minPrice, BigDecimal maxPrice, PageRequest request) {
		logger.info("findByPriceGreatThenAndLessThan was called min=" + minPrice + ", max=" + maxPrice);
		return productRepo.findByPriceGreatThenAndLessThan(minPrice, maxPrice, request);
	}

	public Product create(Product p) {
		return productRepo.save(p);
	}

	public Page<Product> findAll(BigDecimal minPrice, BigDecimal maxPrice, String startWith, PageRequest request) {
		String makeSearchpatter = startWith + "%";
		
		logger.info("findByPriceGreatThenAndLessThan was called min=" + minPrice + ", max=" + maxPrice + ", startWiht=" + makeSearchpatter);
		return productRepo.findSuperFilter(minPrice, maxPrice, makeSearchpatter, request);
		
	}

	public Page<Product> aaa(String startWith, PageRequest request) {
		String makeSearchpatter = startWith + "%'";
		logger.info("aaa was called startWiht=" + makeSearchpatter);
		return productRepo.findSuperFilter(makeSearchpatter, request);
	}

}
