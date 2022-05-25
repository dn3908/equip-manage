package com.ruoyi.web.controller.equip;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.equip.domain.Equipment;
import com.ruoyi.equip.service.EquipmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 装备Controller
 *
 * @author dengn
 * @date 2022-01-22
 */
@RestController
@RequestMapping("/ruiy/equipment")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@AllArgsConstructor
public class EquipmentController extends BaseController {

    @Autowired
    private  EquipmentService equipmentService;

    /**
     * 查询装备列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Equipment equipment)
    {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>(equipment);
        startPage();
        List<Equipment> list = equipmentService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 导出装备列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:equipment:export')")
    @Log(title = "装备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Equipment equipment)
    {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>(equipment);
        List<Equipment> list = equipmentService.list(queryWrapper);
        ExcelUtil<Equipment> util = new ExcelUtil<Equipment>(Equipment.class);
        return util.exportExcel(list, "装备数据");
    }

    /**
     * 获取装备详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruiy:equipment:query')")
    @GetMapping(value = "/{equipmentId}")
    public AjaxResult getInfo(@PathVariable("equipmentId") Long equipmentId)
    {
        return AjaxResult.success(equipmentService.getById(equipmentId));
    }

    /**
     * 新增装备
     */
    @PreAuthorize("@ss.hasPermi('ruiy:equipment:add')")
    @Log(title = "装备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Equipment equipment)
    {
        return toAjax(equipmentService.save(equipment));
    }

    /**
     * 修改装备
     */
    @PreAuthorize("@ss.hasPermi('ruiy:equipment:edit')")
    @Log(title = "装备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Equipment equipment)
    {
        return toAjax(equipmentService.updateById(equipment));
    }

    /**
     * 删除装备
     */
    @PreAuthorize("@ss.hasPermi('ruiy:equipment:remove')")
    @Log(title = "装备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{equipmentIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentIds)
    {
        return toAjax(equipmentService.removeByIds(Arrays.asList(equipmentIds)));
    }
}
