package com.jiurong.hcx.common.dubbo;

import com.jiurong.hcx.common.model.second.PlatLog;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description
 */
public interface SecondService {
    PlatLog getPlatLog(String id);
}