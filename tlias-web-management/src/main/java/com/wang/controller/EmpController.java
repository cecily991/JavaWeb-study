package com.wang.controller;

import com.wang.mapper.EmpMapper;
import com.wang.pojo.Emp;
import com.wang.pojo.PageBean;
import com.wang.pojo.Result;
import com.wang.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);

        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);

        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除操作，ids:{}", ids);

        empService.delete(ids);

        return Result.success();
    }

    @PostMapping("")
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工：{}", emp);

        empService.add(emp);

        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("根据ID查询员工信息：{}",id);

        Emp emp = empService.findById(id);

        return Result.success(emp);
    }

    @PutMapping("")
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}",emp);

        empService.update(emp);

        return Result.success();
    }

}
