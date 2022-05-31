package com.java.interview.java.report.domain.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.interview.java.report.domain.entity.ConfigItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 配置项 Mapper 接口
 *
 * @author xuweizhi
 * @since 2022-05-31
 */
@Mapper
public interface ConfigItemMapper extends BaseMapper<ConfigItemDO> {

}
