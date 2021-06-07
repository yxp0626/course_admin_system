package com.course.business.controller.admin;

import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.dto.TeacherDto;
import com.course.server.service.TeacherService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 9:55
 */

@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    public static final String BUSINESS_NAME = "讲师";

    @Resource
    private TeacherService teacherService;

//列表查询
    @PostMapping(value="/list",produces = "application/json;charset=UTF-8")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        teacherService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;

    }
//保存操作，id在数据库中含有时执行更新操作，id在数据库中不
//含有时执行新增操作。
    @PostMapping (value = "/save", produces = "application/json;charset=UTF-8")
    public ResponseDto save(@RequestBody TeacherDto teacherDto) {
        // 保存校验
            ValidatorUtil.require(teacherDto.getName(), "姓名");
            ValidatorUtil.length(teacherDto.getName(), "姓名", 1, 50);
            ValidatorUtil.length(teacherDto.getNickname(), "昵称", 1, 50);
            ValidatorUtil.length(teacherDto.getImage(), "头像", 1, 100);
            ValidatorUtil.length(teacherDto.getPosition(), "职位", 1, 50);
            ValidatorUtil.length(teacherDto.getMotto(), "座右铭", 1, 50);
            ValidatorUtil.length(teacherDto.getIntro(), "简介", 1, 500);

        ResponseDto responseDto = new ResponseDto();
        teacherService.save(teacherDto);
        responseDto.setContent(teacherDto);
        return responseDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json;charset=UTF-8")
        public ResponseDto delete(@PathVariable String id){
            ResponseDto responseDto = new ResponseDto();
            teacherService.delete(id);
            return responseDto;
    }
}
