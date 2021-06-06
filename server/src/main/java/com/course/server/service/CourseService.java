package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 14:38
 */
@Service
public class CourseService {

    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

//列表查询
    public void list(PageDto pageDto){
        /*插件分页语句规则:调用startPage方法之后，执行的第一个select语句会进行分页。执行分页查询功能至少需要两条sql，一条是查询总记录数，一条是查当前页的记录。*/
        //当传入的分页参数不合法时，比如0,0时，程序不会报错，而是查全部记录，分页不生效。
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (int i = 0, l = courseList.size(); i < l; i++) {
            Course course = courseList.get(i);
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course,courseDto);
            courseDtoList.add(courseDto);
        }
        pageDto.setList(courseList);
    }
//保存操作，id有值的时候更新，无值的时候新增。
    @Transactional
    public void save(CourseDto courseDto){
        Course course = CopyUtil.copy(courseDto,Course.class);
        if (StringUtils.isEmpty(courseDto.getId())){
            this.insert(course);
        }
        else {
            this.update(course);
        }

//批量保存课程的分类
        courseCategoryService.saveBatch(course.getId(),courseDto.getCategorys());
    }

    private void insert(Course course){
        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    private void update(Course course){
    course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    public void delete(String id){
        courseMapper.deleteByPrimaryKey(id);
    }

//    更新课程时长
    public void updateTime(String courseId){
        LOG.info("更新课程时长:{}",courseId);
        myCourseMapper.updateTime(courseId);
    }
}
