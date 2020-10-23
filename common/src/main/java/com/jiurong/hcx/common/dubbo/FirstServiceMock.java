package com.jiurong.hcx.common.dubbo;


import com.jiurong.hcx.common.model.first.User;

/**
 * @author hcx
 * @date 2020/10/22
 * explain:
 */
public class FirstServiceMock implements FirstService {
    @Override
    public User getUser(String id) {
        User user = new User();
        user.setId("111");
        user.setPassword("222");
        user.setPassword("333");
        return user;
    }
}
