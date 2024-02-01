package com.wang.service;

import com.wang.pojo.Dept;
import com.wang.pojo.Emp;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    Dept findById(Integer id);

    void update(Dept dept);
}
