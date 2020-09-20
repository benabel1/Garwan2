package repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import data.OrderItem;

@Repository
public interface OrderItemRepositary extends JpaRepository<OrderItem, Long> {

}
