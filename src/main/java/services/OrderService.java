package services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.Garwan_User;
import data.OperationResult;
import data.Order;
import dto.OrderDTO;
import mappers.OrderMapper;
import repo.OrderRepository;

@Service
public class OrderService {
	
	Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	OrderRepository orderRepositary;

	public List<OrderDTO> getAllOrders() {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		
		logger.info("OrderSevice getAllOders was called.");
		
		try {
			for (Order o : orderRepositary.findAll()) {
				orders.add(OrderMapper.decode(o));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return orders;
	}

	public OperationResult<Order, OrderService> createOrder(OrderDTO dto, Garwan_User user) {
		
		Order order = OrderMapper.code(dto);
		
		if(order == null || user == null) {
			return new OperationResult<Order, OrderService>(order, false, false, this);
		}
		
		order.setUserr(user);
		order = orderRepositary.save(order);
		
		return new OperationResult<Order, OrderService>(order, true, false, this);
	}

	public List<OrderDTO> getAllOrdersByUser(Garwan_User user) {
		List<OrderDTO> ordersByUser = new ArrayList<OrderDTO>();
		
		List<Order> userOrders = orderRepositary.findAllByUserr(user);
		
		for (Order order : userOrders) {
			if(order != null) {
				ordersByUser.add(OrderMapper.decode(order));
			}
		}
		
		return ordersByUser;
	}

}
