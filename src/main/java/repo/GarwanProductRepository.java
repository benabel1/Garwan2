package repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import data.Product;

@Repository
public interface GarwanProductRepository extends JpaRepository<Product, Long> {
	
	Page<Product> findAll(Pageable page);
	
	@Query("select p from Product p where p.price >= :minPrice and p.price <= :maxPrice	")
	Page<Product> findByPriceGreatThenAndLessThan(@Param(value = "minPrice") double minPrice, @Param(value = "maxPrice") double maxPrice, Pageable page);

}
