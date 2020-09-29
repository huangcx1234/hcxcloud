package com.jiurong.hcx.common.util.excel;

import lombok.Data;

import java.util.List;

/**
 * @author soyeajr
 * @date 2019-2-26
 * @Description ExcelSheet实体类
 */
@Data
public class SheetData {
    String name;
    List<String> titles;
    List<List<Object>> rows;
}