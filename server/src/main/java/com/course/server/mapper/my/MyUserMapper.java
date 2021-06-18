package com.course.server.mapper.my;

/**
 * @author Xiaoping Yu
 * @date 2021/6/18 - 15:25
 */
import com.course.server.dto.ResourceDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyUserMapper {

    List<ResourceDto> findResources(@Param("userId") String userId);

}

