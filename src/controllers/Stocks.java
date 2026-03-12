package controllers;

import java.util.HashMap;
import java.util.Map;

public class Stocks {
    public static Stocks instance = new Stocks();

    public static Map<String, Integer> seeds = new HashMap<>(Map.of(
            "Patate", 0,
            "Maïs", 0
    ));

    public static Map<String, Integer> stocks = new HashMap<>(Map.of(
            "Patate", 0,
            "Maïs", 0
    ));

    public void addSeeds(String plant, int qty) {
        seeds.put(plant, seeds.getOrDefault(plant, 0) + qty);
    }

    public boolean useSeed(String plant) {
        int current = seeds.getOrDefault(plant, 0);
        if (current <= 0) return false;
        seeds.put(plant, current - 1);
        return true;
    }

    public void add(String plant, int qty) {
        stocks.put(plant, stocks.getOrDefault(plant, 0) + qty);
    }
}