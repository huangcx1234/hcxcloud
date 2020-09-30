package com.jiurong.hcx.first.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiurong.hcx.common.exception.ClientException;
import com.jiurong.hcx.common.model.first.User;
import com.jiurong.hcx.first.mapper.UserMapper;
import com.jiurong.hcx.first.request.user.PageUser;
import com.jiurong.hcx.first.request.user.SaveUser;
import com.jiurong.hcx.first.request.user.UpdateUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description 用户
 */
@Service(value = "userService")
@Slf4j
public class UserService {

    @Value("${page.size}")
    private Integer pageSize;

    @Autowired
    private UserMapper userMapper;

    public User save(SaveUser saveUser) {
        User user = new User();
        user.setUsername(saveUser.getUsername());
        user.setPassword(saveUser.getPassword());
        userMapper.save(user);
        return user;
    }

    public void delete(String id) {
        if (userMapper.deleteById(id) == 0) {
            throw new ClientException("不存在");
        }
    }

    public User update(UpdateUser updateUser) {
        User user = userMapper.selectById(updateUser.getId());
        if (user == null) {
            throw new ClientException("不存在");
        }
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        userMapper.updateById(user);
        return user;
    }

    public PageInfo<User> page(PageUser pageUser) {
        log.info("每页数量：{}",pageSize.toString());
        PageHelper.startPage(pageUser.getPageNum() == null ? 1 : pageUser.getPageNum(), pageUser.getPageSize() == null ? pageSize : pageUser.getPageSize());
        List<User> list = userMapper.selectBySelective(pageUser.toMap());
        return new PageInfo<>(list);
    }
}