package me.alnmgdev.sje.Engine.Window;

import javax.swing.*;
import java.awt.*;

public class WindowHandler extends JFrame{
    String title;
    Dimension screenSize;
    boolean lockSize;
    Component content;

    public WindowHandler(String title ,Dimension screenSize, boolean lockSize, Component content){
        this.content = content;

        this.title = title;
        this.screenSize = screenSize;
        this.lockSize = lockSize;
    }

    public void changeTitle(String title){
        this.title = title;
        this.setTitle(title);
    }
    public void addWindowComponent(Component component){
        this.add(component);
        System.out.println(component + " added");
    }
    public void changeSize(Dimension screenSize){
        this.screenSize = screenSize;
        this.setSize(screenSize);
        this.setPreferredSize(screenSize);

        this.setPreferredSize(screenSize);

            this.setMinimumSize(screenSize);
            this.setMinimumSize(screenSize);
            this.setResizable(false);

    }

    public void StartWindow(){

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }

        this.setTitle(title);

        this.setPreferredSize(screenSize);
        if(lockSize){
            this.setMinimumSize(screenSize);
            this.setMinimumSize(screenSize);
            this.setResizable(false);
        }


        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(content);
        this.setVisible(true);

    }
}
