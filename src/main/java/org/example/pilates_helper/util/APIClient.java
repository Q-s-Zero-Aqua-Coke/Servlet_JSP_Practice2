package org.example.pilates_helper.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.pilates_helper.model.dto.APIClientParam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public interface APIClient {

    private Logger getLogger() {
        return Logger.getLogger(this.getClass().getName());
    }

    private void info(String message)  {
        getLogger().info(message);
    }

    private void error(String message)  {
        getLogger().severe(message);
    }

    default String callAPI(APIClientParam param)  {
        try {
            HttpResponse<String> response = httpClient.send(buildRequest(param), HttpResponse.BodyHandlers.ofString());
            info("%d".formatted(response.statusCode()));
            return response.body();
        } catch (Exception e) {
            error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    HttpClient httpClient = HttpClient.newHttpClient();

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    private HttpRequest buildRequest(APIClientParam param) {

        return HttpRequest.newBuilder()
                .uri(URI.create(param.url()))
                .method(param.method(), HttpRequest.BodyPublishers.ofString(param.body()))
                .headers(param.headers())
                .build();
    }
}
