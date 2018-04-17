package com.test.opcdemo.httpClient;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;

public class HttpClient {

    private TsdbClient tsdbClient;

    public HttpClient() {
        this.setTsdbClient();
    }

    public TsdbClient getTsdbClient() {
        return tsdbClient;
    }

    private void setTsdbClient() {
        String ACCESS_KEY_ID = "207235136af147568f16218436df18d0";
        String SECRET_ACCESS_KEY = "0521b881bdca47ef8af4617f5099dd46";
        String ENDPOINT = "opctest.tsdb.iot.gz.baidubce.com";
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY))
                .withEndpoint(ENDPOINT);

        this.tsdbClient = new TsdbClient(config);
    }

}
