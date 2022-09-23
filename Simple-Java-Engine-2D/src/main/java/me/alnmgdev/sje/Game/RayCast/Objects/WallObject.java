package me.alnmgdev.sje.Game.RayCast.Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;

public class WallObject {

    int ID;
    ArrayList<Color> colors = new ArrayList<>();
    String texturePath = "resources/raycaster/textures/";
    private String textureName;

    public WallObject(String textureName){
        this.setTextureName(textureName);
        getTexture();
    }

    public ArrayList<Color> getColors(){
        return colors;
    }

    public int getID(){
        return ID;
    }
    BufferedImage texture;
    public void getTexture()  {
        //System.out.println("pegando array de cores");

        try {
            InputStream file = getClass().getClassLoader().getResourceAsStream(getTextureName());
            texture = ImageIO.read(file);

            int textureHeight = texture.getHeight();
            int textureWidht = texture.getWidth();


            for(int y = 0; y < texture.getHeight(); y++) {
                for(int x = 0; x < texture.getWidth(); x++) {
                    int clr = texture.getRGB(x, y);

                    int red = (clr & 0x00ff0000) >> 16;
                    int green = (clr & 0x0000ff00) >> 8;
                    int blue = clr & 0x000000ff;

                    //System.out.println("R: " + red + " G: " +green + " B: " + blue);

                    colors.add(new Color(red,green,blue));

                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }
}
