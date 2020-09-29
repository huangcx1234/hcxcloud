package com.jiurong.hcx.second.controller;

import com.github.pagehelper.PageInfo;
import com.jiurong.hcx.common.config.annotation.OperateLog;
import com.jiurong.hcx.common.dubbo.FirstService;
import com.jiurong.hcx.common.model.first.User;
import com.jiurong.hcx.common.model.second.PlatLog;
import com.jiurong.hcx.second.request.platLog.PagePlatLog;
import com.jiurong.hcx.second.request.platLog.SavePlatLog;
import com.jiurong.hcx.second.request.platLog.UpdatePlatLog;
import com.jiurong.hcx.second.service.PlatLogService;
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
 * @Description 平台日志
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(description = "平台日志")
@Slf4j
public class PlatLogController {

    @Autowired
    private PlatLogService platLogService;
    @Reference
    private FirstService firstService;


    @OperateLog(module = "平台日志", operate = "新增")
    @ApiOperation(value = "新增")
    @PostMapping("/platLogs")
    public PlatLog save(@RequestBody @Valid SavePlatLog savePlatLog) {
        return platLogService.save(savePlatLog);
    }

    @OperateLog(module = "平台日志", operate = "删除")
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping("/platLogs/{id}")
    public void delete(@PathVariable("id") String id) {
        platLogService.delete(id);
    }

    @OperateLog(module = "平台日志", operate = "修改")
    @ApiOperation(value = "修改")
    @PutMapping("/platLogs")
    public PlatLog update(@RequestBody @Valid UpdatePlatLog updatePlatLog) {
        return platLogService.update(updatePlatLog);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/platLogs")
    public PageInfo<PlatLog> page(PagePlatLog pagePlatLog) {
        return platLogService.page(pagePlatLog);
    }

    @ApiOperation(value = "单个查询")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @GetMapping("/users/{id}")
    public User get(@PathVariable("id") String id) {
        return firstService.getUser(id);
    }
}