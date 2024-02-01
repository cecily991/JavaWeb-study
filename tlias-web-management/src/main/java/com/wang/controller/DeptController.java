package com.wang.controller;

import com.wang.pojo.Dept;
import com.wang.pojo.Emp;
import com.wang.pojo.Result;
import com.wang.service.DeptService;
import com.wang.service.EmpService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  //日志记录
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping("/depts")
    @GetMapping("")
    public Result list(){
        log.info("查询全部部门信息");

        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);

        deptService.delete(id);

        return Result.success();
    }

    @PostMapping("")
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);

        deptService.add(dept);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result listById(@PathVariable Integer id){
        log.info("根据ID查询部门：{}",id);

        Dept dept = deptService.findById(id);

        return Result.success(dept);
    }

    @PutMapping("")
    public Result update(@RequestBody Dept dept){
        log.info("根据ID修改部门：{}",dept);

        deptService.update(dept);

        return Result.success();
    }
}
