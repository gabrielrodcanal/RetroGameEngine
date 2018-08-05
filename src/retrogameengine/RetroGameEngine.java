/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrogameengine;

import java.awt.Color;

/**
 *
 * @author gabriel
 */
public class RetroGameEngine {
    private int scr_width;
    private int scr_height;
    public int[] gfx;
    private Screen screen;

    public RetroGameEngine(int scr_width, int scr_height) {
        this.scr_width = scr_width;
        this.scr_height = scr_height;
        gfx = new int[scr_width * scr_height];
        screen = new Screen(scr_width, scr_height, gfx);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                screen.setVisible(true);
            }
        });
    }
    
    public static void main(String[] args) {
        RetroGameEngine engine = new RetroGameEngine(640,480);
        
        Circle c = new Circle(100,200,2,engine,Color.MAGENTA);
        Polygon block = new Polygon(4,200,200,60,engine,Color.CYAN);
        
        /*
        //Rotation test
        for(int i = 0; i <= 50; i++) {
            engine.clearScreen();
            block.rotate(2 * i * Math.PI / 50);
            block.fill(Color.blue);
            block.draw();
            engine.updateScreen();
            try {
                Thread.sleep(100);
            }
            catch(Exception e) {}
        }
        */
        
        //Collision test
        int sign = 1;
        for(int i = 0; i < 50; i++) {
            engine.clearScreen();
            c.fill(Color.GREEN);
            c.draw();
            block.fill(Color.ORANGE);
            block.draw();
            engine.updateScreen();
            if(c.hasCollided(block))
                sign = -1;
            c.shift(sign * 4, 0);
            
            try {
                Thread.sleep(16);
            }
            catch(Exception e) {}
        }
    }
    
    public void clearScreen() {
        for(int i = 0; i < scr_width * scr_height; i++)
            gfx[i] = 0;
    }
    
    public void updateScreen() {
        screen.paint_screen();
    }
    
    public int[] getGfx() {
        return gfx;
    }
    
    public int[] getScrSize() {
        return new int[] {scr_width, scr_height};
    }
}
