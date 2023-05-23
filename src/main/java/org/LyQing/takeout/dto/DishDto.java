package org.LyQing.takeout.dto;

import lombok.Data;
import org.LyQing.takeout.entity.Dish;
import org.LyQing.takeout.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜dto
 *
 * @author yjxx_2022
 * @date 2023/05/23
 */
@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;

}
