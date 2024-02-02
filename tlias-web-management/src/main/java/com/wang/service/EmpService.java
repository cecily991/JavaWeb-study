package com.wang.service;

import com.wang.pojo.Emp;
import com.wang.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void add(Emp emp);

    Emp findById(Integer id);

    void update(Emp emp);
}
