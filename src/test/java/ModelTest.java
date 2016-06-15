
import java.io.IOException;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dish;
import model.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Maciej
 */
public class ModelTest{
    public void testAdd() {
        HashSet<Dish> expResult = new HashSet<>();
        expResult.add(new Dish("Spaghetti Bolognese", 60, "Pyszne i pożywne"));
        Model.addDish(new Dish("Spaghetti Bolognese", 60, "Pyszne i pożywne"));
        assertEquals(expResult,Model.getAllDishes());
        
    }
}
