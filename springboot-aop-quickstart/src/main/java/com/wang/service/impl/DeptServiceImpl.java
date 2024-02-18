package com.wang.service.impl;

import com.wang.aop.MyLog;
import com.wang.mapper.DeptMapper;
import com.wang.pojo.Dept;
import com.wang.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    //查询全部部门信息
    @MyLog
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //根据ID删除部门
    /*
    * Transactional注解
    * spring事务管理
    * rollbackFor属性为 Exception.class 指定所有异常均需回滚
    * propagation
    * */
    @MyLog
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
            deptMapper.delete(id);
    }

    //新增部门
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    //根据ID查询部门
    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}
