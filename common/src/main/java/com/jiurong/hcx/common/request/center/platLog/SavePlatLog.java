package com.jiurong.hcx.common.request.center.platLog;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author soyeajr
 * @date 2019-3-20
 * @Description 
 */
@Data
@AllArgsConstructor
public class SavePlatLog {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "模块名称")
    @NotBlank(message = "模块名称不能为空")
    private String module;

    @ApiModelProperty(value = "内容")
    @NotBlank(message = "内容不能为空")
    private String content;
}