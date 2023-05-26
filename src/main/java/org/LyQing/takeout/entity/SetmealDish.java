package org.LyQing.takeout.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐菜
 *
 * @author yjxx_2022
 * @date 2023/05/26
 */
@Data
public class SetmealDish {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long setmealId;

    private Long dishId;

    /**
     * 名字(冗余字段)
     */
    private String name;

    /**
     * 菜品原价
     */
    private BigDecimal price;

    /**
     * 份数
     */
    private Integer copies;

    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    private Integer isDeleted;

}
