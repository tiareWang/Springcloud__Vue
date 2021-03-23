package com.coursera.server.service;

import com.coursera.server.domain.User;
import com.coursera.server.domain.UserExample;
import com.coursera.server.dto.PageDto;
import com.coursera.server.dto.UserDto;
import com.coursera.server.exception.BusinessException;
import com.coursera.server.exception.BusinessExceptionCode;
import com.coursera.server.mapper.UserMapper;
import com.coursera.server.util.CopyUtil;
import com.coursera.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //分页功能
        UserExample userExample = new UserExample();
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserDto> userDtoList = CopyUtil.copyList(userList, UserDto.class);
        pageDto.setList(userDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(UserDto userDto){
        User user = CopyUtil.copy(userDto, User.class);
        if(StringUtils.isEmpty(userDto.getId())){
            this.insert(user);
        } else {
            this.update(user);
        }
    }

    /**
     * 新增
     */
    private void insert(User user){
        user.setId(UuidUtil.getShortUuid());
        User userDb = this.selectByLoginName(user.getLoginName());
        if(userDb != null){
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);

        }
        userMapper.insert(user);
    }

    /**
     * 更新
     */
    private void update(User user){
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 删除
     */
    public void delete(String id){
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据登录名查询用户信息
     * @param loginName
     * @return
     */
    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
            //loginName是唯一的，所以查出来要么没有记录，要么只有一条记录
        }
    }
}