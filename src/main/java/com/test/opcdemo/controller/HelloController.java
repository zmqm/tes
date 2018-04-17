package com.test.opcdemo.controller;

import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.TsdbConstants;
import com.baidubce.services.tsdb.model.Aggregator;
import com.baidubce.services.tsdb.model.Filters;
import com.baidubce.services.tsdb.model.Query;
import com.baidubce.services.tsdb.model.QueryDatapointsResponse;
import com.test.opcdemo.httpClient.HttpClient;
import com.test.opcdemo.param.getDataParam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

    private TsdbClient tsdbClient = new HttpClient().getTsdbClient();

    @RequestMapping(value="/getData",method = RequestMethod.POST)
    public QueryDatapointsResponse getData(@RequestBody getDataParam param){
        List<Query> queries = Arrays.asList(new Query()                          // 创建Query对象
                .withMetric(param.getMETRIC())                                   // 设置metric
                .withField(param.getFIELD())                                     // 设置查询的域名，不设置表示查询默认域
                .withFilters(new Filters()                                       // 创建Filters对象
                        .withRelativeStart("50 seconds ago"))                    // 设置相对的开始时间，这里设置为2秒前
                .withLimit(100)                                                  // 设置返回数据点数目限制
                .addAggregator(new Aggregator()                                  // 创建Aggregator对象
                        .withName(TsdbConstants.AGGREGATOR_NAME_SUM)             // 设置聚合函数为Sum
                        .withSampling("1 second")));                             // 设置采样\
        System.out.println(tsdbClient.queryDatapoints(queries));
        return tsdbClient.queryDatapoints(queries);
    }
}
