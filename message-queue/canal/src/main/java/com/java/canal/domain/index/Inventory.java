
package com.java.canal.domain.index;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

/**
 * Canal Message 库存对象
 *
 * @author xuweizhi
 * @since 2022/04/07 11:44
 */
@Data
@NoArgsConstructor
@Document(indexName = "inventory")
@Setting(shards = 1, replicas = 1)
public class Inventory implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String productId;

    @Field(type = FieldType.Text)
    private String totalInventory;

    @Field(type = FieldType.Text)
    private String lockInventory;
}