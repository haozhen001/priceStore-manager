package me.zhengjie.modules.pricelibaray.service.transform;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/30 17:06
 **/
public class TimestampConverter implements Converter<Timestamp> {
    @Override
    public Class supportJavaTypeKey() {
        return Timestamp.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Timestamp convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        int value = cellData.getNumberValue().intValue();
        return formatExcelDate(value);
    }

    @Override
    public CellData convertToExcelData(Timestamp timestamp, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    private Timestamp formatExcelDate(int day) {
        LocalDateTime localDate = LocalDateTime.of(1900,1,1,0,0,0);
        return Timestamp.valueOf(localDate.plusDays(day));
    }

}
