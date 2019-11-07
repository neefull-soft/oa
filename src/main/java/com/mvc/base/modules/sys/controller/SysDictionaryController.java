package com.mvc.base.modules.sys.controller;

import com.mvc.base.common.annotation.SysLog;
import com.mvc.base.common.utils.Constant;
import com.mvc.base.common.utils.PageUtils;
import com.mvc.base.common.utils.R;
import com.mvc.base.common.validator.Assert;
import com.mvc.base.modules.sys.entity.SysDictionaryEntity;
import com.mvc.base.modules.sys.entity.SysUserEntity;
import com.mvc.base.modules.sys.form.PasswordForm;
import com.mvc.base.modules.sys.service.SysDictionaryService;
import com.mvc.base.modules.sys.service.SysUserRoleService;
import com.mvc.base.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/dictionary")
public class SysDictionaryController extends AbstractController {
    @Autowired
    private SysDictionaryService sysDictionaryService;

    /**
     * 所有字典列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:dictionary:list")
    public R list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        PageUtils page = sysDictionaryService.queryPage(params);
       List<SysDictionaryEntity> dictionaryList = (List<SysDictionaryEntity>) page.getList();
        page.setList(dictionaryList);
        return R.ok().put("page", page);
    }


    /**
     * 字典信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:dictionary:info")
    public R info(@PathVariable("id") Long id) {
        SysDictionaryEntity dictionary = sysDictionaryService.selectById(id);
        return R.ok().put("dictionary", dictionary);
    }

    /**
     * 所有父级字典
     */
    @GetMapping("/selectParent")
    @RequiresPermissions("sys:dictionary:select")
    public R select(){
        Map<String, Object> map = new HashMap<>();
        map.put("type", "1");
        List<SysDictionaryEntity> list = sysDictionaryService.selectByMap(map);
        return R.ok().put("list", list);
    }

    /**
     * 保存字典信息
     */
    @SysLog("保存字典信息")
    @PostMapping("/save")
    @RequiresPermissions("sys:dictionary:save")
    public R save(@RequestBody SysDictionaryEntity dictionary) {
        if("1".equals(dictionary.getParentId())){
            dictionary.setType(1);
        }else{
            dictionary.setType(2);
        }
        sysDictionaryService.insert(dictionary);
        return R.ok();
    }

    /**
     * 修改用户
     */
    @SysLog("修改字典信息")
    @PostMapping("/update")
    @RequiresPermissions("sys:dictionary:update")
    public R update(@RequestBody SysDictionaryEntity dictionary) {
        sysDictionaryService.updateById(dictionary);
        return R.ok();
    }

    /**
     * 删除用户
     */
    @SysLog("删除字典信息")
    @PostMapping("/delete")
    @RequiresPermissions("sys:dictionary:delete")
    public R delete(@RequestBody Long[] ids) {
        sysDictionaryService.deleteDictionaryInfo(ids);
        return R.ok();
    }
}
