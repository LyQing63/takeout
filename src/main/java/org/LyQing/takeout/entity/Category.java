package org.LyQing.takeout.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 类别
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
@Data
public class Category {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 类型
     * 1 菜品; 2 套餐
     */
    private Integer type;

    /**
     * 名字
     */
    private String name;


    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
