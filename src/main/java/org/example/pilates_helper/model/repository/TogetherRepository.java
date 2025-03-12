package org.example.pilates_helper.model.repository;

import org.example.pilates_helper.model.dto.APIClientParam;
import org.example.pilates_helper.model.dto.TogetherAPIParam;
import org.example.pilates_helper.util.APIClient;

public class TogetherRepository implements APIClient {
    private TogetherRepository() {}
    private final static TogetherRepository instance = new TogetherRepository();
    public static TogetherRepository getInstance() {
        return instance;
    }

    public String callAPI(TogetherAPIParam param) {
        String url = "";
        String token = "";
        String method = "POST";
        String body = "";
        String[] headers = {};
        return APIClient.super.callAPI(new APIClientParam(url, token, method, body, headers));
    }
}
