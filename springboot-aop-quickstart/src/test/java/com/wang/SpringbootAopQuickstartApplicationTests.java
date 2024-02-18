package com.wang;

import com.wang.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAopQuickstartApplicationTests {

    @Autowired
    private DeptService deptService;

    @Test
    public void testAopDelete(){
        deptService.delete(10);
    }

    @Test
    public void testAopList(){
        deptService.list();
    }

    @Test
    public void testAopFindById(){
        deptService.findById(8);
    }
}
