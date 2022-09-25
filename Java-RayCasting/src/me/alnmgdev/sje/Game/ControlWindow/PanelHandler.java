package me.alnmgdev.sje.Game.ControlWindow;

import me.alnmgdev.sje.Engine.Window.WindowHandler;

import javax.swing.*;
import java.awt.*;

public class PanelHandler extends JPanel {
    WindowHandler windowHandler;
    int screenX = 640, screenY = 480;
    public void changeTitle(String title){
        windowHandler.changeTitle(title);
    }
    public PanelHandler(){
        windowHandler = new WindowHandler("control panel", new Dimension(screenX, screenY),
                true, this);
        windowHandler.StartWindow();
    }
    public void close(){
        windowHandler.dispose();
    }
}
