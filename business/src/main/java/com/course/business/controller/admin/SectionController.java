package com.course.business.controller.admin;

import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.exception.ValidatorException;
import com.course.server.service.SectionService;
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
@RequestMapping("/admin/section")
public class SectionController {

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "小节";

    @Resource
    private SectionService sectionService;

//列表查询
    @PostMapping(value="/list",produces = "application/json;charset=UTF-8")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        sectionService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;

    }
//保存操作，id在数据库中含有时执行更新操作，id在数据库中不
//含有时执行新增操作。
    @PostMapping (value = "/save", produces = "application/json;charset=UTF-8")
    public ResponseDto save(@RequestBody SectionDto sectionDto) {

        ResponseDto responseDto = new ResponseDto();
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json;charset=UTF-8")
        public ResponseDto delete(@PathVariable String id){
            ResponseDto responseDto = new ResponseDto();
            sectionService.delete(id);
            return responseDto;
    }
}