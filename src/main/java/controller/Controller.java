/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.Model;
import javax.swing.JFrame;
import model.*;
import view.View;
import view.ViewTest;

/**
 *
 * @author Maciej
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    private static Model theModel;
    private static ViewTest theView;
    
    public static void init() throws IOException, FileNotFoundException, ClassNotFoundException {
        theModel = new Model();
        theView = new ViewTest();
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        Controller.init();
        theView.show();
        
    }
    
}
