package org.LyQing.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.LyQing.takeout.dto.SetMealDto;
import org.LyQing.takeout.entity.Setmeal;

import java.util.List;

/**
 * 套餐服务
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐同时需要保存套餐和菜品的关联关系
     *
     * @param setMealDto 套餐dto
     */
    void saveWithDish(SetMealDto setMealDto);

    /**
     * 删除套餐同时需要删除套餐和菜品的关联关系
     *
     * @param ids id
     */
    void removeWithDish(List<Long> ids);

}
