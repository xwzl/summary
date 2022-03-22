package com.spring.cloud.user.mapper;

import org.apache.ibatis.annotations.*;

/**
 * Tcc 事务
 *
 * @author xuweizhi
 * @since 2022/03/22 00:42
 */
@Mapper
public interface AccountMapper {

    @Update("update t_account set balance=balance - #{amount} where balance>=#{amount} and bank_card=#{accountNo} ")
    int subtractAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

    @Update("update t_account set balance=balance + #{amount} where bank_card=#{accountNo} ")
    int addAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);


    /**
     * 增加某分支事务执行记录
     * @param txNo 本地事务编号
     * @param type  1:try,2:confirm,3:cancel
     * @return
     */
    @Insert("insert into local_transaction_log values(#{txNo},#{type},now());")
    int addTransactionLog(@Param("txNo") String txNo,@Param("type") int type);

    /**
     * 查询分支事务是否已执行
     * @param txNo 本地事务编号
     * @param type  1:try,2:confirm,3:cancel
     * @return
     */
    @Select("select count(1) from local_transaction_log where tx_no = #{txNo} and type=#{type} ")
    int isExistTransactionLogByType(@Param("txNo")String txNo,@Param("type")int type);

}
