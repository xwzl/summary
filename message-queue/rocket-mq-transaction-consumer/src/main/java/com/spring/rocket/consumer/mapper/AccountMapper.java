package com.spring.rocket.consumer.mapper;

import com.spring.rocket.consumer.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Fox
 */
@Mapper
public interface AccountMapper {

    @Update("update t_account set balance=balance - #{amount} where balance>=#{amount} and bank_card=#{accountNo} ")
    int subtractAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

    @Update("update t_account set balance=balance + #{amount} where bank_card=#{accountNo} ")
    int addAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);


    @Select("select * from t_account where where bank_card=#{accountNo}")
    Account findByIdAccountNo(@Param("accountNo") String accountNo);



    @Select("select count(1) from local_transaction_log where tx_no = #{txNo}")
    int isExistTx(String txNo);


    @Insert("insert into local_transaction_log values(#{txNo},'1',now());")
    int addTx(String txNo);
}
