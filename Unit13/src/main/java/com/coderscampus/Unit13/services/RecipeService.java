package com.coderscampus.Unit13.services;

import com.coderscampus.Unit13.models.Recipe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service 
public class RecipeService {
	
	public List<Recipe> parseRecipesFromCSV() {
		List<Recipe> recipes = new ArrayList<>();
		
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Recipe.txt");
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().withQuote('"'))) {
			
				System.out.println("CSV Headers: " + csvParser.getHeaderMap().keySet());
				
			for (CSVRecord csvRecord : csvParser) {
				
				System.out.println("CSV Record: " + csvRecord.toString());
				
				Recipe recipe = new Recipe(
					Integer.parseInt(csvRecord.get("Cooking Minutes")),
					Boolean.parseBoolean(csvRecord.get("Dairy Free")),
					Boolean.parseBoolean(csvRecord.get("Gluten Free")),
					csvRecord.get("Instructions"),
					Double.parseDouble(csvRecord.get("Preparation Minutes")),
					Double.parseDouble(csvRecord.get("Price Per Serving")),
					Integer.parseInt(csvRecord.get("Ready In Minutes")),
					Integer.parseInt(csvRecord.get("Servings")),
					Double.parseDouble(csvRecord.get("Spoonacular Score")),
					csvRecord.get("Title"),
					Boolean.parseBoolean(csvRecord.get("Vegan")),
					Boolean.parseBoolean(csvRecord.get("Vegetarian"))
				);
				recipes.add(recipe);
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return recipes;
	}

}





//






