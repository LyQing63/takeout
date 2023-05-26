package org.LyQing.takeout.dto;

import lombok.Data;
import org.LyQing.takeout.entity.SetmealDish;
import org.LyQing.takeout.entity.Setmeal;

import java.util.List;

/**
 * 套餐dto
 *
 * @author yjxx_2022
 * @date 2023/05/26
 */
@Data
public class SetMealDto extends Setmeal{

    private List<SetmealDish> setmealDishes;

    private String categoryName;

}
