package ua.ithillel.reflect.drink.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrinkDbResponse {
    private List<Drink> drinks;
}
