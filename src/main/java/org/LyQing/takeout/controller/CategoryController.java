package org.LyQing.takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.common.R;
import org.LyQing.takeout.entity.Category;
import org.LyQing.takeout.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 类别控制器
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 保存
     *
     * @param category 类别
     * @return {@link R}<{@link String}>
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {

        log.info("category:{}", category);
        categoryService.save(category);

        return R.success("新增分类成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {

        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //条件构造器：设置排序条件
        LambdaQueryWrapper<Category> query = new LambdaQueryWrapper<>();
        //添加排序条件
        query.orderByAsc(Category::getSort);

        //进行分页查询
        categoryService.page(pageInfo, query);

        return R.success(pageInfo);
    }

    /**
     * 根据id删除
     *
     * @param ids id
     * @return {@link R}<{@link String}>
     */
    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("删除分类，id为:{}", ids);

        categoryService.removeById(ids);

        return R.success("分类信息删除成功");
    }

}
