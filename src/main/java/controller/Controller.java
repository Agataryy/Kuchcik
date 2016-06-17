/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import model.Model;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.*;
import view.View;
import view.View;

/**
 *
 * @author Agata
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    private static Model theModel;
    private static View theView;
    
    public static void init() throws IOException, FileNotFoundException, ClassNotFoundException {
        theModel = Model.getInstance();
        theView = View.getInstance();
        
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
                    Controller.updateDishList(Model.getAllDishes());
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
        
        theView.showAllMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.updateDishList(Model.getAllDishes());
            }
        });
        //ActionListenery dla okna AddFrame
        theView.addFrame.ingredientsList.setModel(new DefaultListModel<String>());
        
        theView.addFrame.addIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!theView.addFrame.ingredientTextField.equals("")) {
                    DefaultListModel<String> model = (DefaultListModel) theView.addFrame.ingredientsList.getModel();
                    model.addElement(theView.addFrame.ingredientTextField.getText());
                    theView.addFrame.ingredientsList.setModel(model);
                }
            }
        });
        
        theView.addFrame.deleteIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> model = (DefaultListModel) theView.addFrame.ingredientsList.getModel();
                model.remove(theView.addFrame.ingredientsList.getSelectedIndex());
                theView.addFrame.ingredientsList.setModel(model);
            }
        });
        
        theView.addFrame.addDishConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Controller.isInteger(theView.addFrame.dishPrepTime.getText()) && !theView.addFrame.dishNameTextField.getText().equals("") && !theModel.doesNameExist(theView.addFrame.dishNameTextField.getText())) {
                    String name = theView.addFrame.dishNameTextField.getText();
                    int prepTime = Integer.parseInt(theView.addFrame.dishPrepTime.getText());
                    String description = theView.addFrame.dishDescTextArea.getText();
                    Dish dish = new Dish(name, prepTime, description);
                    DefaultListModel model = (DefaultListModel)theView.addFrame.ingredientsList.getModel();
                    Object[] products = model.toArray();
                    for(Object product : products) {
                        dish.addIngredient((String) product);
                    }
                    Model.addDish(dish);
                    Controller.updateDishList(Model.getAllDishes());
                } else if (theModel.doesNameExist(theView.addFrame.dishNameTextField.getText())) {
                    JOptionPane.showMessageDialog(theView, "Potrawa o tej nazwie już istnieje, nadaj inną nazwę.");
                }
            } 
        });
        
        theView.addFrame.addDishCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.addFrame.dispose();
            }
        });
        
        //ActionListenery dla okna SearchFrame
        
        theView.searchFrame.searchCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theView.searchFrame.dispose();
            }
        });
        
        theView.searchFrame.searchConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashSet<Dish> searchResult = null;
                if(theView.searchFrame.searchByNameRadioButton.isSelected()) {
                   searchResult = theModel.searchDishesByName(theView.searchFrame.searchByNameTextField.getText(), theView.searchFrame.exactNameCheckBox.isSelected());
                } else if (theView.searchFrame.searchByPreparationTimeRadioButton.isSelected()) {
                    if(Controller.isInteger(theView.searchFrame.prepTimeMin.getText()) && Controller.isInteger(theView.searchFrame.prepTimeMax.getText())) {
                        searchResult = theModel.searchDishesByPrepTime(Integer.parseInt(theView.searchFrame.prepTimeMin.getText()), Integer.parseInt(theView.searchFrame.prepTimeMax.getText()));
                    }
                } else if (theView.searchFrame.searchByIngredientsRadioButton.isSelected()) {
                    String logicalStatement;
                    if(theView.searchFrame.allIngredientsCheckBox.isSelected()) {
                        logicalStatement = "and";
                    } else {
                        logicalStatement = "or";
                    }
                    HashSet<String> products = new HashSet<>();
                    String[] parts = theView.searchFrame.ingredientsTextArea.getText().split(", ");
                    for(int i=0;i<parts.length;i++) {
                        products.add(parts[i]);
                    }
                    searchResult = theModel.searchDishesByProducts(products, logicalStatement);
                }
                if(theView.searchFrame.searchByNameRadioButton.isSelected() || theView.searchFrame.searchByPreparationTimeRadioButton.isSelected() ||  theView.searchFrame.searchByIngredientsRadioButton.isSelected()) {
                    Controller.updateDishList(searchResult);
                }
            }
        });
        
        theView.dishList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(((JList)e.getSource()).getSelectedIndex() != -1) 
                {
                    Dish dish = Model.getDishByName((String)((JList)e.getSource()).getSelectedValue());
                    if(!dish.equals(null)) {
                        theView.dishNameLabel.setText(dish.getName());
                        theView.prepTimeLabel.setText(Integer.toString(dish.getPreparationTime())+" min");
                        theView.descTextArea.setText(dish.getDescription());
                        String[] products = new String[dish.getIngredients().size()];
                        HashSet<String> ingredients = dish.getIngredients();
                        int i =0;
                        for(String product : ingredients) {
                            products[i] = product;
                            i++;
                        }
                        theView.ingredientsList.setModel(new AbstractListModel<String>() {
                            @Override


                            public int getSize() {
                               return products.length;

                            }

                            @Override
                            public String getElementAt(int index) {
                                return products[index];
                            }
                        });
                    }
                }
            }
        });
        
        theView.deleteDishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = theView.dishList.getSelectedValue();
                theModel.removeDish(theModel.getDishByName(name));
                Controller.updateDishList(theModel.getAllDishes());
            }
        });
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        Controller.init();
        theView.show();
        
    }
    
    public static void updateDishList(HashSet<Dish> data) {
        
        theView.dishList.setModel(new AbstractListModel<String>() {
            String[] strings = theModel.dishesToStringArray(data);
            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int index) {
                return strings[index];
            }
        });
    }
    
    public static boolean isInteger(String s) {
    return isInteger(s,10);
}

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
}
    
}
