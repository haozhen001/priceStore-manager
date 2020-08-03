package me.zhengjie.modules.pricelibaray.service.dto;

import me.zhengjie.annotation.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 15:32
 **/
public class GoodsPriceSparePartCriteria implements Serializable {

    @Query
    private Long id;

    @Query
    private String materialNumber;

    @Query
    private String category;

    @Query
    private String type;

    @Query
    private String inputer;

    @Query
    private String applyDepart;

    @Query
    private Timestamp effecticeDate;

    @Query(type = Query.Type.INNER_LIKE)
    private String supplier;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @Query(type = Query.Type.GREATER_THAN,propName = "noTaxPrice")
    private BigDecimal pricelt;

    @Query(type = Query.Type.LESS_THAN,propName = "noTaxPrice")
    private BigDecimal pricegl;

    @Query(type = Query.Type.GREATER_THAN,propName = "marketPrice")
    private BigDecimal marketPricelt;

    @Query(type = Query.Type.LESS_THAN,propName = "marketPrice")
    private BigDecimal marketPricegl;



}
