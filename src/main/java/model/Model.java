/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
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
 * @author Agata
 */
public class Model {
    private static  Model instance = new Model();
    private static final File file = new File("przepisy.kch");
    private static HashSet<Dish> dishes = new HashSet<>();
    
    private Model() {
        
    }
    
    public static Model getInstance() {
        return instance;
    }
    
    public static void loadDishes() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        dishes = (HashSet<Dish>) in.readObject();
        in.close();
    }
    
    public static void saveDishes() throws IOException {
        if(!file.exists()) {
            file.createNewFile();
        } 
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(dishes);
        out.close();
        
    }
    public static HashSet<Dish> searchDishesByProducts(HashSet<String> products, String logicalStatement) {
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
    
    public static HashSet<Dish> searchDishesByPrepTime(int minTime, int maxTime) {
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
    
    public static HashSet<Dish> searchDishesByName(String name, boolean exact) {
        HashSet<Dish> result = new HashSet<>();
        if (exact) {
            Model.dishes.stream().filter((d) -> (d.getName().equals(name))).forEach((d) -> {
                result.add(d);
            });
        } else {
            Model.dishes.stream().filter((d) -> (d.getName().matches("(.*)"+name+"(.*)"))).forEach((d) -> {
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
    
    public static String[] dishesToStringArray(HashSet<Dish> data) {
        String[] strings = new String[data.size()];
        int i = 0;
        for(Dish d: data) {
            strings[i] = d.getName();
            i++;
        }
        return strings;
    }
    
    public static boolean doesNameExist(String name) {
        for(Dish d: dishes) {
            if(d.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public static Dish getDishByName(String name) {
        for(Dish d: dishes) {
            if(d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }
    
}

