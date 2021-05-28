package com.course.system.controller;

import com.course.server.domain.Test;
import com.course.server.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 9:55
 */

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping(value="/test",produces = "application/json;charset=UTF-8")
    public List<Test> test(){

        return testService.list();

    }
}
