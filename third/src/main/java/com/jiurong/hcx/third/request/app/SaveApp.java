package com.jiurong.hcx.third.request.app;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description APP
 */
@Data
public class SaveApp {

    @ApiModelProperty(value = "ios版本")
    @NotBlank(message = "ios版本不能为空")
    private String iosVersion;

    @ApiModelProperty(value = "ios强制更新：1-是，0-否")
    @NotNull(message = "ios强制更新：1-是，0-否不能为空")
    private Integer iosForceUpdate;

    @ApiModelProperty(value = "android版本")
    @NotBlank(message = "android版本不能为空")
    private String androidVersion;

    @ApiModelProperty(value = "android强制更新：1-是，0-否")
    @NotNull(message = "android强制更新：1-是，0-否不能为空")
    private Integer androidForceUpdate;

    @Override
    public String toString() {
        return "{"
                + "ios版本:" + iosVersion
                + "," + "ios强制更新：1-是，0-否:" + iosForceUpdate
                + "," + "android版本:" + androidVersion
                + "," + "android强制更新：1-是，0-否:" + androidForceUpdate
                +"}";
    }
}