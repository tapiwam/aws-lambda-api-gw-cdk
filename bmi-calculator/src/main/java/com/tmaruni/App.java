package com.tmaruni;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class App  implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        RequestInput bodyInput = extractRequestInput(input.getBody());
        double result = calculateBodyMassIndex(bodyInput.getHeight(), bodyInput.getWeight());

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent().withHeaders(responseHeaders());
        return response
                .withStatusCode(200)
                .withBody(getApiResponse(result));
    }

    private RequestInput extractRequestInput(String body) {
        log.info("Extracting request body: {}", body);
        return new Gson().fromJson(body, RequestInput.class);
    }

    private String getApiResponse(double result) {
        return String.format("{ \"result\": %s }", result);
    }

    private Map<String, String> responseHeaders (){
        Map<String, String> responseHeaders = new HashMap<>();
        responseHeaders.put("Content-Type", "application/json");
        return responseHeaders;
    }

    private double calculateBodyMassIndex(double height, double weight) {
        double bmi = weight / Math.pow(height, 2);
        double bmiRounded = Math.round(bmi * 10);
        return  bmiRounded / 10;
    }
}
