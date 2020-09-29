package com.jiurong.hcx.second.dubbo;

import com.jiurong.hcx.common.dubbo.SecondService;
import com.jiurong.hcx.common.model.second.PlatLog;
import com.jiurong.hcx.second.mapper.PlatLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hcx
 * @date 2020/9/18
 * explain:
 */
@Service
@Slf4j
public class SecondServiceImpl implements SecondService {
    @Autowired
    private PlatLogMapper platLogMapper;

    @Override
    public PlatLog getPlatLog(String id) {
        PlatLog platLog = platLogMapper.selectById(id);
        return platLog;
    }
}
