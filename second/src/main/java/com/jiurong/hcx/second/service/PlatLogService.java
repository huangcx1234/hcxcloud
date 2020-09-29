package com.jiurong.hcx.second.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiurong.hcx.common.exception.ClientException;
import com.jiurong.hcx.common.model.second.PlatLog;
import com.jiurong.hcx.second.mapper.PlatLogMapper;
import com.jiurong.hcx.second.request.platLog.SavePlatLog;
import com.jiurong.hcx.second.request.platLog.PagePlatLog;
import com.jiurong.hcx.second.request.platLog.UpdatePlatLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description 平台日志
 */
@Service(value = "platLogService")
public class PlatLogService {

    @Value("${page.size}")
    private Integer pageSize;

    @Autowired
    private PlatLogMapper platLogMapper;

    public PlatLog save(SavePlatLog savePlatLog) {
        PlatLog platLog = new PlatLog();
        platLog.setUsername(savePlatLog.getUsername());
        platLog.setModule(savePlatLog.getModule());
        platLog.setContent(savePlatLog.getContent());
        platLogMapper.save(platLog);
        return platLog;
    }

    public void delete(String id) {
        if (platLogMapper.deleteById(id) == 0) {
            throw new ClientException("不存在");
        }
    }

    public PlatLog update(UpdatePlatLog updatePlatLog) {
        PlatLog platLog = platLogMapper.selectById(updatePlatLog.getId());
        if (platLog == null) {
            throw new ClientException("不存在");
        }
        platLog.setUsername(updatePlatLog.getUsername());
        platLog.setModule(updatePlatLog.getModule());
        platLog.setContent(updatePlatLog.getContent());
        platLogMapper.updateById(platLog);
        return platLog;
    }

    public PageInfo<PlatLog> page(PagePlatLog pagePlatLog) {
        PageHelper.startPage(pagePlatLog.getPageNum() == null ? 1 : pagePlatLog.getPageNum(), pagePlatLog.getPageSize() == null ? pageSize : pagePlatLog.getPageSize());
        List<PlatLog> list = platLogMapper.selectBySelective(pagePlatLog.toMap());
        return new PageInfo<>(list);
    }
}