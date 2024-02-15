package com.wang.controller;

import com.wang.pojo.Emp;
import com.wang.pojo.Result;
import com.wang.service.EmpService;
import com.wang.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录：{}", emp);

        Emp e = empService.login(emp);

        //登录成功，颁发令牌
        if (e != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            claims.put("name",e.getName());

            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }
}
