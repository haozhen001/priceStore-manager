package me.zhengjie.modules.pricelibaray.service.transform;

import me.zhengjie.modules.pricelibaray.domain.GoodsPriceIT;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceITExcel;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/30 17:30
 **/
public class GoodsPriceITTransform {

    public static GoodsPriceIT ExcelToEntity(GoodsPriceITExcel excel){
        GoodsPriceIT goodsPriceIT = new GoodsPriceIT();
        BeanUtils.copyProperties(excel,goodsPriceIT);
        Timestamp now = Timestamp.from(Instant.now());
        goodsPriceIT.setCreateTime(now);
        return goodsPriceIT;
    }

    public static List<GoodsPriceIT> ExcelToEntity(List<GoodsPriceITExcel> excels){
        List<GoodsPriceIT> goodsPriceITS = new ArrayList<>();
        excels.forEach(excel -> goodsPriceITS.add(ExcelToEntity(excel)));
        return goodsPriceITS;
    }

}
