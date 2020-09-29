package com.jiurong.hcx.third.request.app;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description APP
 */
@Data
public class PageApp {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "ios版本")
    private String iosVersion;

    @ApiModelProperty(value = "ios强制更新：1-是，0-否")
    private Integer iosForceUpdate;

    @ApiModelProperty(value = "android版本")
    private String androidVersion;

    @ApiModelProperty(value = "android强制更新：1-是，0-否")
    private Integer androidForceUpdate;

    @ApiModelProperty(value = "第几页")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    public Map toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("iosVersion", iosVersion);
        map.put("iosForceUpdate", iosForceUpdate);
        map.put("androidVersion", androidVersion);
        map.put("androidForceUpdate", androidForceUpdate);
        return map;
    }
}