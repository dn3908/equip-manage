package com.ruoyi.web.controller.equip;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.equip.domain.EquipmentType;
import com.ruoyi.equip.service.EquipmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;


/**
 * 装备分类Controller
 *
 * @author dengn
 * @date 2022-01-23
 */
@RestController
@RequestMapping("/ruiy/type")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EquipmentTypeController extends BaseController
{
    @Autowired
    private EquipmentTypeService equipmentTypeService;

    /**
     * 查询装备分类列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:type:list')")
    @GetMapping("/list")
    public AjaxResult list(EquipmentType equipmentType)
    {
        QueryWrapper<EquipmentType> queryWrapper = new QueryWrapper<>(equipmentType);
        startPage();
        List<EquipmentType> list = equipmentTypeService.list(queryWrapper);
        return AjaxResult.success(list);
    }

    /**
     * 导出装备分类列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:type:export')")
    @Log(title = "装备分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(EquipmentType equipmentType)
    {
        QueryWrapper<EquipmentType> queryWrapper = new QueryWrapper<>(equipmentType);
        List<EquipmentType> list = equipmentTypeService.list(queryWrapper);
        ExcelUtil<EquipmentType> util = new ExcelUtil<EquipmentType>(EquipmentType.class);
        return util.exportExcel(list, "装备分类数据");
    }

    /**
     * 获取装备分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruiy:type:query')")
    @GetMapping(value = "/{equipmentTypeId}")
    public AjaxResult getInfo(@PathVariable("equipmentTypeId") Long equipmentTypeId)
    {
        return AjaxResult.success(equipmentTypeService.getById(equipmentTypeId));
    }

    /**
     * 新增装备分类
     */
    @PreAuthorize("@ss.hasPermi('ruiy:type:add')")
    @Log(title = "装备分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EquipmentType equipmentType)
    {
        return toAjax(equipmentTypeService.save(equipmentType));
    }

    /**
     * 修改装备分类
     */
    @PreAuthorize("@ss.hasPermi('ruiy:type:edit')")
    @Log(title = "装备分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EquipmentType equipmentType)
    {
        return toAjax(equipmentTypeService.updateById(equipmentType));
    }

    /**
     * 删除装备分类
     */
    @PreAuthorize("@ss.hasPermi('ruiy:type:remove')")
    @Log(title = "装备分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{equipmentTypeIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentTypeIds)
    {
        return toAjax(equipmentTypeService.removeByIds(Arrays.asList(equipmentTypeIds)));
    }
}