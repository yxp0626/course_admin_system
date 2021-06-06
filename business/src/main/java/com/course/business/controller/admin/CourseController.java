package com.course.business.controller.admin;

import com.course.server.dto.*;
import com.course.server.service.CourseCategoryService;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 9:55
 */

@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程表";

    @Resource
    private CourseService courseService;

    @Resource
    private CourseCategoryService courseCategoryService;

//查找课程下所有的分类
    @PostMapping(value="/list-category/{courseId}",produces = "application/json;charset=UTF-8")
    public ResponseDto listCategory(@PathVariable(value = "courseId") String courseId){
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> dtoList = courseCategoryService.listByCourse(courseId);
        responseDto.setContent(dtoList);
        return responseDto;
    }

//列表查询
    @PostMapping(value="/list",produces = "application/json;charset=UTF-8")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;

    }
//保存操作，id在数据库中含有时执行更新操作，id在数据库中不
//含有时执行新增操作。
    @PostMapping (value = "/save", produces = "application/json;charset=UTF-8")
    public ResponseDto save(@RequestBody CourseDto courseDto) {
        // 保存校验
            ValidatorUtil.require(courseDto.getName(), "名称");
            ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
            ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
            ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json;charset=UTF-8")
        public ResponseDto delete(@PathVariable String id){
            ResponseDto responseDto = new ResponseDto();
            courseService.delete(id);
            return responseDto;
    }

    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable String id) {
               ResponseDto responseDto = new ResponseDto();
                CourseContentDto contentDto = courseService.findContent(id);
               responseDto.setContent(contentDto);
               return responseDto;
            }

       @PostMapping("/save-content")
    public ResponseDto saveContent(@RequestBody CourseContentDto contentDto) {
                ResponseDto responseDto = new ResponseDto();
                courseService.saveContent(contentDto);
                return responseDto;
            }

            @RequestMapping(value = "/sort")
    public ResponseDto sort(@RequestBody SortDto sortDto) {
        LOG.info("更新排序");
        ResponseDto responseDto = new ResponseDto();
        courseService.sort(sortDto);
        return responseDto;
    }
}
