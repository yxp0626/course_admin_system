package com.course.server.service;

import com.course.server.domain.CourseContentFile;
import com.course.server.domain.CourseContentFileExample;
import com.course.server.dto.CourseContentFileDto;
import com.course.server.mapper.CourseContentFileMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 14:38
 */
@Service
public class CourseContentFileService {

    @Resource
    private CourseContentFileMapper courseContentFileMapper;
//列表查询
public List<CourseContentFileDto> list(String courseId) {
    CourseContentFileExample example = new CourseContentFileExample();
    CourseContentFileExample.Criteria criteria = example.createCriteria();
    criteria.andCourseIdEqualTo(courseId);
    List<CourseContentFile> fileList = courseContentFileMapper.selectByExample(example);
    return CopyUtil.copyList(fileList, CourseContentFileDto.class);
}
//保存操作，id有值的时候更新，无值的时候新增。
    public void save(CourseContentFileDto courseContentFileDto){
        CourseContentFile courseContentFile = CopyUtil.copy(courseContentFileDto,CourseContentFile.class);
        if (StringUtils.isEmpty(courseContentFileDto.getId())){
            this.insert(courseContentFile);
        }
        else {
            this.update(courseContentFile);
        }
    }

    private void insert(CourseContentFile courseContentFile){
        courseContentFile.setId(UuidUtil.getShortUuid());
        courseContentFileMapper.insert(courseContentFile);
    }

    private void update(CourseContentFile courseContentFile){
        courseContentFileMapper.updateByPrimaryKey(courseContentFile);
    }

    public void delete(String id){
        courseContentFileMapper.deleteByPrimaryKey(id);
    }
}
