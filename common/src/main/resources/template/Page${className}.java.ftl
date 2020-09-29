<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.${moduleName}.request.${classNameLower};

<#assign dateNum=0>
<#list table.columns as column>
<#if column.simpleJavaType =='Date'&& dateNum ==0>
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
<#assign dateNum=1>
</#if>
</#list>
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
@Data
public class Page${className} {
<#list table.columns as column>
    <#if column.columnNameLower !='createTime' && column.columnNameLower !='updateTime'>

    @ApiModelProperty(value = "${column.remarks}")
    <#if column.jdbcSqlTypeName =='TIMESTAMP'>
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    </#if>
    <#if column.jdbcSqlTypeName =='DATE'>
    @DateTimeFormat(pattern="yyyy-MM-dd")
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};
    </#if>
</#list>

    @ApiModelProperty(value = "第几页")
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;

    public Map toMap() {
        Map<String, Object> map = new HashMap<>();
    <#list table.columns as column>
        <#if column.columnNameLower !='createTime' && column.columnNameLower !='updateTime'>
        map.put("${column.columnNameLower}", ${column.columnNameLower});
        </#if>
    </#list>
        return map;
    }
}