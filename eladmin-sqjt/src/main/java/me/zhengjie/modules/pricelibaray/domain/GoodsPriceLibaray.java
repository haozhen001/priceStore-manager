package me.zhengjie.modules.pricelibaray.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.zhengjie.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/28 10:13
 **/
@Getter
@Setter
@MappedSuperclass
public class GoodsPriceLibaray extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "商品标识")
    @Column(name = "goods_id")
    private String goodsId;

    @ApiModelProperty(value = "标的描述")
    @Column(name = "goods_describe")
    private String goodsDescribe;

    @ApiModelProperty(value = "规格描述")
    @Column(name = "specification_describe")
    private String specificationDescribe;

    @ApiModelProperty(value="单位")
    private String unit;

    @ApiModelProperty(value = "未税单价")
    @Column(name = "no_tax_price")
    private BigDecimal noTaxPrice;

    @ApiModelProperty(value = "税率")
    @Column(name = "tax_rate")
    private BigDecimal taxRate;

    @ApiModelProperty(value = "供应商")
    @Column(name = "supplier")
    private String supplier;

    @ApiModelProperty(value = "物料号")
    @Column(name = "material_number")
    private String materialNumber;

    @ApiModelProperty(value = "电商价")
    @Column(name = "elec_price")
    private BigDecimal elecPrice;

    @ApiModelProperty(value = "市场价")
    @Column(name = "market_price")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "录入者")
    @Column(name = "inputer")
    private String inputer;

    @ApiModelProperty(value = "申请部门")
    @Column(name = "apply_depart")
    private String applyDepart;

    @ApiModelProperty(value = "删除标识 0未删除 1删除")
    @Column(name = "delflag")
    private Integer delflag = 0;

}
