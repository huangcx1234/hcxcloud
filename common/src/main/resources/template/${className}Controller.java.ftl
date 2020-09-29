<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.${moduleName}.controller;

import com.github.pagehelper.PageInfo;
import ${basePackage}.common.exception.ClientException;
import ${basePackage}.common.config.annotation.OperateLog;
import ${basePackage}.common.model.${moduleName}.${className};
import ${basePackage}.${moduleName}.request.${classNameLower}.Save${className};
import ${basePackage}.${moduleName}.request.${classNameLower}.Page${className};
import ${basePackage}.${moduleName}.request.${classNameLower}.Update${className};
import ${basePackage}.${moduleName}.service.${className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author ${author}
 * @date ${createTime}
 * @Description ${tableComment}
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(description = "${tableComment}")
@Slf4j
public class ${className}Controller {

    @Autowired
    private ${className}Service ${classNameLower}Service;

    @OperateLog(module = "${tableComment}", operate = "新增")
    @ApiOperation(value = "新增")
    @PostMapping("/${classNameLower}s")
    public ${className} save(@RequestBody @Valid Save${className} save${className}) {
        return ${classNameLower}Service.save(save${className});
    }

    @OperateLog(module = "${tableComment}", operate = "删除")
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "path", required = true)
    @DeleteMapping("/${classNameLower}s/{id}")
    public void delete(@PathVariable("id") String id) {
        ${classNameLower}Service.delete(id);
    }

    @OperateLog(module = "${tableComment}", operate = "修改")
    @ApiOperation(value = "修改")
    @PutMapping("/${classNameLower}s")
    public ${className} update(@RequestBody @Valid Update${className} update${className}) {
        return ${classNameLower}Service.update(update${className});
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/${classNameLower}s")
    public PageInfo<${className}> page(Page${className} page${className}) {
        return ${classNameLower}Service.page(page${className});
    }
}