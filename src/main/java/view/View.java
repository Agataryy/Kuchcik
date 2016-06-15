/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author Maciej
 */
public class View extends JFrame{
    private JList dishesList = new JList();
    private JTextPane dishDesc = new JTextPane();
    private JPanel mainPanel = new JPanel();
    private static View instance = new View();
    
    private View() {
        //Celowo puste
    }
    
    public static View getInstance() {
        instance.init();
        return View.instance;
    }
    
    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("Kuchcik 1.0");
        this.setSize(1024,768);
        this.setLayout(new FlowLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setSize(1024, 700);
        this.add(mainPanel);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setSize(1024, 68);
        JMenu fileMenu = new JMenu("Plik");
        JMenuItem load = new JMenuItem("Wczytaj dane");
        JMenuItem save = new JMenuItem("Zapisz dane");
        JMenuItem quit = new JMenuItem("Wyjdź");
        fileMenu.add(load);
        fileMenu.add(save);
        fileMenu.add(quit);
        menuBar.add(fileMenu);
        JMenu programMenu = new JMenu("Program");
        JMenuItem search = new JMenuItem("Wyszukaj");
        JMenuItem showAll = new JMenuItem("Pokaż wszystko");
        programMenu.add(search);
        programMenu.add(showAll);
        menuBar.add(programMenu);
        
        this.setJMenuBar(menuBar);
        this.mainPanel.setLayout(new FlowLayout());
        this.mainPanel.add(this.dishesList);
        this.getContentPane().add(this.mainPanel);
    }
    
    
}
