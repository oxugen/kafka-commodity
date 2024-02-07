package com.oxuegen.springcoreproducer;

import com.oxuegen.springcoreproducer.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityService {
    private static final Map<String, Commodity> COMMODITY_MAP = new HashMap();
    private static final String COPPER = "Copper";
    private static final String GOLD = "Gold";
    private static final double MAX_ADJUSTMENT = 1.05d;
    private static final double MIN_ADJUSTMENT = 0.95d;

    static {
        var timestamp = System.currentTimeMillis();

        COMMODITY_MAP.put(COPPER, new Commodity(COPPER, 5_900.57, "tonne", timestamp));
        COMMODITY_MAP.put(GOLD, new Commodity(GOLD, 1_895.29, "ounce", timestamp));
    }

    public Commodity createDummyCommodity(String name) {
        if (!COMMODITY_MAP.containsKey(name)) {
            throw new IllegalArgumentException("Invalid commodity: " + name);
        }

        var commodity = COMMODITY_MAP.get(name);
        var basePrice = commodity.getPrice();
        var newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT, MAX_ADJUSTMENT);

        commodity.setPrice(newPrice);
        commodity.setTimestamp(System.currentTimeMillis());

        return commodity;
    }


    public List<Commodity> createDummyCommodities(){
        var result = new ArrayList<Commodity>();
        COMMODITY_MAP.keySet().forEach(c -> result.add(createDummyCommodity(c)));

        return result;
    }
}
