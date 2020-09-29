package com.jiurong.hcx.common.model.third;

import com.jiurong.hcx.common.mybatis.annotation.UUID;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiurong.hcx.common.mybatis.annotation.CreateTime;
import com.jiurong.hcx.common.mybatis.annotation.UpdateTime;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author soyeajr
 * @date 2020-9-18
 * @Description APP
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class App {

    @ApiModelProperty(value = "id")
    @UUID
    private String id;

    @ApiModelProperty(value = "ios版本")
    private String iosVersion;

    @ApiModelProperty(value = "ios强制更新：1-是，0-否")
    private Integer iosForceUpdate;

    @ApiModelProperty(value = "android版本")
    private String androidVersion;

    @ApiModelProperty(value = "android强制更新：1-是，0-否")
    private Integer androidForceUpdate;

    @ApiModelProperty(value = "创建时间")
    @CreateTime
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @UpdateTime
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
}