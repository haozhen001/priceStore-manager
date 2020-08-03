package me.zhengjie.modules.pricelibaray.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 10:54
 * IT组 商品价格库
 **/
@Entity
@Getter
@Setter
@Table(name="goods_price_it")
public class GoodsPriceIT extends GoodsPriceLibaray implements Serializable {

    @Id
    @NotNull(groups = BaseEntity.Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "等级")
    private String level;

    @ApiModelProperty(value = "地区")
    private String region;

    @ApiModelProperty(value = "驻场")
    private String site;

    @ApiModelProperty(value = "市场价信息来源")
    @Column(name = "market_source")
    private String marketSource;

    @ApiModelProperty(value = "生效起始时间")
    @Column(name = "effectice_start_date")
    private Timestamp effecticeStartDate;

    @ApiModelProperty(value = "生效截止时间")
    @Column(name = "effectice_end_date")
    private Timestamp effecticeEndDate;

}
