package org.LyQing.takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.entity.DishFlavor;
import org.LyQing.takeout.mapper.DishFlavorMapper;
import org.LyQing.takeout.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
