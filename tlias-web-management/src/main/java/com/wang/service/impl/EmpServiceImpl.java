package com.wang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.mapper.EmpMapper;
import com.wang.pojo.Emp;
import com.wang.pojo.PageBean;
import com.wang.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    //分页查询员工信息，可条件查询
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

//        Long count = empMapper.count();
//
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);

        //1.设置分页参数
        PageHelper.startPage(page,pageSize);

        //2.执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page p = (Page<Emp>) empList;

        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

        return pageBean;
    }

    //批量删除员工
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    //根据ID查询员工信息
    @Override
    public Emp findById(Integer id) {
        return empMapper.findById(id);
    }

    //更新员工信息
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    //员工登录
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
