package me.alnmgdev.sje.Engine.Engine;

import java.awt.*;
import java.awt.event.*;

public class KeyHandler implements KeyListener, MouseListener, MouseMotionListener {
    public boolean mouseLeftPressed,mouseRightPressed, mouseMiddlePressed, mouseClicked, mouseInside;
    public boolean upPressed, downPressed, leftPressed, rightPressed, Wpressed, Apressed, Spressed,
    Dpressed, F1pressed,F2pressed,F3pressed,F4pressed ;

    public int getMouseX, getMouseY;

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_W){
            Wpressed = true;
        }
        if(code == KeyEvent.VK_A){
            Apressed = true;
        }
        if(code == KeyEvent.VK_S){
            Spressed = true;
        }
        if(code == KeyEvent.VK_D){
            Dpressed = true;
        }
        if(code == KeyEvent.VK_F1){
            F1pressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if(code == KeyEvent.VK_UP){

            upPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_W){
            Wpressed = false;
        }
        if(code == KeyEvent.VK_A){
            Apressed = false;
        }
        if(code == KeyEvent.VK_S){
            Spressed = false;
        }
        if(code == KeyEvent.VK_D){
            Dpressed = false;
        }
        if(code == KeyEvent.VK_F1){
            F1pressed = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {


        mouseClicked = true;


        int code = mouseEvent.getButton();
        if (code == MouseEvent.BUTTON1) {
            mouseLeftPressed = true;
        }
        if (code == MouseEvent.BUTTON2) {
            mouseRightPressed = true;
        }
        if (code == MouseEvent.BUTTON3) {
            mouseMiddlePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        mouseClicked = false;
        int code = mouseEvent.getButton();
        if (code == MouseEvent.BUTTON1) {
            mouseLeftPressed = false;
        }
        if (code == MouseEvent.BUTTON2) {
            mouseRightPressed = false;
        }
        if (code == MouseEvent.BUTTON3) {
            mouseMiddlePressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
     mouseInside = true;
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
     mouseInside = false;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        getMouseX = mouseEvent.getX();
        getMouseY = mouseEvent.getY();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        getMouseX = mouseEvent.getX();
        getMouseY = mouseEvent.getY();
    }
}
