package com.ruoyi.web.controller.equip;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.tree.Tree;
import com.ruoyi.common.utils.tree.TreeFactory;
import com.ruoyi.equip.domain.Warehouse;
import com.ruoyi.equip.service.WarehouseService;
import com.ruoyi.system.mapper.SysDeptMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 部门仓库Controller
 *
 * @author dengn
 * @date 2022-01-23
 */
@RestController
@RequestMapping("/ruiy/warehouse")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WarehouseController extends BaseController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private SysDeptMapper deptMapper;


    /**
     * 查询部门仓库列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:warehouse:list')")
    @GetMapping("/list")
    public TableDataInfo list(Warehouse warehouse) {
        QueryWrapper<Warehouse> queryWrapper = new QueryWrapper<>(warehouse);
        startPage();
        List<Warehouse> list = warehouseService.list(queryWrapper);
        return getDataTable(list);
    }

    /**
     * 导出部门仓库列表
     */
    @PreAuthorize("@ss.hasPermi('ruiy:warehouse:export')")
    @Log(title = "部门仓库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Warehouse warehouse) {
        QueryWrapper<Warehouse> queryWrapper = new QueryWrapper<>(warehouse);
        List<Warehouse> list = warehouseService.list(queryWrapper);
        ExcelUtil<Warehouse> util = new ExcelUtil<Warehouse>(Warehouse.class);
        return util.exportExcel(list, "部门仓库数据");
    }

    /**
     * 获取部门仓库详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruiy:warehouse:query')")
    @GetMapping(value = "/{warId}")
    public AjaxResult getInfo(@PathVariable("warId") Long warId) {
        return AjaxResult.success(warehouseService.getById(warId));
    }

    /**
     * 新增部门仓库
     */
    @PreAuthorize("@ss.hasPermi('ruiy:warehouse:add')")
    @Log(title = "部门仓库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Warehouse warehouse) {
        return toAjax(warehouseService.save(warehouse));
    }

    /**
     * 修改部门仓库
     */
    @PreAuthorize("@ss.hasPermi('ruiy:warehouse:edit')")
    @Log(title = "部门仓库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Warehouse warehouse) {
        return toAjax(warehouseService.updateById(warehouse));
    }

    /**
     * 删除部门仓库
     */
    @PreAuthorize("@ss.hasPermi('ruiy:warehouse:remove')")
    @Log(title = "部门仓库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{warIds}")
    public AjaxResult remove(@PathVariable Long[] warIds) {
        return toAjax(warehouseService.removeByIds(Arrays.asList(warIds)));
    }

    /**
     * 部门仓库树
     */
    @PreAuthorize("@ss.hasPermi('ruiy:warehouse:tree')")
    @GetMapping("/tree")
    public R tree() {
        List<Warehouse> warehouses = Optional.ofNullable(warehouseService.list()).orElse(Lists.newArrayList());
        List<SysDept> sysDepts = deptMapper.selectAllDept();
        List<Tree> trees = Lists.newArrayList();
        List<Tree> deptTrees = Lists.newArrayList();

        for (SysDept sysDept : sysDepts) {
            Tree deptTree = new Tree()
                    .setLabel(sysDept.getDeptName())
                    .setId(sysDept.getDeptId())
                    .setPid(sysDept.getParentId())
                    .setValue(sysDept.getDeptId().toString());
            deptTrees.add(deptTree);
        }
        for (Warehouse warehouse : warehouses) {
            Tree tree = new Tree()
                    .setLabel(warehouse.getWarName())
                    .setId(warehouse.getWarId())
                    .setPid(warehouse.getDeptId())
                    .setValue(warehouse.getWarId().toString());
            trees.add(tree);
        }

        List<Tree> rootTrees = deptTrees.stream().filter(a -> a.getPid() == 0).collect(Collectors.toList());
        trees.addAll(deptTrees);
        for (Tree rootTree : rootTrees) {
            TreeFactory.createTree(trees, rootTree, "id", "pid", "children");
        }

        return R.ok(rootTrees);
    }
}
