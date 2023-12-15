package ua.ithillel.reflect.drink.manager;

import lombok.RequiredArgsConstructor;
import ua.ithillel.reflect.drink.client.DrinkClient;
import ua.ithillel.reflect.drink.model.Drink;
import ua.ithillel.reflect.drink.model.DrinkDbResponse;

@RequiredArgsConstructor
public class DefaultDrinkManager implements DrinkManager {
    private final DrinkClient drinkClient;

    @Override
    public Drink getDrinkByName(String name) {
        final DrinkDbResponse drinkDbResponse = drinkClient.searchByName(name);
        final Drink drink = drinkDbResponse.getDrinks().get(0);
        return drink;
    }
}
