package me.alnmgdev.sje.Engine.EngineHandler;

import java.awt.*;
import java.util.LinkedList;

//process the grahics
public class ScriptHandler {

    LinkedList<GameScript> scripts = new LinkedList<GameScript>();


    public void Tick(){
        for (int i = 0; i < scripts.size(); i++){
            GameScript script = scripts.get(i);

            script.Tick();
        }
    }
    public void Render(Graphics2D graphics){
        for (int i = 0; i < scripts.size(); i++){
            GameScript script = scripts.get(i);

            script.Render(graphics);
        }
    }
    public void Frame(){
        for (int i = 0; i < scripts.size(); i++){
            GameScript script = scripts.get(i);

            script.Frame();
        }
    }
    public void onStart(){
        for (int i = 0; i < scripts.size(); i++){
            GameScript script = scripts.get(i);

            script.onStart();
        }
    }
    public void afterFirstFrame(){
        for (int i = 0; i < scripts.size(); i++){
            GameScript script = scripts.get(i);

            script.afterFirstFrame();
        }
    }

    public void addScript(GameScript script){
        System.out.println("adicionando o script " + script.getClass().getName());
        this.scripts.add(script);
    }

    public void removeScript(GameScript script){
        System.out.println("removendo o script " + script.getClass().getName());
        this.scripts.remove(script);
    }
    public void changeScript(GameScript addScript, GameScript removeScript){
        System.out.println("trocando do script: "+removeScript.getClass().getName()+ " para o script: "+addScript.getClass().getName());
        addScript(addScript);
        removeScript(removeScript);
        onStart();
    }

}
