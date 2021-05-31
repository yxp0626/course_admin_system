package com.course.business.controller.admin;

import com.course.server.domain.Chapter;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 9:55
 */

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping(value="/list",produces = "application/json;charset=UTF-8")
    public PageDto list(@RequestBody PageDto pageDto){

        chapterService.list(pageDto);
        return pageDto;

    }
}
