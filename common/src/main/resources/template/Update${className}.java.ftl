<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.${moduleName}.request.${classNameLower};

<#assign dateNum=0>
<#list table.columns as column>
<#if column.simpleJavaType =='Date'&& dateNum ==0>
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
<#assign dateNum=1>
</#if>
</#list>
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
@Data
public class Update${className} {
<#list table.columns as column>
    <#if column.columnNameLower !='createTime' && column.columnNameLower !='updateTime'>

    @ApiModelProperty(value = "${column.remarks}")
    <#if column.nullable ==false>
    <#if column.simpleJavaType =='String'>
    @NotBlank(message = "${column.remarks}不能为空")
    <#else>
    @NotNull(message = "${column.remarks}不能为空")
    </#if>
    </#if>
    <#if column.jdbcSqlTypeName =='TIMESTAMP'>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    </#if>
    <#if column.jdbcSqlTypeName =='DATE'>
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};
    </#if>
</#list>

    @Override
    public String toString() {
        return "{"
            <#list table.columns as column>
                <#if column.columnNameLower !='createTime' && column.columnNameLower !='updateTime'>
                <#if column_index != 0>+ "," </#if>+ "${column.remarks}:" + ${column.columnNameLower}
                </#if>
            </#list>
        +"}";
    }
}