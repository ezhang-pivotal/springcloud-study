package io.pivotal.ezhang.controller;

import io.pivotal.ezhang.domain.Product;
import io.pivotal.ezhang.domain.UserOrder;
import io.pivotal.ezhang.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ezhang on 2016/12/9.
 */

@RestController
public class OrderController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param id
     * @return order信息
     */
    @GetMapping("/{id}")
    public UserOrder findByOrderId(@PathVariable Long id) {
        UserOrder findOne = this.orderRepository.findOne(id);
        this.restTemplate.getForObject("http://microservice-provider-product/" +
                findOne.getProductid(), Product.class);
        return findOne;
    }

    /**
     * 本地服务实例的信息
     * @return
     */
    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }
}
