package me.alnmgdev.sje.Game.RayCast;

import me.alnmgdev.sje.Engine.Engine.EngineHandler;
import me.alnmgdev.sje.Engine.Engine.KeyHandler;
import me.alnmgdev.sje.Engine.EngineHandler.GameScript ;
import me.alnmgdev.sje.Engine.EngineHandler.ScriptHandler;
import me.alnmgdev.sje.Engine.Window.WindowHandler;
import me.alnmgdev.sje.Game.ControlWindow.ControlWindow;
import me.alnmgdev.sje.Game.RayCast.Objects.MainMap;
import me.alnmgdev.sje.Game.RayCast.Objects.WallObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapMaker extends GameScript implements MainMap {
    WindowHandler window= EngineHandler.getWindowHandler();
    ScriptHandler scriptHandler = EngineHandler.getScriptHandler();

    JComboBox comboBox = new JComboBox();
    KeyHandler keyHandler =  EngineHandler.getKeyHandler();
    ControlWindow controlWindow;
    JButton button = new JButton("play!");
    @Override
    public void onStart() {

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //show jdialog when button is clicked
                change();
            }
        });

        window.changeSize(new Dimension(800,800));
        window.changeTitle("SJE - raycast MapMaker");

        controlWindow = new ControlWindow();
        controlWindow.changeTitle("materiais");

        for (WallObject wall: walls) {
            comboBox.addItem(wall.getTextureName());
        }
        controlWindow.add(comboBox);
        controlWindow.add(button);
    }
    public void change(){
        scriptHandler.changeScript(new RayCaster(), this);
        controlWindow.close();
    }

    @Override
    public void Frame() {
    }
    int selectedIndex;
    @Override
    public void Tick() {
        selectedIndex = comboBox.getSelectedIndex();
        changeMapValue();
    }

    void changeMapValue(){
        if( keyHandler.mouseInside){
            int posX = (int) (keyHandler.getMouseX/(mapS/3));
            int posY = (int) (keyHandler.getMouseY/(mapS/3));

            if(keyHandler.mouseLeftPressed) {
                if (posX < 32 && posY < 32) {
                    System.out.println("x:" + posX + "y:" + posY);
                    map[posX][posY] = selectedIndex + 1;
                }
            }if(keyHandler.mouseRightPressed){
                if (posX < 32 && posY < 32) {
                    map[posX][posY] = 0;
                }
            }

        }
    }

    @Override
    public void Render(Graphics2D graphics) {

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, EngineHandler.windowWidht, EngineHandler.windowHeight);

        RenderGrid(graphics);
        renderTexture(graphics);
    }
    public void renderTexture(Graphics2D graphics){
        if(selectedIndex >= 0){
            int yoff = 0;
            int y = 0;
            int xoff = 800;
            for (int i = 0; i < walls.get(selectedIndex).getColors().size(); i++){
                graphics.setColor(walls.get(selectedIndex).getColors().get(i));
                graphics.fillRect((y*14)+ xoff,(yoff*14),14,14);
                y++;

                if(y==32){
                    yoff++;
                    y = 0;
                }
            }
        }
    }
    public void RenderGrid(Graphics2D graphics){
        for (int y = 0; y < mapY; y++){
            for (int x = 0; x < mapX; x++){
                graphics.setColor(Color.DARK_GRAY);
                if(map[x][y] > 0){
                    graphics.setColor(Color.GRAY);
                }
                if(map[x][y] == 18){
                    graphics.setColor(Color.GREEN);
                }
                graphics.fillRect(x*(int)(mapS/3),(int) y*(int)(mapS/3),
                        (mapS/3)-1,(mapS/3)-1);
            }
        }


    }

    @Override
    public void afterFirstFrame() {

    }
}
