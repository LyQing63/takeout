package org.LyQing.takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.LyQing.takeout.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜品映射器
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
