package ua.ithillel.reflect.drink.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Drink {
    private String strDrink;
    private String strCategory;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strInstructions;
}
