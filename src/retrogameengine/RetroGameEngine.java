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
        
        block.scale(0,1);
        block.fill(Color.blue);
        block.draw();
        engine.updateScreen();
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
