package com.java.canal.message.impl;

import com.java.canal.convert.InventoryMapStruct;
import com.java.canal.domain.InventoryDTO;
import com.java.canal.domain.index.Inventory;
import com.java.canal.mapper.InventoryRepository;
import com.java.canal.message.CanalMessageHandler;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库存 elastic 同步
 *
 * @author xuweizhi
 * @since 2022/04/07 20:56
 */
@Component
public class InventoryElasticsearchHandler implements CanalMessageHandler<InventoryDTO> {

    @Resource
    private InventoryRepository inventoryRepository;


    @Override
    public void insert(List<InventoryDTO> data) {
        List<Inventory> inventoryList = InventoryMapStruct.INSTANCE.convertInventoryList(data);
        try {
            inventoryRepository.saveAll(inventoryList);
        } catch (Exception e) {
        }
    }

    @Override
    public void update(List<InventoryDTO> data) {
        List<Inventory> inventoryList = InventoryMapStruct.INSTANCE.convertInventoryList(data);
        try {
            inventoryRepository.saveAll(inventoryList);
        } catch (Exception e) {
        }
    }

    @Override
    public void delete(List<InventoryDTO> data) {
        inventoryRepository.deleteAllById(data.stream().map(InventoryDTO::getId).collect(Collectors.toList()));
    }
}
