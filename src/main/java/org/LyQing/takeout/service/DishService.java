package org.LyQing.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.LyQing.takeout.dto.DishDto;
import org.LyQing.takeout.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 菜服务
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
public interface DishService extends IService<Dish> {

    /**
     * 新增菜品，同时插入菜品对应的口味数据，需要操作两表
     *
     * @param dishDto 菜dto
     */
    void saveWithFlavor(DishDto dishDto);

    /**
     * 根据id获取菜品信息
     *
     * @param id id
     * @return {@link DishDto}
     */
    DishDto getByIdWithFlavor(Long id);

    /**
     * 更新菜品与味道信息
     *
     * @param dishDto 菜dto
     */
    void updateWithFlavor(DishDto dishDto);
}
