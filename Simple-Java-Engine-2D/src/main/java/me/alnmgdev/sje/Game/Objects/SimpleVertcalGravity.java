package me.alnmgdev.sje.Game.Objects;

import me.alnmgdev.sje.Engine.EngineHandler.GameObject;

public class SimpleVertcalGravity extends GameObject {

    public double velY;
    public double gravityScale = 0.5;

    public SimpleVertcalGravity(double posX, double posY) {
        super(posX, posY);
    }

    public void calculateVelY(){
        velY -= gravityScale;
        setPosY(getPosY()-velY);
    }
}
