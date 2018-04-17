package com.test.opcdemo;

import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.TsdbConstants;
import com.baidubce.services.tsdb.model.Aggregator;
import com.baidubce.services.tsdb.model.Filters;
import com.baidubce.services.tsdb.model.Query;
import com.baidubce.services.tsdb.model.QueryDatapointsResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

    private TsdbClient tsdbClient = new HttpClient().tsdbClient;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String say(){
        return "hello";
    }

    @RequestMapping(value="/getData",method = RequestMethod.POST)
    public QueryDatapointsResponse getData(@RequestParam("METRIC") String METRIC,
                                           @RequestParam("FIELD") String FIELD){
        List<Query> queries = Arrays.asList(new Query()                          // 创建Query对象
                .withMetric(METRIC)                                              // 设置metric
                .withField(FIELD)                                                // 设置查询的域名，不设置表示查询默认域
                .withFilters(new Filters()                                       // 创建Filters对象
                        .withRelativeStart("50 seconds ago"))                    // 设置相对的开始时间，这里设置为2秒前
                .withLimit(100)                                                  // 设置返回数据点数目限制
                .addAggregator(new Aggregator()                                  // 创建Aggregator对象
                        .withName(TsdbConstants.AGGREGATOR_NAME_SUM)             // 设置聚合函数为Sum
                        .withSampling("1 second")));                             // 设置采样
        return tsdbClient.queryDatapoints(queries);
    }
}
