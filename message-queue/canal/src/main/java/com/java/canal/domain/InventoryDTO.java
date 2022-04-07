package com.java.canal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Canal Message 库存对象
 *
 * @author xuweizhi
 * @since 2022/04/07 11:44
 */
@Data
@NoArgsConstructor
public class InventoryDTO implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("total_inventory")
    private String totalInventory;

    @JsonProperty("lock_inventory")
    private String lockInventory;
}