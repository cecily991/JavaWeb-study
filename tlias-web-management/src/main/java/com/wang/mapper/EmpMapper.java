package com.wang.mapper;

import com.wang.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //查询总记录数
    //@Select("select count(*) from emp")
    //public Long count();

    //分页查询获取数据列表
    //@Select("select * from emp limit #{start},#{pageSize}")
    //public List<Emp> page(Integer start, Integer pageSize);

    //@Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    @Insert("insert into emp(username,name,image,gender,job,entrydate,dept_id,create_time,update_time)\n" +
            "         values (#{username},#{name},#{image},#{gender}.,#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void add(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp findById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
