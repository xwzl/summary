package com.spring.cloud.seata.storage.controller;

import com.spring.cloud.seata.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuweizhi
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(path = "/deduct")
    public Boolean deduct(String commodityCode, Integer count) {
        // 扣减库存
        storageService.deduct(commodityCode, count);
        return true;
    }
}
