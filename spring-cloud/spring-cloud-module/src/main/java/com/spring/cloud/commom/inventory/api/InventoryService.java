/*
 * Copyright 2017-2021 Dromara.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.spring.cloud.commom.inventory.api;

import com.spring.cloud.commom.inventory.dto.InventoryDTO;
import com.spring.cloud.commom.inventory.entity.InventoryDO;
import org.dromara.hmily.annotation.Hmily;

import java.util.List;

/**
 * The interface Inventory service.
 *
 * @author xuweizhi
 */
public interface InventoryService {

    /**
     * 扣减库存操作
     * 这一个tcc接口
     *
     * @param inventoryDTO 库存DTO对象
     * @return true boolean
     */
    @Hmily
    Boolean decrease(InventoryDTO inventoryDTO);

    /**
     * Test in line list.
     *
     * @return the list
     */
    @Hmily
    List<InventoryDTO> testInLine();

    /**
     * Test decrease boolean.
     *
     * @param inventoryDTO the inventory dto
     * @return the boolean
     */
    Boolean testDecrease(InventoryDTO inventoryDTO);

    /**
     * 获取商品库存信息
     *
     * @param productId 商品id
     * @return InventoryDO inventory do
     */
    InventoryDO findByProductId(String productId);

    /**
     * mock扣减库存异常
     *
     * @param inventoryDTO dto对象
     * @return String string
     */
    @Hmily
    String mockWithTryException(InventoryDTO inventoryDTO);

    /**
     * mock扣减库存超时
     *
     * @param inventoryDTO dto对象
     * @return String boolean
     */
    @Hmily
    Boolean mockWithTryTimeout(InventoryDTO inventoryDTO);

    /**
     * mock 扣减库存confirm超时
     *
     * @param inventoryDTO dto对象
     * @return True boolean
     */
    @Hmily
    Boolean mockWithConfirmTimeout(InventoryDTO inventoryDTO);
}
