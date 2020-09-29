package com.jiurong.hcx.third.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiurong.hcx.common.exception.ClientException;
import com.jiurong.hcx.common.model.third.App;
import com.jiurong.hcx.third.mapper.AppMapper;
import com.jiurong.hcx.third.request.app.SaveApp;
import com.jiurong.hcx.third.request.app.PageApp;
import com.jiurong.hcx.third.request.app.UpdateApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description APP
 */
@Service(value = "appService")
public class AppService {

    @Value("${page.size}")
    private Integer pageSize;

    @Autowired
    private AppMapper appMapper;

    public App save(SaveApp saveApp) {
        App app = new App();
        app.setIosVersion(saveApp.getIosVersion());
        app.setIosForceUpdate(saveApp.getIosForceUpdate());
        app.setAndroidVersion(saveApp.getAndroidVersion());
        app.setAndroidForceUpdate(saveApp.getAndroidForceUpdate());
        appMapper.save(app);
        return app;
    }

    public void delete(String id) {
        if (appMapper.deleteById(id) == 0) {
            throw new ClientException("不存在");
        }
    }

    public App update(UpdateApp updateApp) {
        App app = appMapper.selectById(updateApp.getId());
        if (app == null) {
            throw new ClientException("不存在");
        }
        app.setIosVersion(updateApp.getIosVersion());
        app.setIosForceUpdate(updateApp.getIosForceUpdate());
        app.setAndroidVersion(updateApp.getAndroidVersion());
        app.setAndroidForceUpdate(updateApp.getAndroidForceUpdate());
        appMapper.updateById(app);
        return app;
    }

    public PageInfo<App> page(PageApp pageApp) {
        PageHelper.startPage(pageApp.getPageNum() == null ? 1 : pageApp.getPageNum(), pageApp.getPageSize() == null ? pageSize : pageApp.getPageSize());
        List<App> list = appMapper.selectBySelective(pageApp.toMap());
        return new PageInfo<>(list);
    }
}