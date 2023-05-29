package org.LyQing.takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.common.CustomException;
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
@Transactional
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetMealDishService setMealDishService;

    /**
     * 新增套餐同时需要保存套餐和菜品的关联关系
     *
     * @param setMealDto 套餐dto
     */
    @Override
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

    /**
     * 删除套餐同时需要删除套餐和菜品的关联关系
     *
     * @param ids id
     */
    @Override
    public void removeWithDish(List<Long> ids) {
        //查询套餐状态，确定是否可用删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);

        int count = this.count(queryWrapper);
        //如果不可以删除抛出业务异常
        if (count > 0) {
            throw new CustomException("套餐正在售卖中，不能删除");
        }
        //如可以删除，先删除套餐表中的数据---setmeal
        this.removeByIds(ids);
        //删除关系表中的数据---setmeal_dish
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);

        setMealDishService.remove(lambdaQueryWrapper);
    }
}
