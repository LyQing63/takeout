package org.LyQing.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.dto.SetMealDto;
import org.LyQing.takeout.entity.SetmealDish;
import org.LyQing.takeout.entity.Setmeal;
import org.LyQing.takeout.mapper.SetmealMapper;
import org.LyQing.takeout.service.SetMealDishService;
import org.LyQing.takeout.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐服务
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetMealDishService setMealDishService;

    /**
     * 新增套餐同时需要保存套餐和菜品的关联关系
     *
     * @param setMealDto 套餐dto
     */
    @Override
    @Transactional
    public void saveWithDish(SetMealDto setMealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setMealDto);
        List<SetmealDish> setmealDishes = setMealDto.getSetmealDishes();

        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setMealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，执行setmeal_dish，执行insert操作
        setMealDishService.saveBatch(setmealDishes);
    }
}
