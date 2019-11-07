package com.mvc.base.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mvc.base.modules.sys.entity.SysDictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDictionaryDao extends BaseMapper<SysDictionaryEntity> {
}
