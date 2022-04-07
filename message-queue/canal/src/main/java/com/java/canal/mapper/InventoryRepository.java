package com.java.canal.mapper;

import com.java.canal.domain.index.Inventory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xuweizhi
 * @since 2022/04/07 21:10
 */
@Repository
public interface InventoryRepository extends ElasticsearchRepository<Inventory, String> {

}
