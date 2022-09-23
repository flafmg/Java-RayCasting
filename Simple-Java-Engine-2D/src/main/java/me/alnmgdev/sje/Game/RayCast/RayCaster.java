package me.alnmgdev.sje.Game.RayCast;

import me.alnmgdev.sje.Engine.Engine.EngineHandler;
import me.alnmgdev.sje.Engine.Engine.KeyHandler;
import me.alnmgdev.sje.Engine.EngineHandler.GameScript;
import me.alnmgdev.sje.Engine.EngineHandler.ScriptHandler;
import me.alnmgdev.sje.Engine.Window.WindowHandler;
import me.alnmgdev.sje.Game.ControlWindow.ControlWindow;
import me.alnmgdev.sje.Game.RayCast.Objects.MainMap;
import me.alnmgdev.sje.Game.RayCast.Objects.MainPlayer;
import me.alnmgdev.sje.Game.RayCast.Objects.WallObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RayCaster extends GameScript implements MainMap {
    KeyHandler keyHandler = EngineHandler.getKeyHandler();
    WindowHandler windowHandler = EngineHandler.getWindowHandler();
    MainPlayer player = new MainPlayer((mapS * 2) - mapS / 2, (mapS * 2) - mapS / 2, 0);
    ScriptHandler scriptHandler = EngineHandler.getScriptHandler();


    ControlWindow controlWindow = new ControlWindow();
    JButton button = new JButton("editar mapa");
    @Override
    public void onStart() {
        System.out.println("hello world");
        windowHandler.changeSize(new Dimension(1280,800));
        windowHandler.changeTitle("SJE - DDA RAYCASTING");
        win = false;

        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //show jdialog when button is clicked
                change();
            }
        });

        controlWindow.add(button);


        //scriptHandler.changeScript(new MapMaker(), this);

    }
    public void change(){
        controlWindow.close();
        scriptHandler.changeScript(new MapMaker(), this);
    }



    @Override
    public void Frame() {

    }

    @Override
    public void Tick() {

        movePlayer();
        checkPlayerPos();
    }

    public void checkPlayerPos(){

        int px = (int)(player.getPosX()/mapS);
        int py = (int)(player.getPosX()/mapS);

        //if exit
        if(map[px][py] == 17){
            win = true;
            System.out.println("aaaaaaaaaa");
        }

    }

    boolean win;
    public void movePlayer() {
        //double newX = player.getPosX(), newY = player.getPosY();

        if (keyHandler.upPressed || keyHandler.Wpressed) {
            player.setPosY((player.getPosY() + player.getPlayerDeltaY()));
            player.setPosX((player.getPosX() + player.getPlayerDeltaX()));
        }
        if (keyHandler.downPressed || keyHandler.Spressed) {
            player.setPosY((player.getPosY() - player.getPlayerDeltaY()));
            player.setPosX((player.getPosX() - player.getPlayerDeltaX()));
        }

        if (keyHandler.leftPressed) {
            if (player.getPlayerAngle() <= 0) {
                player.setPlayerAngle(player.getPlayerAngle() + Math.toRadians(360));
            }
            player.setPlayerAngle(player.getPlayerAngle() - Math.toRadians(player.getRotationSpeed()));
            player.setPlayerDeltaX(Math.cos(player.getPlayerAngle()) * player.getPlayerSpeed());
            player.setPlayerDeltaY(Math.sin(player.getPlayerAngle()) * player.getPlayerSpeed());

        }
        if (keyHandler.rightPressed) {

            if (player.getPlayerAngle() >= Math.toRadians(360)) {
                player.setPlayerAngle(player.getPlayerAngle() - Math.toRadians(360));
            }
            player.setPlayerAngle(player.getPlayerAngle() + Math.toRadians(player.getRotationSpeed()));
            player.setPlayerDeltaX(Math.cos(player.getPlayerAngle()) * player.getPlayerSpeed());
            player.setPlayerDeltaY(Math.sin(player.getPlayerAngle()) * player.getPlayerSpeed());

        }

    }


    float mapscale = 4;

    @Override
    public void Render(Graphics2D graphics) {
        //render blank background

        graphics.setColor(new Color(128,128,128));
        graphics.fillRect(0,0,
                EngineHandler.windowWidht, EngineHandler.windowHeight/2);

        graphics.setColor(new Color(64,64,64));
        graphics.fillRect(0,EngineHandler.windowHeight/2
                ,EngineHandler.windowWidht, EngineHandler.windowHeight/2);

        RenderRay(graphics);
       // RenderMap(graphics);
        // RenderPlayer(graphics);

    }



    @Override
    public void afterFirstFrame() {

    }


    int playersize = 8;

    void RenderPlayer(Graphics2D graphics) {
        float scaledplayersize = playersize / mapscale;

        graphics.setColor(Color.red);
        graphics.fillOval(
                (int) ((player.getPosX() - scaledplayersize / 2) / mapscale),
                (int) ((player.getPosY() - scaledplayersize / 2) / mapscale),
                playersize, playersize
        );
    }


    public void RenderRay(Graphics2D graphics) {

        int rays = EngineHandler.windowWidht/resolution;
        double anglestep = (double) fov/ (double) rays;
        double initialangle = player.getPlayerAngle() - Math.toRadians(fov/2);

        for(int r = 0; r < rays; r++) {

            Ray ray = new Ray(player.getPosX(),player.getPosY(), initialangle, player.getPlayerAngle());
            ray.CastRay();

            //drawn lines

            float lineH = (float) ((mapS * EngineHandler.windowHeight) / ray.getDistance());

            float ty_step = (32.0f/(float)lineH);
            float ty_off = 0;

            if (lineH > EngineHandler.windowHeight) {
                ty_off = (lineH-EngineHandler.windowHeight)/2;
                lineH = EngineHandler.windowHeight;
            }
            float lineO = (EngineHandler.windowHeight - lineH) / 2;



            float ty = ty_off*ty_step;
            float tx = 0 ;

            if(ray.isVertical()) {
                tx = (int)(ray.getRayPosY()/2)%32 ;
                if (ray.getRayAngle() > Math.toRadians(90) && ray.getRayAngle() < Math.toRadians(270)) {
                    tx = 31 - tx;
                }
            }else {
                tx = (int)(ray.getRayPosX()/2)%32 ;
                if (ray.getRayAngle() < Math.toRadians(180)) {
                    tx = 31 - tx;
                }
            }


            //draw textures
            for (int y = 0; y < lineH; y++) {
                //int tid = ray.getMapCell();
                if((int)ty*32 > 1023){
                    ty = 0;
                }

                Color c = new Color(0,0,0,0);
                int mid = ray.getMapCell()-1;


                   //System.out.println(ray.getMapCell());
                  c = walls.get(mid).getColors().get((int) ty * 32 + (int) (tx));

                   graphics.setColor(c);

                   if (ray.isVertical()) {
                       graphics.setColor(c.darker());
                   }

                    graphics.fillRect(r * resolution, (int) (y + lineO), resolution, resolution);
                    ty += ty_step;


            }

            //add anglestap and recast
            initialangle+= Math.toRadians(anglestep);
            if(initialangle >Math.toRadians(360)){
                initialangle-=Math.toRadians(360);
            }
            if(initialangle < 0){
                initialangle +=Math.toRadians(360);
            }

        }
    }




    void RenderMap(Graphics2D graphics){
            for(int y = 0; y < mapY; y++) {
                for(int x = 0; x < mapX; x++) {
                    int x1 = mapS*x;
                    int y1 = mapS*y;

                    if(map[y][x] > 0){
                        graphics.setColor(new Color(255,255,255,150));
                    }else {
                        graphics.setColor(new Color(0,0,0,0));
                    }
                    graphics.fillRect((int)(x1/mapscale), (int)(y1/mapscale),(int)(mapS/mapscale) ,(int)(mapS/mapscale));

            }

        }
    }
}

