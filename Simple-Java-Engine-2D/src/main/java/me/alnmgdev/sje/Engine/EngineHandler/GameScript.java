package me.alnmgdev.sje.Engine.EngineHandler;

import java.awt.*;

public abstract class GameScript{

    public abstract void onStart();
    public abstract void Frame();
    public abstract void Tick();
    public abstract void Render(Graphics2D graphics);

    public abstract void afterFirstFrame();



}
