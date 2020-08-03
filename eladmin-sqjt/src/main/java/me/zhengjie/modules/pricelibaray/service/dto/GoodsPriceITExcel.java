package me.zhengjie.modules.pricelibaray.service.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.modules.pricelibaray.service.transform.TimestampConverter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/30 16:58
 **/
@Data
public class GoodsPriceITExcel {

    @ExcelProperty(value = "标的描述")
    private String goodsDescribe;


    @ExcelProperty(value="单位")
    private String unit;

    @ExcelProperty(value = "未税单价")
    private BigDecimal noTaxPrice;

    @ExcelProperty(value = "税率")
    private BigDecimal taxRate;

    @ExcelProperty(value = "供应商")
    private String supplier;


    @ExcelProperty(value = "市场价")
    private BigDecimal marketPrice;




    @ExcelProperty(value = "类型")
    private String type;

    @ExcelProperty(value = "等级")
    private String level;

    @ExcelProperty(value = "地区")
    private String region;

    @ExcelProperty(value = "驻场")
    private String site;

    @ApiModelProperty(value = "市场行情")
    @Column(name = "market_source", updatable = false)
    private String marketSource;

    @ExcelProperty(value = "生效起始时间",converter = TimestampConverter.class)
    @Column(name = "effectice_start_date", updatable = false)
    private Timestamp effecticeStartDate;

    @ExcelProperty(value = "生效截止时间",converter = TimestampConverter.class)
    @Column(name = "effectice_end_date", updatable = false)
    private Timestamp effecticeEndDate;
}
