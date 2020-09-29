<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.${moduleName}.mapper;

import ${basePackage}.common.model.${moduleName}.${className};
import ${basePackage}.common.mybatis.BaseMapper;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
public interface ${className}Mapper extends BaseMapper<${className}> {
}