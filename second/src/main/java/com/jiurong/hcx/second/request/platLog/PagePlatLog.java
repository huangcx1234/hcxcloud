package com.jiurong.hcx.second.request.platLog;

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
 * @Description 平台日志
 */
@Data
public class PagePlatLog {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "模块名")
    private String module;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "第几页")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    public Map toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("module", module);
        map.put("content", content);
        return map;
    }
}