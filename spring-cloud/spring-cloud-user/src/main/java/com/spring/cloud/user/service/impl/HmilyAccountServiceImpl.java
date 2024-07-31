package com.spring.cloud.user.service.impl;

import com.spring.cloud.commom.account.dto.HmilyAccountDTO;
import com.spring.cloud.commom.account.dto.AccountNestedDTO;
import com.spring.cloud.commom.account.entity.HmilyAccountDO;
import com.spring.cloud.user.mapper.HmilyAccountMapper;
import com.spring.cloud.commom.inventory.dto.InventoryDTO;
import com.spring.cloud.user.feign.InventoryClient;
import com.spring.cloud.user.service.HmilyAccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

/**
 * Tcc 事务
 *
 * @author xuweizhi
 * @since 2022/03/22 00:42
 */
@Service
@Slf4j
public class HmilyAccountServiceImpl implements HmilyAccountService {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private HmilyAccountMapper hmilyAccountMapper;

    @Resource
    private InventoryClient inventoryClient;


    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean payment(final HmilyAccountDTO accountDTO) {
        LOGGER.info("============执行try付款接口===============");
        hmilyAccountMapper.update(accountDTO);
        return Boolean.TRUE;
    }

    @Override
    public boolean testPayment(HmilyAccountDTO accountDTO) {
        hmilyAccountMapper.testUpdate(accountDTO);
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean mockWithTryException(HmilyAccountDTO accountDTO) {
        throw new HmilyRuntimeException("账户扣减异常！");
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean mockWithTryTimeout(HmilyAccountDTO accountDTO) {
        try {
            //模拟延迟 当前线程暂停10秒
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int decrease = hmilyAccountMapper.update(accountDTO);
        if (decrease != 1) {
            throw new HmilyRuntimeException("账户余额不足");
        }
        return true;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmNested", cancelMethod = "cancelNested")
    public boolean paymentWithNested(AccountNestedDTO nestedDTO) {
        hmilyAccountMapper.update(buildAccountDTO(nestedDTO));
        inventoryClient.decrease(buildInventoryDTO(nestedDTO));
        return Boolean.TRUE;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmNested", cancelMethod = "cancelNested")
    public boolean paymentWithNestedException(AccountNestedDTO nestedDTO) {
        hmilyAccountMapper.update(buildAccountDTO(nestedDTO));
        inventoryClient.mockWithTryException(buildInventoryDTO(nestedDTO));
        return Boolean.TRUE;
    }

    @Override
    public HmilyAccountDO findByUserId(final String userId) {
        return hmilyAccountMapper.findByUserId(userId);
    }

    /**
     * Confirm boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    public boolean confirm(final HmilyAccountDTO accountDTO) {
        LOGGER.info("============执行confirm 付款接口===============");
        return hmilyAccountMapper.confirm(accountDTO) > 0;
    }


    /**
     * Cancel boolean.
     *
     * @param accountDTO the account dto
     * @return the boolean
     */
    public boolean cancel(final HmilyAccountDTO accountDTO) {
        LOGGER.info("============执行cancel 付款接口===============");
        return hmilyAccountMapper.cancel(accountDTO) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirmNested(AccountNestedDTO accountNestedDTO) {
        LOGGER.info("============confirmNested确认付款接口===============");
        return hmilyAccountMapper.confirm(buildAccountDTO(accountNestedDTO)) > 0;
    }

    /**
     * Cancel nested boolean.
     *
     * @param accountNestedDTO the account nested dto
     * @return the boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelNested(AccountNestedDTO accountNestedDTO) {
        LOGGER.info("============cancelNested 执行取消付款接口===============");
        return hmilyAccountMapper.cancel(buildAccountDTO(accountNestedDTO)) > 0;
    }

    private HmilyAccountDTO buildAccountDTO(AccountNestedDTO nestedDTO) {
        HmilyAccountDTO dto = new HmilyAccountDTO();
        dto.setAmount(nestedDTO.getAmount());
        dto.setUserId(nestedDTO.getUserId());
        return dto;
    }

    private InventoryDTO buildInventoryDTO(AccountNestedDTO nestedDTO) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setCount(nestedDTO.getCount());
        inventoryDTO.setProductId(nestedDTO.getProductId());
        return inventoryDTO;
    }
}
