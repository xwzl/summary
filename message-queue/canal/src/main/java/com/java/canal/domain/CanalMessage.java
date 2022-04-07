package com.java.canal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Canal Message
 *
 * @author xuweizhi
 * @since 2022/04/07 20:09
 */
@Data
public class CanalMessage<T> implements Serializable {
    @JsonProperty("data")
    private List<T> data;
    @JsonProperty("database")
    private String database;
    @JsonProperty("es")
    private Long es;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("isDdl")
    private Boolean isDdl;
    @JsonProperty("mysqlType")
    private MysqlTypeDTO mysqlType;
    @JsonProperty("old")
    private List<T> old;
    @JsonProperty("pkNames")
    private List<String> pkNames;
    @JsonProperty("sql")
    private String sql;
    @JsonProperty("sqlType")
    private SqlTypeDTO sqlType;
    @JsonProperty("table")
    private String table;
    @JsonProperty("ts")
    private Long ts;
    @JsonProperty("type")
    private String type;

    @NoArgsConstructor
    @Data
    public static class MysqlTypeDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("product_id")
        private String productId;
        @JsonProperty("total_inventory")
        private String totalInventory;
        @JsonProperty("lock_inventory")
        private String lockInventory;
    }

    @NoArgsConstructor
    @Data
    public static class SqlTypeDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("product_id")
        private Integer productId;
        @JsonProperty("total_inventory")
        private Integer totalInventory;
        @JsonProperty("lock_inventory")
        private Integer lockInventory;
    }


}
