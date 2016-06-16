/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        theView.closeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        
        theView.saveMenuItem.addActionListener(new ActionListener() {
            @Override
            @SuppressWarnings("empty-statement")
            public void actionPerformed(ActionEvent e) {
                try {
                    Model.saveDishes();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
;
            }
            
        });
        
        theView.loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Model.loadDishes();
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        theView.showAllMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.setDishList(Model.dishesToStringArray());
            }
        });
        
        theView.addMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.addFrame.show();
            }
        });
        
        theView.searchMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.searchFrame.show();
            }
        });
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        Controller.init();
        theView.show();
        
    }
    
}
