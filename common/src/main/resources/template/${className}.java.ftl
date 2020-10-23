<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.common.model.${moduleName};

<#assign dateNum=0>
<#list table.columns as column>
<#if column.simpleJavaType =='Date'&& dateNum ==0>
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
<#assign dateNum=1>
</#if>
<#if column.columnNameLower =='id'>
import ${basePackage}.common.mybatis.annotation.UUID;
</#if>
<#if column.columnNameLower =='createTime'>
import ${basePackage}.common.mybatis.annotation.CreateTime;
</#if>
<#if column.columnNameLower =='updateTime'>
import ${basePackage}.common.mybatis.annotation.UpdateTime;
</#if>
</#list>
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
@Data
public class ${className} implements Serializable {
<#list table.columns as column>

    @ApiModelProperty(value = "${column.remarks}")
    <#if column.columnNameLower =='id'>
    @UUID
    </#if>
    <#if column.columnNameLower =='createTime'>
    @CreateTime
    </#if>
    <#if column.columnNameLower =='updateTime'>
    @UpdateTime
    </#if>
    <#if column.jdbcSqlTypeName =='TIMESTAMP'>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    </#if>
    <#if column.jdbcSqlTypeName =='DATE'>
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};
</#list>
}