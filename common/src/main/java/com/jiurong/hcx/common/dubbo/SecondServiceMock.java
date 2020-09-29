package com.jiurong.hcx.common.dubbo;

import com.jiurong.hcx.common.exception.ClientException;
import com.jiurong.hcx.common.model.second.PlatLog;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description
 */
public class SecondServiceMock implements SecondService {

    @Override
    public PlatLog getPlatLog(String id) {
        PlatLog platLog = new PlatLog();
        platLog.setUsername("111111");
        platLog.setContent("111111");
        return platLog;
    }
}