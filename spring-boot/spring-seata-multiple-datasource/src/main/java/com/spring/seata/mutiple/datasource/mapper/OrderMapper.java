package com.spring.seata.mutiple.datasource.mapper;

import com.spring.seata.mutiple.datasource.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author xuweizhi
 */

@Repository
public interface OrderMapper {

    /**
     * 保存订单
     * @param record
     * @return
     */
    @Insert("INSERT INTO order_tbl (user_id, commodity_code, count, status, money) VALUES (#{userId}, #{commodityCode}, #{count}, #{status}, #{money})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(Order record);

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    @Update("UPDATE order_tbl SET status = #{status} WHERE id = #{id}")
    int updateOrderStatus(@Param("id") Integer id, @Param("status") int status);

}
