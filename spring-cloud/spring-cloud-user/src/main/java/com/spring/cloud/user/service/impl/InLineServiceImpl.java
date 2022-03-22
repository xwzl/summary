package com.spring.cloud.user.service.impl;

import com.spring.cloud.user.service.InLineService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Component;

/**
 * The type In line service.
 *
 * @author xuweizhi(Myth)
 */
@Component
public class InLineServiceImpl implements InLineService {

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public void test() {
        System.out.println("执行inline try......");
    }

    /**
     * Confrim.
     */
    public void confirm() {
        System.out.println("执行inline confirm......");
    }

    /**
     * Cancel.
     */
    public void cancel() {
        System.out.println("执行inline cancel......");
    }
}
