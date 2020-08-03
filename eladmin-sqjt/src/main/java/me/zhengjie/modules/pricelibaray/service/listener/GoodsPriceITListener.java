package me.zhengjie.modules.pricelibaray.service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import me.zhengjie.modules.pricelibaray.service.GoodsPriceITService;
import me.zhengjie.modules.pricelibaray.service.dto.GoodsPriceITExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author haozhen
 * @program 设备后台监控系统
 * @description 长庆油田油厂采集系统
 * @email 624536203@qq.com
 * @create 2020/7/30 17:50
 **/
public class GoodsPriceITListener extends AnalysisEventListener<GoodsPriceITExcel> {


    private List<GoodsPriceITExcel> datas;

    private GoodsPriceITService goodsPriceITService;

    public GoodsPriceITListener(GoodsPriceITService goodsPriceITService){
        this.goodsPriceITService = goodsPriceITService;
    }

    /**
     * 解析excel每行数据都会进行调用
     * @param excel
     * @param analysisContext
     */
    @Override
    public void invoke(GoodsPriceITExcel excel, AnalysisContext analysisContext) {
            if(Objects.equals(null,datas)){
                datas = new ArrayList<>();
            }else{
                if(datas.size()>100){
                    goodsPriceITService.saveExcelData(datas);
                    datas = new ArrayList<>();
                }
                datas.add(excel);
            }
    }

    /**
     * 解析完sheet后才会调用
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        goodsPriceITService.saveExcelData(datas);
        datas = null;
    }

    /**
     * 是否进行下一行的解析
     * @param context
     * @return
     */
    @Override
    public boolean hasNext(AnalysisContext context) {
        return super.hasNext(context);
    }

    /**
     * 处理异常信息
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        super.onException(exception, context);
    }
}
