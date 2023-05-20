package org.LyQing.takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.common.R;
import org.LyQing.takeout.entity.Employee;
import org.LyQing.takeout.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 员工控制器
 *
 * @author yjxx_2022
 * @date 2023/05/18
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 登录
     * 员工登录
     *
     * @param request  请求
     * @param employee 员工
     * @return {@link R}<{@link Employee}>
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        //对密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //判断是否查询到
        if (emp == null) {
            return R.error("登陆失败");
        }
        //密码比对
        if (!emp.getPassword().equals(password)) {
            return R.error("登陆失败");
        }
        //查看状态
        if (emp.getStatus() == 0) {
            return R.error("账户已禁用");
        }

        //登录成功，放入将id放入session
        request.getSession().setAttribute("employee", emp.getId());

        return  R.success(emp);
    }

    /**
     * 员工退出
     *
     * @param request 请求
     * @return {@link R}<{@link String}>
     */
    @PostMapping("/logout")
    public  R<String> logout(HttpServletRequest  request) {
        //清理session
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     *
     * @param request  请求
     * @param employee 员工
     * @return {@link R}<{@link String}>
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工， 员工信息:{}", employee);
        //设置初始密码123456，需要md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());

        //Long empId = (Long) request.getSession().getAttribute("employee");
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    /**
     * 员工分页
     *
     * @param page     页面
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link R}<{@link Page}>
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);

        //分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //添加排序条件
        queryWrapper.orderByAsc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     *
     * @param employee 员工
     * @return {@link R}<{@link String}>
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.toString());

//        long id = Thread.currentThread().getId();
//        log.info("线程id位:{}", id);

//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);
        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }

    /**
     * 通过id查询员工信息
     *
     * @param id id
     * @return {@link R}<{@link Employee}>
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {

        log.info("根据id查询员工信息");
        Employee emp = employeeService.getById(id);

        if (emp != null) {
            return R.success(emp);
        } else {
            return R.error("没有查询到对应的员工信息");
        }

    }

}
