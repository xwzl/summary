package com.spring.cloud.seata.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xuweizhi
 */
@FeignClient(name = "account-service",path = "/account",fallbackFactory = FallbackAccountFeignServiceFactory.class)
public interface AccountFeignService {

    @RequestMapping("/debit")
    Boolean debit(@RequestParam("userId") String userId,@RequestParam("money") int money);
}
