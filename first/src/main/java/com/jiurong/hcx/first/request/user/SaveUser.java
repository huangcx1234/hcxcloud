package com.jiurong.hcx.first.request.user;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description 用户
 */
@Data
public class SaveUser {

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Override
    public String toString() {
        return "{"
                + "用户名:" + username
                + "," + "密码:" + password
                +"}";
    }
}