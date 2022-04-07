package com.java.canal.convert;

import com.java.canal.domain.InventoryDTO;
import com.java.canal.domain.index.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author xuweizhi
 * @since 2022/04/07 21:16
 */
@Mapper(componentModel = "spring")
public interface InventoryMapStruct {

    InventoryMapStruct INSTANCE = Mappers.getMapper(InventoryMapStruct.class);

    /**
     * 库存对象转换
     *
     * @param inventory //
     * @return //
     */
    Inventory convertInventory(InventoryDTO inventory);

    /**
     * 库存对象转换
     *
     * @param inventoryList //
     * @return //
     */
    List<Inventory> convertInventoryList(List<InventoryDTO> inventoryList);


}
