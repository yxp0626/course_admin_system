package com.course.business.controller.admin;

import com.course.server.dto.CourseContentFileDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseContentFileService;
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
@RequestMapping("/admin/course-content-file")
public class CourseContentFileController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseContentFileController.class);
    public static final String BUSINESS_NAME = "课程内容文件";

    @Resource
    private CourseContentFileService courseContentFileService;

//列表查询
    @GetMapping("/list/{courseId}")
    public ResponseDto list(@PathVariable String courseId) {
    ResponseDto responseDto = new ResponseDto();
    List<CourseContentFileDto> fileDtoList = courseContentFileService.list(courseId);
    responseDto.setContent(fileDtoList);
    return responseDto;
}
//保存操作，id在数据库中含有时执行更新操作，id在数据库中不
//含有时执行新增操作。
    @PostMapping (value = "/save", produces = "application/json;charset=UTF-8")
    public ResponseDto save(@RequestBody CourseContentFileDto courseContentFileDto) {
        // 保存校验
            ValidatorUtil.require(courseContentFileDto.getCourseId(), "课程id");
            ValidatorUtil.length(courseContentFileDto.getUrl(), "地址", 1, 100);
            ValidatorUtil.length(courseContentFileDto.getName(), "文件名", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseContentFileService.save(courseContentFileDto);
        responseDto.setContent(courseContentFileDto);
        return responseDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json;charset=UTF-8")
        public ResponseDto delete(@PathVariable String id){
            ResponseDto responseDto = new ResponseDto();
            courseContentFileService.delete(id);
            return responseDto;
    }
}
