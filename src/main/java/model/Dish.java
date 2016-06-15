/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Set;

/**
 *
 * @author Maciej
 */
public class Dish {
    private int id;
    private String name;
    private int preparationTime; //in minutes
    private String description;
    Set<String> ingredients;
    
    public Dish(String name, int prepTime, String description) {
        if((!name.isEmpty()) && (name != null)) {
            this.description = description;
            this.preparationTime = prepTime;
            this.name = name;
        }
    }
    
    public Dish(String name, int prepTime, String description, int id) {
        if((!name.isEmpty()) && (name != null)) {
            this.description = description;
            this.preparationTime = prepTime;
            this.name = name;
            this.id = id;
        }
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    public void setPreparationTime(int prepTime) {
        this.preparationTime = prepTime;
    }
    
    public int getPreparationTime() {
        return this.preparationTime;
    }
    
    public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }
    
    public boolean removeIngredient(String ingredient) {
        return this.ingredients.remove(ingredient);
        
    }
    
    public boolean hasIngredient(String ingredient) {
        return this.ingredients.contains(ingredient);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
