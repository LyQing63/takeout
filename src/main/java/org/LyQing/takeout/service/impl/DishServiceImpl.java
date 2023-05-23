package org.LyQing.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.dto.DishDto;
import org.LyQing.takeout.entity.Dish;
import org.LyQing.takeout.entity.DishFlavor;
import org.LyQing.takeout.mapper.DishMapper;
import org.LyQing.takeout.service.DishFlavorService;
import org.LyQing.takeout.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品服务
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 保存与味道
     *
     * @param dishDto 菜dto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {

        //保存菜品基本信息到菜品表
        this.save(dishDto);

        Long dishId = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //保存菜品口味信息到口味表
        dishFlavorService.saveBatch(flavors);

    }
}
