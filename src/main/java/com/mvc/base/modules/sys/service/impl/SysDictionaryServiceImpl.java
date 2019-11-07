package com.mvc.base.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mvc.base.common.utils.PageUtils;
import com.mvc.base.common.utils.Query;
import com.mvc.base.modules.sys.dao.SysDictionaryDao;
import com.mvc.base.modules.sys.entity.SysDictionaryEntity;
import com.mvc.base.modules.sys.service.SysDictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

@Service("sysDictionaryService")
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryDao, SysDictionaryEntity> implements SysDictionaryService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");
        Page<SysDictionaryEntity> page = this.selectPage(
                new Query<SysDictionaryEntity>(params).getPage(),
                new EntityWrapper<SysDictionaryEntity>()
                        .like(StringUtils.isNotBlank(name), "name", name)
        );
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(Long[] userId) {
        this.deleteBatchIds(Arrays.asList(userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void deleteDictionaryInfo(Long[] ids) {
        deleteBatch(ids);
    }

}
