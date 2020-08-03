package me.zhengjie.modules.pricelibaray.service.dto;

import lombok.Getter;
import lombok.Setter;
import me.zhengjie.annotation.Query;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 15:06
 **/
@Getter
@Setter
public class GoodsPriceITCriteria implements Serializable {

    @Query
    private Long id;

    @Query(type = Query.Type.INNER_LIKE)
    private String goodsDescribe;

    @Query(type = Query.Type.INNER_LIKE)
    private String supplier;

    @Query
    private String type;

    @Query
    private Integer delflag = 0;

    @Query
    private String level;

    @Query(type = Query.Type.INNER_LIKE)
    private String region;

    @Query(type = Query.Type.INNER_LIKE)
    private String site;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> effecticeStartDate;
}
