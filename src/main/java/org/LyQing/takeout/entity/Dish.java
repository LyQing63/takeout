package org.LyQing.takeout.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
@Data
@Slf4j
public class Dish {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 菜品名字
     */
    private String name;

    /**
     * 菜品分类id
     */
    private Long categoryId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 商品码
     */
    private String code;

    /**
     * 图像
     */
    private String image;

    /**
     * 描述
     */
    private String description;

    /**
     * 0 停售 1 起售
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateUser;

    /**
     * 是否删除
     */
    private Integer isDeleted;

}
