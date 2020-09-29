package com.jiurong.hcx.first.dubbo;

import com.jiurong.hcx.common.dubbo.FirstService;
import com.jiurong.hcx.common.model.first.User;
import com.jiurong.hcx.first.mapper.UserMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hcx
 * @date 2020/9/18
 * explain:
 */
@Service
public class FirstServiceImpl implements FirstService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String id) {
        return userMapper.selectById(id);
    }
}
