package io.pivotal.ezhang.controller;

import io.pivotal.ezhang.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ezhang on 2016/12/10.
 */
@RestController
public class FeignController {
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private UserFeignHystrixClient userFeignHystrixClient;

    @FeignClient(name = "microservice-provider-user")
    interface UserFeignClient {
        @RequestMapping("/{id}")
        public User findByIdFeign(@RequestParam("id") Long id);
    }

    /**
     * 通过Feign调用服务
     * @param id id
     * @return 通过id查询到的用户
     */
    @GetMapping("feign/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        User user = this.userFeignClient.findByIdFeign(id);
        return user;
    }

    /**
     * 使用@FeignClient注解的fallback属性，指定fallback类
     * @author ezhang
     */
    @FeignClient(name = "microservice-provider-user", fallback = UserFeignHystrixClient.HystrixClientFallback.class)
    public interface UserFeignHystrixClient {
        @RequestMapping("/{id}")
        public User findByIdFeignWithHystrix(@RequestParam("id") Long id);

        @Component
        static class HystrixClientFallback implements UserFeignHystrixClient {
            /**
             * hystrix fallback方法
             *
             * @param id id
             * @return 默认的用户
             */
            @Override
            public User findByIdFeignWithHystrix(Long id) {
                User user = new User();
                user.setId(-1L);
                user.setUsername("default username");
                user.setAge(0);
                return user;
            }
        }
    }
    @GetMapping("feign-hystrix/{id}")
    public User findByIdFeignWithHystrix(@PathVariable Long id) {
        User user = this.userFeignHystrixClient.findByIdFeignWithHystrix(id);
        return user;
    }
}