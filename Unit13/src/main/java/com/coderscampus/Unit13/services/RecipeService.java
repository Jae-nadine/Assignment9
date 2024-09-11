package com.coderscampus.Unit13.services;

import com.coderscampus.Unit13.models.Recipe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service 
public class RecipeService {
	
	public List<Recipe> parseRecipesFromCSV(String filePath) {
		List<Recipe> recipes = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
				
			for (CSVRecord csvRecord : csvParser) {
				Recipe recipe = new Recipe(
					Integer.parseInt(csvRecord.get("cookingMinutes")),
					Boolean.parseBoolean(csvRecord.get("dairyFree")),
					Boolean.parseBoolean(csvRecord.get("glutenFree")),
					csvRecord.get("instructions"),
					Double.parseDouble(csvRecord.get("preparationMinutes")),
					Double.parseDouble(csvRecord.get("pricePerServing")),
					Integer.parseInt(csvRecord.get("readyInMinutes")),
					Integer.parseInt(csvRecord.get("servings")),
					Double.parseDouble(csvRecord.get("spoonacularScore")),
					csvRecord.get("title"),
					Boolean.parseBoolean(csvRecord.get("vegan")),
					Boolean.parseBoolean(csvRecord.get("vegetarian"))
				);
				recipes.add(recipe);
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return recipes;
	}

}












