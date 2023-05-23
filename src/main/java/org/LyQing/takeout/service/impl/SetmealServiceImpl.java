package org.LyQing.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.entity.Setmeal;
import org.LyQing.takeout.mapper.SetmealMapper;
import org.LyQing.takeout.service.SetmealService;
import org.springframework.stereotype.Service;

/**
 * 套餐服务
 *
 * @author yjxx_2022
 * @date 2023/05/21
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
