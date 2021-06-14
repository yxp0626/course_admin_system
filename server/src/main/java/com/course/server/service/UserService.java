package com.course.server.service;

import com.course.server.domain.User;
import com.course.server.domain.UserExample;
import com.course.server.dto.UserDto;
import com.course.server.dto.PageDto;
import com.course.server.exception.BusinessException;
import com.course.server.exception.BusinessExceptionCode;
import com.course.server.mapper.UserMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xiaoping Yu
 * @date 2021/5/28 - 14:38
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
//列表查询
    public void list(PageDto pageDto){
        /*插件分页语句规则:调用startPage方法之后，执行的第一个select语句会进行分页。执行分页查询功能至少需要两条sql，一条是查询总记录数，一条是查当前页的记录。*/
        //当传入的分页参数不合法时，比如0,0时，程序不会报错，而是查全部记录，分页不生效。
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserDto> userDtoList = new ArrayList<>();
        for (int i = 0, l = userList.size(); i < l; i++) {
            User user = userList.get(i);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user,userDto);
            userDtoList.add(userDto);
        }
        pageDto.setList(userList);
    }
//保存操作，id有值的时候更新，无值的时候新增。
    public void save(UserDto userDto){
        User user = CopyUtil.copy(userDto,User.class);
        if (StringUtils.isEmpty(userDto.getId())){
            this.insert(user);
        }
        else {
            this.update(user);
        }
    }

    private void insert(User user){
        user.setId(UuidUtil.getShortUuid());
        User userDb = this.selectByLoginName(user.getLoginName());
        if (userDb != null){
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userMapper.insert(user);
    }

    private void update(User user){
        userMapper.updateByPrimaryKey(user);
    }

    public void delete(String id){
        userMapper.deleteByPrimaryKey(id);
    }


//    根据登录名查询用户信息
    public User selectByLoginName(String loginName){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)){
            return null;
        }
        else {
            return userList.get(0);
        }
    }
}
