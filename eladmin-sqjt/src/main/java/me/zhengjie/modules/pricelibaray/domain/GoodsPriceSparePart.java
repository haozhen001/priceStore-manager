package me.zhengjie.modules.pricelibaray.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 10:55
 * 备件组 商品价格库
 **/
@Entity
@Getter
@Setter
@Table(name="goods_price_sparepart")
public class GoodsPriceSparePart
        extends GoodsPriceLibaray implements Serializable {

    @Id
    @NotNull(groups = BaseEntity.Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID", hidden = true)
    private Long id;


    @Column(name = "category", updatable = false)
    @ApiModelProperty(value = "大类")
    private String category;

    @ApiModelProperty(value = "类别")
    private String type;


    @JsonFormat(pattern="yyyy-MM")
    @ApiModelProperty(value = "生效日期年月")
    @Column(name = "effectice_date", updatable = false)
    private Timestamp effecticeDate;
}
