package org.LyQing.takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.common.R;
import org.LyQing.takeout.dto.DishDto;
import org.LyQing.takeout.dto.SetMealDto;
import org.LyQing.takeout.entity.Category;
import org.LyQing.takeout.entity.Setmeal;
import org.LyQing.takeout.service.CategoryService;
import org.LyQing.takeout.service.SetMealDishService;
import org.LyQing.takeout.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐控制器
 *
 * @author yjxx_2022
 * @date 2023/05/26
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetMealController {

    @Autowired
    private SetmealService setMealService;

    @Autowired
    private SetMealDishService setMealDishService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    private R<String> save(@RequestBody SetMealDto setMealDto) {

        setMealService.saveWithDish(setMealDto);

        return R.success("新增套餐成功!");
    }

    /**
     * 页面
     *
     * @param page     页面
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link R}<{@link Page}>
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        //分页构造器
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);

        Page<SetMealDto> pageDto = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //添加条件
        queryWrapper.like(name != null, Setmeal::getName , name);
        //排序条件,根据时间降序
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setMealService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, pageDto, "records");
        //records属性拷贝
        List<SetMealDto> list = pageInfo.getRecords().stream().map((item) -> {
            SetMealDto setMealDto = new SetMealDto();
            BeanUtils.copyProperties(item, setMealDto);
            //分类id
            Long categoryId = item.getCategoryId();
            //根据分类id查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                setMealDto.setCategoryName(categoryName);
            }
            return setMealDto;
        }).collect(Collectors.toList());

        pageDto.setRecords(list);

        return R.success(pageDto);
    }

    /**
     * 删除
     *
     * @param ids id
     * @return {@link R}<{@link String}>
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {

        log.info("ids:{}", ids);
        setMealService.removeWithDish(ids);

        return R.success("删除成功");
    }

}
