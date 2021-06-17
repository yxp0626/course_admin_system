package com.course.server.service;

import com.course.server.domain.Resource;
import com.course.server.domain.ResourceExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResourceDto;
import com.course.server.mapper.ResourceMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 14:38
 */
@Service
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;
//列表查询
    public void list(PageDto pageDto){
        /*插件分页语句规则:调用startPage方法之后，执行的第一个select语句会进行分页。执行分页查询功能至少需要两条sql，一条是查询总记录数，一条是查当前页的记录。*/
        //当传入的分页参数不合法时，比如0,0时，程序不会报错，而是查全部记录，分页不生效。
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ResourceExample resourceExample = new ResourceExample();
        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ResourceDto> resourceDtoList = new ArrayList<>();
        for (int i = 0, l = resourceList.size(); i < l; i++) {
            Resource resource = resourceList.get(i);
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resource,resourceDto);
            resourceDtoList.add(resourceDto);
        }
        pageDto.setList(resourceList);
    }
//保存操作，id有值的时候更新，无值的时候新增。
    public void save(ResourceDto resourceDto){
        Resource resource = CopyUtil.copy(resourceDto,Resource.class);
        if (StringUtils.isEmpty(resourceDto.getId())){
            this.insert(resource);
        }
        else {
            this.update(resource);
        }
    }

    private void insert(Resource resource){
        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }

    private void update(Resource resource){
        resourceMapper.updateByPrimaryKey(resource);
    }

    public void delete(String id){
        resourceMapper.deleteByPrimaryKey(id);
    }
}
