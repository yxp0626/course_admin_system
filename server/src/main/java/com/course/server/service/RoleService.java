package com.course.server.service;

import com.course.server.domain.Role;
import com.course.server.domain.RoleExample;
import com.course.server.dto.RoleDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.RoleMapper;
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
public class RoleService {

    @Resource
    private RoleMapper roleMapper;
//列表查询
    public void list(PageDto pageDto){
        /*插件分页语句规则:调用startPage方法之后，执行的第一个select语句会进行分页。执行分页查询功能至少需要两条sql，一条是查询总记录数，一条是查当前页的记录。*/
        //当传入的分页参数不合法时，比如0,0时，程序不会报错，而是查全部记录，分页不生效。
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleExample roleExample = new RoleExample();
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (int i = 0, l = roleList.size(); i < l; i++) {
            Role role = roleList.get(i);
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(role,roleDto);
            roleDtoList.add(roleDto);
        }
        pageDto.setList(roleList);
    }
//保存操作，id有值的时候更新，无值的时候新增。
    public void save(RoleDto roleDto){
        Role role = CopyUtil.copy(roleDto,Role.class);
        if (StringUtils.isEmpty(roleDto.getId())){
            this.insert(role);
        }
        else {
            this.update(role);
        }
    }

    private void insert(Role role){
        role.setId(UuidUtil.getShortUuid());
        roleMapper.insert(role);
    }

    private void update(Role role){
        roleMapper.updateByPrimaryKey(role);
    }

    public void delete(String id){
        roleMapper.deleteByPrimaryKey(id);
    }
}
