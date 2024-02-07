package com.oxuegen.springcoreproducer.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oxuegen.springcoreproducer.CommodityProducer;
import com.oxuegen.springcoreproducer.entity.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommodityScheduler {
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CommodityProducer commodityProducer;

    @Scheduled(fixedRate = 5000)
    public void fetchCommodities(){
        var commodities = restTemplate.exchange("http://localhost:8085/api/commodity/v1/all", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();
            commodities.forEach(t -> {
                try {
                    commodityProducer.sendMessage(t);
                } catch (JsonProcessingException e){
                    e.printStackTrace();
                }

            });
    }
}
