package com.coderscampus.Unit13;

import com.coderscampus.Unit13.models.Recipe;
import com.coderscampus.Unit13.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {
	
	private final RecipeService recipeService;
	
@Autowired
	public RecipeController(RecipeService recipeService) {
	this.recipeService = recipeService;
}

@GetMapping("/all-recipes")
public List<Recipe> getAllRecipes() {
	String filePath = "/Users/jaecaramelsmith/Desktop/Installers/Assignment9.recipe.text.rtf";
	return recipeService.parseRecipesFromCSV(filePath);
}

}
