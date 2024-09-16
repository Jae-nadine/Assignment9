package com.coderscampus.Unit13.services;

import com.coderscampus.Unit13.models.Recipe;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

	public List<Recipe> parseRecipesFromCSV() throws IOException {
		List<Recipe> recipes = new ArrayList<>();
		
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("recipes.csv");
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withQuote('"'))) {
			
				System.out.println("CSV Headers: " + csvParser.getHeaderMap().keySet());
				
			for (CSVRecord csvRecord : csvParser) {
				try {
					
					Integer cookingMinutes = parseInteger(csvRecord.get("Cooking Minutes").trim());
					Boolean dairyFree = Boolean.parseBoolean(csvRecord.get("Dairy Free").trim());
					Boolean glutenFree = Boolean.parseBoolean(csvRecord.get("Gluten Free").trim());
					String instructions = csvRecord.get("Instructions").trim();
					Double preparationMinutes = parseDouble(csvRecord.get("Preparation Minutes").trim());
					Double pricePerServing = parseDouble(csvRecord.get("Price Per Serving").trim());
					Integer readyInMinutes = parseInteger(csvRecord.get("Ready In Minutes").trim());
					Integer servings = parseInteger(csvRecord.get("Servings").trim());
					Double spoonacularScore = parseDouble(csvRecord.get("Spoonacular Score").trim());
					String title = csvRecord.get("Title").trim();
					Boolean vegan = Boolean.parseBoolean(csvRecord.get("Vegan").trim());
					Boolean vegetarian = Boolean.parseBoolean(csvRecord.get("Vegetarian").trim());
				
				Recipe recipe = new Recipe(
						cookingMinutes, dairyFree, glutenFree, instructions, preparationMinutes, pricePerServing, 
						readyInMinutes, servings, spoonacularScore, title, vegan, vegetarian);
				
				recipes.add(recipe);
			} catch (NumberFormatException e) {
				System.out.println("Invalid number format in CSV: " + csvRecord.toString());
			}}}
		return recipes;
			}

	private Integer parseInteger(String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private Double parseDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}

//
