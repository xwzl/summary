package com.summary.elasticsearch.repository;

import com.summary.elasticsearch.domain.model.BookDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 书索引 repository
 *
 * @author xuweizhi
 * @since 2021/07/20 18:37
 */
@Component
public interface BookRepository extends ElasticsearchRepository<BookDO, String> {

    /**
     * 根据 author 获取信息
     *
     * @param author 对象
     * @return 返回值
     */
    Optional<BookDO> findByAuthor(String author);

    /**
     * 根据 author 查询所有的信息
     *
     * @param author 作者名称
     * @return 返回值
     */
    List<BookDO> findAllByAuthor(String author);
}
