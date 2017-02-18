package io.pivotal.ezhang.controller;


import io.pivotal.ezhang.domain.Product;
import io.pivotal.ezhang.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ezhang on 2016/12/9.
 */

@RestController
public class ProductController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private ProductRepository productRepository;

    /**
     * @param id
     * @return user信息
     */
    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        Product findOne = this.productRepository.findOne(id);
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
