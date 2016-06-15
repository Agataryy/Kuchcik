/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author Maciej
 */
public class View extends JFrame{
    private static View instance = new View();
    
    private View() {
        //Celowo puste
    }
    
    public static View getInstance() {
        instance.init();
        return View.instance;
    }
    
    public void init() {
        this.setName("Kuchcik 1.0");
        this.setSize(1024,768);
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(1024, 700);
        this.add(mainPanel);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setSize(1024, 68);
        menuBar.add(new JMenu("Plik"));
        this.add(menuBar);
    }
    
    
}
