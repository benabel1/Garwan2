package repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import data.Garwan_User;
import data.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByUserr(Garwan_User userr);

}
