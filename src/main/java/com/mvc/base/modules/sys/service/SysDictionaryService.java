package com.mvc.base.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.mvc.base.common.utils.PageUtils;
import com.mvc.base.modules.sys.entity.SysDictionaryEntity;

import java.util.Map;

public interface SysDictionaryService extends IService<SysDictionaryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void deleteBatch(Long[] userId);

    void deleteDictionaryInfo(Long[] ids);
}
