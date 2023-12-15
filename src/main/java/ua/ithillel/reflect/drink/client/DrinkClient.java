package ua.ithillel.reflect.drink.client;

import ua.ithillel.reflect.anno.Cacheable;
import ua.ithillel.reflect.drink.model.DrinkDbResponse;

public interface DrinkClient {
    @Cacheable
    DrinkDbResponse searchByName(String name);
}
