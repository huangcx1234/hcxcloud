package com.jiurong.hcx.second.request.platLog;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description 平台日志
 */
@Data
public class UpdatePlatLog {

    @ApiModelProperty(value = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "模块名")
    @NotBlank(message = "模块名不能为空")
    private String module;

    @ApiModelProperty(value = "内容")
    @NotBlank(message = "内容不能为空")
    private String content;

    @Override
    public String toString() {
        return "{"
                + "id:" + id
                + "," + "用户名:" + username
                + "," + "模块名:" + module
                + "," + "内容:" + content
        +"}";
    }
}