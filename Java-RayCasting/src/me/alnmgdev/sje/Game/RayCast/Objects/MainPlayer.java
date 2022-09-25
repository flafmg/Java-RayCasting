package me.alnmgdev.sje.Game.RayCast.Objects;

import me.alnmgdev.sje.Engine.EngineHandler.GameObject;

public class MainPlayer extends GameObject {
    private double playerAngle;
    private double playerDeltaX;
    private double playerDeltaY;

    private double playerSpeed = 2.8;

    private float rotationSpeed = 2.8f;

    public MainPlayer(double posX, double posY, float playerAngle) {
        super(posX, posY);
        this.setPlayerAngle(playerAngle);
    }



    public double getPlayerDeltaX() {
        return playerDeltaX;
    }

    public void setPlayerDeltaX(double playerDeltaX) {
        this.playerDeltaX = playerDeltaX;
    }

    public double getPlayerDeltaY() {
        return playerDeltaY;
    }

    public void setPlayerDeltaY(double playerDeltaY) {
        this.playerDeltaY = playerDeltaY;
    }

    public double getPlayerAngle() {
        return playerAngle;
    }

    public void setPlayerAngle(double playerAngle) {
        this.playerAngle = playerAngle;
    }


    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public double getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(double playerSpeed) {
        this.playerSpeed = playerSpeed;
    }
}
