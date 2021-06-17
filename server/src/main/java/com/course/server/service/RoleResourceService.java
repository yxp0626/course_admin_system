package com.course.server.service;

import com.course.server.domain.RoleResource;
import com.course.server.domain.RoleResourceExample;
import com.course.server.dto.RoleResourceDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.RoleResourceMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 14:38
 */
@Service
public class RoleResourceService {

    @Resource
    private RoleResourceMapper roleResourceMapper;
//列表查询
    public void list(PageDto pageDto){
        /*插件分页语句规则:调用startPage方法之后，执行的第一个select语句会进行分页。执行分页查询功能至少需要两条sql，一条是查询总记录数，一条是查当前页的记录。*/
        //当传入的分页参数不合法时，比如0,0时，程序不会报错，而是查全部记录，分页不生效。
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleResourceExample roleResourceExample = new RoleResourceExample();
        List<RoleResource> roleResourceList = roleResourceMapper.selectByExample(roleResourceExample);
        PageInfo<RoleResource> pageInfo = new PageInfo<>(roleResourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleResourceDto> roleResourceDtoList = new ArrayList<>();
        for (int i = 0, l = roleResourceList.size(); i < l; i++) {
            RoleResource roleResource = roleResourceList.get(i);
            RoleResourceDto roleResourceDto = new RoleResourceDto();
            BeanUtils.copyProperties(roleResource,roleResourceDto);
            roleResourceDtoList.add(roleResourceDto);
        }
        pageDto.setList(roleResourceList);
    }
//保存操作，id有值的时候更新，无值的时候新增。
    public void save(RoleResourceDto roleResourceDto){
        RoleResource roleResource = CopyUtil.copy(roleResourceDto,RoleResource.class);
        if (StringUtils.isEmpty(roleResourceDto.getId())){
            this.insert(roleResource);
        }
        else {
            this.update(roleResource);
        }
    }

    private void insert(RoleResource roleResource){
        roleResource.setId(UuidUtil.getShortUuid());
        roleResourceMapper.insert(roleResource);
    }

    private void update(RoleResource roleResource){
        roleResourceMapper.updateByPrimaryKey(roleResource);
    }

    public void delete(String id){
        roleResourceMapper.deleteByPrimaryKey(id);
    }
}
