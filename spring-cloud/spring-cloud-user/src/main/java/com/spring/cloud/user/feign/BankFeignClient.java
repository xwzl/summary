package com.spring.cloud.user.feign;

import com.spring.cloud.user.feign.dtos.AccountTransferDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 订单服务扣减库存
 *
 * @author xuweizhi
 */
@FeignClient(value = "spring-cloud-order", contextId = "tcc",fallback = BankFeignClientFallback.class)
public interface BankFeignClient {

    /**
     * 扣减库存
     *
     * @param accountTransfer 转入
     * @return 返回
     */
    @Hmily
    @PostMapping("/bank2/transferTo")
    Boolean transferTo(@RequestBody AccountTransferDTO accountTransfer);
}
