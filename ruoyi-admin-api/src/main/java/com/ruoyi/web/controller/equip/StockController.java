package com.ruoyi.web.controller.equip;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.equip.domain.Stock;
import com.ruoyi.equip.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 库存管理Controller
 *
 * @author dengn
 * @date 2022-01-25
 */
@RestController
@RequestMapping("/ruiy/stock")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StockController extends BaseController {

    @Autowired
    private StockService stockService;

    /**
     * 查询库存管理列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:stock:list')")
    @GetMapping("/list")
    public TableDataInfo list(Stock stock)
    {
        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>(stock);
        startPage();
        List<Stock> list = stockService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 导出库存管理列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:stock:export')")
    @Log(title = "库存管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Stock stock)
    {
        QueryWrapper<Stock> queryWrapper = new QueryWrapper<>(stock);
        List<Stock> list = stockService.list(queryWrapper);
        ExcelUtil<Stock> util = new ExcelUtil<Stock>(Stock.class);
        return util.exportExcel(list, "库存管理数据");
    }

    /**
     * 获取库存管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruiy:stock:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(stockService.getById(id));
    }

    /**
     * 新增库存管理
     */
    @PreAuthorize("@ss.hasPermi('ruiy:stock:add')")
    @Log(title = "库存管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Stock stock)
    {
        return toAjax(stockService.save(stock));
    }

    /**
     * 修改库存管理
     */
    @PreAuthorize("@ss.hasPermi('ruiy:stock:edit')")
    @Log(title = "库存管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Stock stock)
    {
        return toAjax(stockService.updateById(stock));
    }

    /**
     * 删除库存管理
     */
    @PreAuthorize("@ss.hasPermi('ruiy:stock:remove')")
    @Log(title = "库存管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stockService.removeByIds(Arrays.asList(ids)));
    }
}
