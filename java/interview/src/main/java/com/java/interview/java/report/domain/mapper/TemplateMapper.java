package com.java.interview.java.report.domain.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.interview.java.report.domain.entity.TemplateDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 模板名称 Mapper 接口
 *
 * @author xuweizhi
 * @since 2022-05-31
 */
@Mapper
public interface TemplateMapper extends BaseMapper<TemplateDO> {

}
