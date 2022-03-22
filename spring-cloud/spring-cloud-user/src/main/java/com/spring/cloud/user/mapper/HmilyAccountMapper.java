package com.spring.cloud.user.mapper;

import com.spring.cloud.commom.account.dto.HmilyAccountDTO;
import com.spring.cloud.commom.account.entity.HmilyAccountDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * The interface Account mapper.
 *
 * @author xuweizhi
 */
@Mapper
public interface HmilyAccountMapper {

    /**
     * Update int.
     *
     * @param accountDTO the account dto
     * @return the int
     */
    @Update("update account set balance = balance - #{amount}," +
            " freeze_amount= freeze_amount + #{amount} ,update_time = now()" +
            " where user_id =#{userId}  and  balance >= #{amount}  ")
    int update(HmilyAccountDTO accountDTO);

    /**
     * Update tac int.
     *
     * @param accountDTO the account dto
     * @return the int
     */
    @Update("update account set balance = balance - #{amount}, update_time = now()" +
            " where user_id =#{userId} and balance >= #{amount}  ")
    int updateTAC(HmilyAccountDTO accountDTO);

    /**
     * Test update int.
     *
     * @param accountDTO the account dto
     * @return the int
     */
    @Update("update account set balance = balance - #{amount}, update_time = now() " +
            " where user_id =#{userId}  and  balance >= #{amount}  ")
    int testUpdate(HmilyAccountDTO accountDTO);

    /**
     * Confirm int.
     *
     * @param accountDTO the account dto
     * @return the int
     */
    @Update("update account set " +
            " freeze_amount= freeze_amount - #{amount}" +
            " where user_id =#{userId}  and freeze_amount >= #{amount} ")
    int confirm(HmilyAccountDTO accountDTO);

    /**
     * Cancel int.
     *
     * @param accountDTO the account dto
     * @return the int
     */
    @Update("update account set balance = balance + #{amount}," +
            " freeze_amount= freeze_amount -  #{amount} " +
            " where user_id =#{userId}  and freeze_amount >= #{amount}")
    int cancel(HmilyAccountDTO accountDTO);

    /**
     * 根据userId获取用户账户信息
     *
     * @param userId 用户id
     * @return AccountDO account do
     */
    @Select("select id,user_id,balance, freeze_amount from account where user_id =#{userId} limit 1")
    HmilyAccountDO findByUserId(String userId);
}
