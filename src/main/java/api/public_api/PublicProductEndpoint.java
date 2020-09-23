package api.public_api;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.Product;
import dto.ProductDTO;
import dto.ProductOnlyFewColumn;
import mappers.ProductMapper;
import services.ProductService;

@RestController
@RequestMapping(value = "/public/products")
public class PublicProductEndpoint {
	
	Logger logger = LoggerFactory.getLogger(PublicProductEndpoint.class);
	
	@Autowired
	ProductService service;

	@GetMapping(value = "details")
	public ProductDTO getDetails(@RequestParam("id") Long id) {
		return service.getProductById(id);
	}
	
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ProductOnlyFewColumn> allProducts(
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("direction") Optional<Direction> direction) {
		
		//creating page request for column price
		PageRequest request = PageRequest.of(
				page.orElse(0), 
				pageSize.orElse(10), 
				direction.orElse(Direction.ASC), 
				Product.PRICE_COLUMN_NAME);
		
		Page<Product> allProducts = service.findAll(request);
		Page<ProductOnlyFewColumn> aa = allProducts.map(p -> ProductMapper.mapFor(p));
		
		return aa;
	}
	
	@GetMapping(value = "filterMinMax",produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ProductOnlyFewColumn> allProductsWithPriceInRangeMinMax(
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("minPrice") Optional<BigDecimal> minPrice,
			@RequestParam("maxPrice") Optional<BigDecimal> maxPrice) {
		
		//creating page request for column price
		PageRequest request = PageRequest.of(
				page.orElse(0), 
				pageSize.orElse(10), 
				Direction.DESC, 
				Product.PRICE_COLUMN_NAME);
		
		//mapping to hide some columns in page content
		Page<Product> allProductsWithPriceInRange = service.findAll(minPrice.orElse(new BigDecimal(0d)), maxPrice.orElse(new BigDecimal(10000d)), request);
		Page<ProductOnlyFewColumn> resultWithHiddenColumns = allProductsWithPriceInRange.map(p -> ProductMapper.mapFor(p));
		
		return resultWithHiddenColumns;
	}
	
	@GetMapping(value = "filterMinMaxAndNameStart",produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ProductOnlyFewColumn> allProductsWithPriceInRangeMinMaxAndNAme(
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("minPrice") Optional<BigDecimal> minPrice,
			@RequestParam("maxPrice") Optional<BigDecimal> maxPrice,
			@RequestParam("startWith") Optional<String> startWith) {
		
		//creating page request for column price
		PageRequest request = PageRequest.of(
				page.orElse(0), 
				pageSize.orElse(10), 
				Direction.DESC, 
				Product.PRICE_COLUMN_NAME);
		
		//mapping to hide some columns in page content
		Page<Product> allProductsWithPriceInRange = service.findAll(minPrice.orElse(new BigDecimal(0d)), maxPrice.orElse(new BigDecimal(10000d)), startWith.orElse(""), request);
		Page<ProductOnlyFewColumn> resultWithHiddenColumns = allProductsWithPriceInRange.map(p -> ProductMapper.mapFor(p));
		
		return resultWithHiddenColumns;
	}
	
	@GetMapping(value = "NameStart",produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ProductOnlyFewColumn> allProductsNAme(
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("startWith") Optional<String> startWith) {
		
		//creating page request for column price
		PageRequest request = PageRequest.of(
				page.orElse(0), 
				pageSize.orElse(10), 
				Direction.DESC, 
				Product.PRICE_COLUMN_NAME);
		
		//mapping to hide some columns in page content
		Page<Product> allProductsWithPriceInRange = service.aaa(startWith.orElse(""), request);
		Page<ProductOnlyFewColumn> resultWithHiddenColumns = allProductsWithPriceInRange.map(p -> ProductMapper.mapFor(p));
		
		return resultWithHiddenColumns;
	}
	
	@GetMapping(value = "info", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> info() {
		return new ResponseEntity<String>(this + " is running", HttpStatus.OK);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
		return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
