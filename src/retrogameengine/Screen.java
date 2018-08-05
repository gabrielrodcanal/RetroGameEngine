/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrogameengine;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 *
 * @author gabriel
 */
public class Screen extends javax.swing.JFrame {
    private BufferedImage screen_buffer;
    
    private int black;
    private int white;
    private int[] gfx;
    
    private int[] trans_colour;
    private int scr_width, scr_height;

    /**
     * Creates new form ScreenFrame
     */
    public Screen(int scr_width, int scr_height, int[] gfx) {   
        initComponents();
        
        this.scr_width = scr_width;
        this.scr_height = scr_height;
        this.gfx = gfx;
        
        black = Color.BLACK.getRGB();
        white = Color.WHITE.getRGB();
        
        trans_colour = new int[] {black,white};
        screen_buffer = new BufferedImage(scr_width,scr_height,BufferedImage.TYPE_INT_RGB);
        screenLabel.setIcon(new ImageIcon(screen_buffer));
        getContentPane().setPreferredSize(new java.awt.Dimension(scr_width, scr_height));
        pack();
        this.setResizable(false);
    }
    
    public void setGfx(int[] gfx) {
        this.gfx  = gfx;
    }
    
    public void paint_screen() {
        screen_buffer.setRGB(0, 0, scr_width, scr_height, gfx, 0, scr_width);
        screenLabel.repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        screenLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(screenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(screenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel screenLabel;
    // End of variables declaration//GEN-END:variables
}
