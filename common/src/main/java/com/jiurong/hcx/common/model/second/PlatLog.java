package com.jiurong.hcx.common.model.second;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiurong.hcx.common.mybatis.annotation.CreateTime;
import com.jiurong.hcx.common.mybatis.annotation.UUID;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description 平台日志
 */
@Data
public class PlatLog implements Serializable {

    @ApiModelProperty(value = "id")
    @UUID
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "模块名")
    private String module;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @CreateTime
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}