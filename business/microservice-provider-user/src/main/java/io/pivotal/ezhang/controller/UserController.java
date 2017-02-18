package io.pivotal.ezhang.controller;


import io.pivotal.ezhang.domain.User;
import io.pivotal.ezhang.repository.UserRepository;
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
public class UserController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserRepository userRepository;

    /**
     * @param id
     * @return user信息
     */
    @GetMapping("/{id}")
    public User findByUserId(@PathVariable Long id) {
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }
    /**
     * @param id
     * @return user信息
     */
    @GetMapping("/usercontact/{id}")
    public User findContactByUserId(@PathVariable Long id) {
        User findOne = this.userRepository.findOne(id);
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
