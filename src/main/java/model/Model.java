/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.reflect.Array.set;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import model.Dish;

/**
 *
 * @author Maciej
 */
public class Model {
    private static final String file = "/przepisy.xml";
    private static HashSet<Dish> dishes;
    
    public Model() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Model.loadDishes();
    }
    
    public static void loadDishes() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        dishes = (HashSet<Dish>) in.readObject();
    }
    
    public static void saveDishes() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(dishes);
        out.close();
        
    }
    public static Set<Dish> searchDishesByProducts(HashSet<String> products, String logicalStatement) {
        HashSet<Dish> result = new HashSet<>();
        if(logicalStatement.equals("or")) {
            for (Dish d : Model.dishes) {
                    for(String product : products) {
                        if(d.ingredients.contains(product)) {
                            result.add(d);
                            break;
                    }
                }
            } 
                    
        } else if (logicalStatement.equals("and")) {
            for (Dish d: Model.dishes) {
                if(d.ingredients.containsAll(products)) {
                    result.add(d);
                }
            }
        }
        return result;
    }
    
    public static Set<Dish> searchDishesByPrepTime(int minTime, int maxTime) {
        HashSet<Dish> result = new HashSet<>();
        if(maxTime < minTime) {
            return null;
        }
        if(minTime == 0) {
            for (Dish d : Model.dishes) {
                if(d.getPreparationTime() <= maxTime) {
                    result.add(d);
                }
            }
        } else if(maxTime == 0) {
            for (Dish d : Model.dishes) {
                if(d.getPreparationTime() >= minTime) {
                    result.add(d);
                }
            }
        } else {
            for (Dish d : Model.dishes) {
                if(d.getPreparationTime() >= minTime && d.getPreparationTime() <= maxTime) {
                    result.add(d);
                }
            }
        }
        return result;
    }
    
    public static Set<Dish> searchDishesByName(String name, boolean exact) {
        HashSet<Dish> result = new HashSet<>();
        if (exact) {
            Model.dishes.stream().filter((d) -> (d.getName().equals(name))).forEach((d) -> {
                result.add(d);
            });
        } else {
            Model.dishes.stream().filter((d) -> (d.getName().matches(name))).forEach((d) -> {
                result.add(d);
            });
        }
        return result;
    }
    
    public static void addDish(Dish dish) {
        Model.dishes.add(dish);
    }
    
    public static void removeDish(Dish dish) {
        Model.dishes.remove(dish);
    }
    
    public static void addDishes(Set<Dish> dishes) {
        for(Dish d: dishes) {
            Model.addDish(d);
        }
    }
    
    public static void removeDishes(Set<Dish> dishes) {
        for(Dish d: dishes) {
            Model.removeDish(d);
        }
    }
    
    public static HashSet<Dish> getAllDishes() {
        return Model.dishes;
    }
    
    public static String[] dishesToStringArray() {
        String[] strings = new String[Model.dishes.size()];
        int i = 0;
        for(Dish d: dishes) {
            strings[i] = d.getName();
            i++;
        }
        return strings;
    }
}

