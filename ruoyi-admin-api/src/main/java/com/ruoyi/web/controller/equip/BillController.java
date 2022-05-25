package com.ruoyi.web.controller.equip;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.equip.domain.Bill;
import com.ruoyi.equip.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 单据主Controller
 *
 * @author dengn
 * @date 2022-01-25
 */
@RestController
@RequestMapping("/ruiy/bill")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BillController extends BaseController {

    @Autowired
    private BillService billService;

    /**
     * 查询单据主列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:bill:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bill bill)
    {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>(bill);
        startPage();
        List<Bill> list = billService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 导出单据主列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:bill:export')")
    @Log(title = "单据主", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Bill bill)
    {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>(bill);
        List<Bill> list = billService.list(queryWrapper);
        ExcelUtil<Bill> util = new ExcelUtil<Bill>(Bill.class);
        return util.exportExcel(list, "单据主数据");
    }

    /**
     * 获取单据主详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruiy:bill:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(billService.getById(id));
    }

    /**
     * 新增单据主
     */
    @PreAuthorize("@ss.hasPermi('ruiy:bill:add')")
    @Log(title = "单据主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bill bill)
    {
        return toAjax(billService.save(bill));
    }

    /**
     * 修改单据主
     */
    @PreAuthorize("@ss.hasPermi('ruiy:bill:edit')")
    @Log(title = "单据主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bill bill)
    {
        return toAjax(billService.updateById(bill));
    }

    /**
     * 删除单据主
     */
    @PreAuthorize("@ss.hasPermi('ruiy:bill:remove')")
    @Log(title = "单据主", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(billService.removeByIds(Arrays.asList(ids)));
    }
}
