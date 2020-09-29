package com.jiurong.hcx.common.dubbo;

import com.jiurong.hcx.common.model.first.User;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description
 */
public interface FirstService {
    User getUser(String id);
}