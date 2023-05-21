package org.LyQing.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.LyQing.takeout.entity.Category;
import org.LyQing.takeout.mapper.CategoryMapper;
import org.LyQing.takeout.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * 类别服务impl
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

}
