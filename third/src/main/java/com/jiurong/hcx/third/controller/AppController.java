package com.jiurong.hcx.third.controller;

import com.github.pagehelper.PageInfo;
import com.jiurong.hcx.common.config.annotation.OperateLog;
import com.jiurong.hcx.common.dubbo.FirstService;
import com.jiurong.hcx.common.dubbo.SecondService;
import com.jiurong.hcx.common.model.first.User;
import com.jiurong.hcx.common.model.second.PlatLog;
import com.jiurong.hcx.common.model.third.App;
import com.jiurong.hcx.third.request.app.PageApp;
import com.jiurong.hcx.third.request.app.SaveApp;
import com.jiurong.hcx.third.request.app.UpdateApp;
import com.jiurong.hcx.third.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description APP
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(description = "APP")
@Slf4j
public class AppController {

    @Autowired
    private AppService appService;
    @Reference
    private FirstService firstService;
    @Reference
    private SecondService secondService;


    @OperateLog(module = "APP", operate = "新增")
    @ApiOperation(value = "新增")
    @PostMapping("/apps")
    public App save(@RequestBody @Valid SaveApp saveApp) {
        return appService.save(saveApp);
    }

    @OperateLog(module = "APP", operate = "删除")
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping("/apps/{id}")
    public void delete(@PathVariable("id") String id) {
        appService.delete(id);
    }

    @OperateLog(module = "APP", operate = "修改")
    @ApiOperation(value = "修改")
    @PutMapping("/apps")
    public App update(@RequestBody @Valid UpdateApp updateApp) {
        return appService.update(updateApp);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/apps")
    public PageInfo<App> page(PageApp pageApp) {
        return appService.page(pageApp);
    }

    @ApiOperation(value = "单个查询")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") String id) {
        return firstService.getUser(id);
    }

    @ApiOperation(value = "单个查询")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping("/platLogs/{id}")
    public PlatLog getPlatLog(@PathVariable("id") String id) {
        return secondService.getPlatLog(id);
    }

}