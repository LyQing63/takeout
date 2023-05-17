package org.LyQing.takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.LyQing.takeout.common.R;
import org.LyQing.takeout.entity.Employee;
import org.LyQing.takeout.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yjxx_2022
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return R
     * */
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
     * */
    @PostMapping("/logout")
    public  R<String> logout(HttpServletRequest  request) {
        //清理session
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

}
