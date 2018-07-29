/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrogameengine;

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
        
        Rectangle rect = new Rectangle(200,200,300,250,engine);
        rect.draw();
        engine.updateScreen();
        /*
        for(int i = 8; i > 0; i--) {
            rect.rotate(Math.PI / i);
            engine.updateScreen();
        }
        */
        /*
        rect.rotate(Math.PI);
        engine.updateScreen(); */
        int resolution = 50;
        /*
        for(int i = 0; i <= 2 * resolution; i++) {
            engine.clearScreen();
            rect.rotate(2 * Math.PI * i / resolution);
            engine.updateScreen();
            try {
                Thread.sleep(100);
            }
            catch(Exception e) {}
        }
        */
        rect.scale(1.5f);
        engine.updateScreen();
        
        rect.shift(20,100);
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
