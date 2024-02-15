package com.wang.service.impl;

import com.wang.mapper.DeptMapper;
import com.wang.mapper.EmpMapper;
import com.wang.pojo.Dept;
import com.wang.pojo.DeptLog;
import com.wang.pojo.Emp;
import com.wang.service.DeptLogService;
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

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    //查询全部部门信息
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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.delete(id);

            empMapper.deleteByDeptId(id); //删除该部门下的员工
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }
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
