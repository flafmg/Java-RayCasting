package me.alnmgdev.sje.Game;

import me.alnmgdev.sje.Engine.Engine.EngineHandler;
import me.alnmgdev.sje.Engine.Engine.KeyHandler;
import me.alnmgdev.sje.Engine.EngineHandler.GameScript;
import me.alnmgdev.sje.Engine.EngineHandler.ScriptHandler;
import me.alnmgdev.sje.Game.Objects.SimpleVertcalGravity;
import me.alnmgdev.sje.Game.RayCast.MapMaker;
import me.alnmgdev.sje.Game.RayCast.Objects.MainMap;
import me.alnmgdev.sje.Game.RayCast.Objects.WallObject;
import me.alnmgdev.sje.Game.RayCast.RayCaster;

import java.awt.*;
import java.util.ArrayList;

public class MainScript extends GameScript implements MainMap {
    KeyHandler keyHandler = EngineHandler.getKeyHandler();
    ScriptHandler scriptHandler = EngineHandler.getScriptHandler();


    @Override
    public void afterFirstFrame() {
        walls.add(new WallObject("CLAYBRICKS.png"));
        walls.add(new WallObject("REDBRICKS.png"));
        walls.add(new WallObject("BRICKS.png"));
        walls.add(new WallObject("BRICKSBOTLEFT.png"));
        walls.add(new WallObject("BRICKSBOTMID.png"));
        walls.add(new WallObject("BRICKSBOTRIGHT.png"));
        walls.add(new WallObject("BRICKSTOPLEFT.png"));
        walls.add(new WallObject("BRICKSTOPRIGHT.png"));
        walls.add(new WallObject("CROSSCUBE.png"));
        walls.add(new WallObject("HIGHTECH.png"));
        walls.add(new WallObject("HEXAGONS.png"));
        walls.add(new WallObject("METALTILE.png"));
        walls.add(new WallObject("PIPES.png"));
        walls.add(new WallObject("STARWALLA.png"));
        walls.add(new WallObject("STARWALLB.png"));
        walls.add(new WallObject("PIPES.png"));
        walls.add(new WallObject("CREAKYDOOR.png"));
        walls.add(new WallObject("OFFICEDOOR.png"));
        walls.add(new WallObject("testTexture.png"));
       // ControlWindow controlWindow = new ControlWindow();
        scriptHandler.changeScript(new MapMaker(), this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void Frame() {

    }

    @Override
    public void Tick() {


    }
    @Override
    public void Render(Graphics2D graphics) {
    }


}
