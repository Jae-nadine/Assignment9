package com.coderscampus.Unit13;

import com.coderscampus.Unit13.models.Recipe;

import com.coderscampus.Unit13.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/all-recipes")
	public List<Recipe> getAllRecipes() {
		return recipeService.parseRecipesFromCSV();
	}
	
	@GetMapping("/gluten-free") 
	public List<Recipe> getGlutenFreeRecipes() {
		return recipeService.parseRecipesFromCSV().stream().filter(Recipe::getGlutenFree).collect(Collectors.toList());
	}
	
	@GetMapping("/vegan")
	public List<Recipe> getVeganRecipes() {
		return recipeService.parseRecipesFromCSV().stream().filter(Recipe::getVegan).collect(Collectors.toList());
	}
	
	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getVeganAndGlutenFreeRecipes() {
		return recipeService.parseRecipesFromCSV().stream().filter(recipe -> recipe.getGlutenFree() && recipe.getVegan()).collect(Collectors.toList());
	}
	
	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarianRecipes() {
		return recipeService.parseRecipesFromCSV().stream().filter(Recipe::getVegetarian).collect(Collectors.toList());
	}
	
}
