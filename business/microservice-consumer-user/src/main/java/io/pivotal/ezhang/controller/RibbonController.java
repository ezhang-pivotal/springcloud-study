package io.pivotal.ezhang.controller;
import io.pivotal.ezhang.entity.User;
import io.pivotal.ezhang.entity.UserOrder;
import io.pivotal.ezhang.services.RibbonHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 * Created by ezhang on 2016/12/10.
 */
@RestController
@RefreshScope
@EnableAutoConfiguration
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RibbonHystrixService ribbonHystrixService;
    /**
     * 通过Ribbon使用RestTemplate调用服务
     * @param id id
     * @return 通过id查询到的用户
     */
    @GetMapping("/ribbon/{id}")
    public User findById(@PathVariable Long id) {
        System.out.println("-----------"+id);
        return this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
    }

    /**
     * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
     * @param id id
     * @return 通过id查询到的用户
     */
    @GetMapping("/ribbon-hystrix/{id}")
    public User findByIdWithHystrix(@PathVariable Long id) {
        return this.ribbonHystrixService.findById(id);
    }


    @Value("${profile}")
    private String profile;

    @Value("${debug}")
    private String debug;

    @GetMapping("/hello")
    public String hello() {
        return this.profile+"/"+this.debug;
    }

    /**
     * 查询用户和订单
     * @param id id
     * @return 通过id查询到的订单
     */
    @GetMapping("/finduserorder/{id}")
    public UserOrder findByUserAndOrder(@PathVariable Long id) {
        UserOrder order = this.restTemplate.getForObject("http://microservice-provider-order/" + id, UserOrder.class);
        this.restTemplate.getForObject("http://microservice-provider-user/" + order.getUserid(), User.class);
        return order;
    }
}