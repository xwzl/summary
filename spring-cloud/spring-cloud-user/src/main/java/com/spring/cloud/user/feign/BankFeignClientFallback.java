package com.spring.cloud.user.feign;

import com.spring.cloud.user.feign.dtos.AccountTransferDTO;
import org.springframework.stereotype.Component;

/**
 * 降级逻辑
 *
 * @author xuweizhi
 */
@Component
public class BankFeignClientFallback implements BankFeignClient {

    @Override
    public Boolean transferTo(AccountTransferDTO accountTransfer) {
        // 调用失败降级处理
        return false;
    }
}
