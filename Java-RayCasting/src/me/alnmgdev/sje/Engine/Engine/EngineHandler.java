package me.alnmgdev.sje.Engine.Engine;

import me.alnmgdev.sje.Engine.EngineHandler.ScriptHandler;
import me.alnmgdev.sje.Engine.Window.WindowHandler;
import me.alnmgdev.sje.Game.ControlWindow.ControlWindow;
import me.alnmgdev.sje.Game.MainScript;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class EngineHandler extends Canvas implements Runnable{
    public final static int windowWidht = 1280, windowHeight = 800;
    private static ScriptHandler scriptHandler ;
    static WindowHandler window;
    public static void main(String[] args) {
        new EngineHandler();
    }
    public static KeyHandler keyHandler = new KeyHandler();
    public static KeyHandler getKeyHandler(){
        return keyHandler;
    }

    public static WindowHandler getWindowHandler(){
        return window;
    }
    public EngineHandler(){
    //operações de inicialização
        this.addKeyListener(keyHandler);
        this.addMouseListener(keyHandler);
        this.addMouseMotionListener(keyHandler);
        this.setFocusable(true);

        scriptHandler = new ScriptHandler();
        scriptHandler.addScript(new MainScript());

        window = new WindowHandler("SJE - blank screen",
                new Dimension(windowWidht,windowHeight),
                true, this
        );
        window.StartWindow();

        Start();

    }

    private Thread thread;
    private boolean running = false;


    public synchronized void Start(){
        thread = new Thread(this);
        thread.start();
        running = true;

    }
    public synchronized void Stop(){
        try {
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public int ticks = 30;
    public int rendertick = 30;
    @Override
    public void run() {
        double updateInterval = 1000000000 / ticks;
        double tickDelta = 0;
        double renderInterval = 1000000000 / rendertick;
        double renderDelta = 0;

        long lastFrame = System.nanoTime();
        long currentFrame;
        int firstFrame = 1;

        onStart();
        while (running){
            Frame();
            currentFrame = System.nanoTime();

            renderDelta += (currentFrame - lastFrame) / renderInterval;
            tickDelta += (currentFrame - lastFrame) / updateInterval;

            lastFrame = currentFrame;
            if (tickDelta >= 1) {
                Tick();

                tickDelta--;
            }
            if (renderDelta >= 1) {
                Render();

                if(firstFrame >= 1){
                    firstFrame --;
                    if(firstFrame == 0){
                        afterFirtsFrame();
                    }
                }

                renderDelta--;
            }


        }

    }
    void afterFirtsFrame(){
        getScriptHandler().afterFirstFrame();
    }
    void onStart() {
        getScriptHandler().onStart();

    }
    void Frame(){
        getScriptHandler().Frame();


    }
    void Tick(){

        getScriptHandler().Tick();
    }
    void Render(){

        BufferStrategy buffer = this.getBufferStrategy();
        if(buffer == null){
            this.createBufferStrategy(3);
            return;
        }

        //fix linux render lag
        if(System.getProperty("os.name").equals("Linux")){
            Toolkit.getDefaultToolkit().sync();
        }

        Graphics2D graphics = (Graphics2D) buffer.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0,0, windowWidht, windowHeight);

        getScriptHandler().Render(graphics);

        graphics.dispose();

        buffer.show();



    }


    public static ScriptHandler getScriptHandler() {
        return scriptHandler;
    }

    public static void setScriptHandler(ScriptHandler scriptHandler) {
        scriptHandler = scriptHandler;
    }
}
