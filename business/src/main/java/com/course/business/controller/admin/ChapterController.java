package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
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
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    public static final String BUSINESS_NAME = "大章";

    @Resource
    private ChapterService chapterService;

//列表查询
    @PostMapping(value="/list",produces = "application/json;charset=UTF-8")
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto){
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(chapterPageDto.getCourseId(),"课程ID");
        chapterService.list(chapterPageDto);
        responseDto.setContent(chapterPageDto);
        return responseDto;

    }
//保存操作，id在数据库中含有时执行更新操作，id在数据库中不//含有时执行新增操作。
    @PostMapping (value = "/save", produces = "application/json;charset=UTF-8")
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {

            ValidatorUtil.require(chapterDto.getName(),"名称");
            ValidatorUtil.require(chapterDto.getCourseId(),"课程ID");
            ValidatorUtil.length(chapterDto.getCourseId(),"课程ID",1,8);

        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json;charset=UTF-8")
        public ResponseDto delete(@PathVariable String id){
            ResponseDto responseDto = new ResponseDto();
            chapterService.delete(id);
            return responseDto;
    }
}
