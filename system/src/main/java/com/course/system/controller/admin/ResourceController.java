package com.course.system.controller.admin;

import com.course.server.dto.ResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.exception.ValidatorException;
import com.course.server.service.ResourceService;
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
@RequestMapping("/admin/resource")
public class ResourceController {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);
    public static final String BUSINESS_NAME = "资源";

    @Resource
    private ResourceService resourceService;

//列表查询
    @PostMapping(value="/list",produces = "application/json;charset=UTF-8")
    public ResponseDto list(@RequestBody PageDto pageDto){
        ResponseDto responseDto = new ResponseDto();
        resourceService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;

    }
//保存操作，id在数据库中含有时执行更新操作，id在数据库中不
//含有时执行新增操作。
    @PostMapping (value = "/save", produces = "application/json;charset=UTF-8")
    public ResponseDto save(@RequestBody ResourceDto resourceDto) {
        // 保存校验
            ValidatorUtil.require(resourceDto.getName(), "名称");
            ValidatorUtil.length(resourceDto.getName(), "名称", 1, 100);
            ValidatorUtil.length(resourceDto.getPage(), "页面", 1, 50);
            ValidatorUtil.length(resourceDto.getRequest(), "请求", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        resourceService.save(resourceDto);
        responseDto.setContent(resourceDto);
        return responseDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json;charset=UTF-8")
        public ResponseDto delete(@PathVariable String id){
            ResponseDto responseDto = new ResponseDto();
            resourceService.delete(id);
            return responseDto;
    }
}
