package com.java.canal.message.impl;

import com.java.canal.convert.InventoryMapStruct;
import com.java.canal.domain.InventoryDTO;
import com.java.canal.domain.index.Inventory;
import com.java.canal.message.CanalMessageHandler;
import com.java.tool.utils.JsonUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 库存 elastic 同步
 *
 * @author xuweizhi
 * @since 2022/04/07 20:56
 */
@Component
public class RedisElasticsearchHandler implements CanalMessageHandler<InventoryDTO> {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private final static String INVENTORY_PREFIX = "stock:inventory:%s";

    @Override
    public void insert(List<InventoryDTO> data) {
        List<Inventory> inventoryList = InventoryMapStruct.INSTANCE.convertInventoryList(data);
        inventoryList.forEach(inventory -> {
            redisTemplate.opsForValue().set(String.format(INVENTORY_PREFIX, inventory.getId()), JsonUtil.toJsonString(inventory));
        });
    }

    @Override
    public void update(List<InventoryDTO> data) {
        List<Inventory> inventoryList = InventoryMapStruct.INSTANCE.convertInventoryList(data);
        inventoryList.forEach(inventory -> {
            redisTemplate.opsForValue().set(String.format(INVENTORY_PREFIX, inventory.getId()), JsonUtil.toJsonString(inventory));
        });
    }

    @Override
    public void delete(List<InventoryDTO> data) {
        List<Inventory> inventoryList = InventoryMapStruct.INSTANCE.convertInventoryList(data);
        inventoryList.forEach(inventory -> {
            redisTemplate.delete(String.format(INVENTORY_PREFIX, inventory.getId()));
        });
    }
}
