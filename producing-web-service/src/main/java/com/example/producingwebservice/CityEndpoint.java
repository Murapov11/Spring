package com.example.producingwebservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import io.spring.guides.gs_producing_web_service.GetCityRequest;
import io.spring.guides.gs_producing_web_service.GetCityResponse;

@Endpoint
public class CityEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CityRepository cityRepository;

    @Autowired
    public CityEndpoint(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCityRequest")
    @ResponsePayload
    public GetCityResponse getCity(@RequestPayload GetCityRequest request) {
        GetCityResponse response = new GetCityResponse();
        response.setCity(cityRepository.findCity(request.getName()));

        return response;
    }
}