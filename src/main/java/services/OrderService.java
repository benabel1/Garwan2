package services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.Garwan_User;
import data.OperationResult;
import data.Order;
import data.OrderItem;
import data.Product;
import dto.OrderDTO;
import dto.OrderItemDTO;
import mappers.OrderItemMapper;
import mappers.OrderMapper;
import repo.GarwanProductRepository;
import repo.OrderItemRepositary;
import repo.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	OrderRepository orderRepositary;
	
	@Autowired
	OrderItemRepositary orderItemRepositary;
	
	@Autowired
	GarwanProductRepository productRepository;

	public List<OrderDTO> getAllOrders() {
		List<OrderDTO> orders = new ArrayList<OrderDTO>();
		
		logger.info("OrderSevice getAllOders was called.");
		
		try {
			for (Order o : orderRepositary.findAll()) {
				orders.add(OrderMapper.toDTO(o));
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return orders;
	}

		
	public OperationResult<Order, OrderService> createOrderForUser(OrderDTO dto, Garwan_User user) {
		List<OrderItem> result = new ArrayList<OrderItem>();
		Order order = OrderMapper.fromDTO(dto);
		
		if(order == null || user == null) {
			return new OperationResult<Order, OrderService>(order, false, false, this);
		}
		
		order.setList(result);
		order.setUserr(user);
		order.setTime(LocalDateTime.now());
		order = orderRepositary.save(order);
		
		for (OrderItemDTO a : dto.getList()) {
			OrderItem orderItem = OrderItemMapper.fromDTO(a, order);
			Product product = productRepository.findById(a.getProduct()).orElse(null);
			orderItem.setProduct(product);
			result.add(orderItem);
			orderItemRepositary.save(orderItem);
		}
	
		return new OperationResult<Order, OrderService>(order, true, false, this);
	}

	public List<OrderDTO> getAllOrdersByUser(Garwan_User user) {
		List<OrderDTO> ordersByUser = new ArrayList<OrderDTO>();
		
		List<Order> userOrders = orderRepositary.findAllByUserr(user);
		
		for (Order order : userOrders) {
			if(order != null) {
				ordersByUser.add(OrderMapper.toDTO(order));
			}
		}
		
		return ordersByUser;
	}

	public List<Order> getAllOrdersByUserRaw(Garwan_User user) {
		return orderRepositary.findAllByUserr(user);
	}

	public OperationResult<Order, OrderService> createOrder(Order dto) {
		Order order = orderRepositary.save(dto);
		return new OperationResult<Order, OrderService>(order, order != null, false, this);
	}

}
