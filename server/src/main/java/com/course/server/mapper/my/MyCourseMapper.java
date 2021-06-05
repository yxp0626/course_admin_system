package com.course.server.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * @author Xiaoping Yu
 * @date 2021/6/5 - 14:39
 */
public interface MyCourseMapper {

    int updateTime(@Param("courseId") String courseId);
}
