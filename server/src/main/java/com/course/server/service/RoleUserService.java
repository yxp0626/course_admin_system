package com.course.server.service;

import com.course.server.domain.RoleUser;
import com.course.server.domain.RoleUserExample;
import com.course.server.dto.RoleUserDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.RoleUserMapper;
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
public class RoleUserService {

    @Resource
    private RoleUserMapper roleUserMapper;
//列表查询
    public void list(PageDto pageDto){
        /*插件分页语句规则:调用startPage方法之后，执行的第一个select语句会进行分页。执行分页查询功能至少需要两条sql，一条是查询总记录数，一条是查当前页的记录。*/
        //当传入的分页参数不合法时，比如0,0时，程序不会报错，而是查全部记录，分页不生效。
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        RoleUserExample roleUserExample = new RoleUserExample();
        List<RoleUser> roleUserList = roleUserMapper.selectByExample(roleUserExample);
        PageInfo<RoleUser> pageInfo = new PageInfo<>(roleUserList);
        pageDto.setTotal(pageInfo.getTotal());
        List<RoleUserDto> roleUserDtoList = new ArrayList<>();
        for (int i = 0, l = roleUserList.size(); i < l; i++) {
            RoleUser roleUser = roleUserList.get(i);
            RoleUserDto roleUserDto = new RoleUserDto();
            BeanUtils.copyProperties(roleUser,roleUserDto);
            roleUserDtoList.add(roleUserDto);
        }
        pageDto.setList(roleUserList);
    }
//保存操作，id有值的时候更新，无值的时候新增。
    public void save(RoleUserDto roleUserDto){
        RoleUser roleUser = CopyUtil.copy(roleUserDto,RoleUser.class);
        if (StringUtils.isEmpty(roleUserDto.getId())){
            this.insert(roleUser);
        }
        else {
            this.update(roleUser);
        }
    }

    private void insert(RoleUser roleUser){
        roleUser.setId(UuidUtil.getShortUuid());
        roleUserMapper.insert(roleUser);
    }

    private void update(RoleUser roleUser){
        roleUserMapper.updateByPrimaryKey(roleUser);
    }

    public void delete(String id){
        roleUserMapper.deleteByPrimaryKey(id);
    }
}
