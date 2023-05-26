package org.example.presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    /**
     * Constructs an instance of the ImagePanel class with the specified layout manager and image path.
     *
     * @param layoutManager the layout manager to be used by the panel
     * @param pathImg       the path to the image file
     */
    public ImagePanel(LayoutManager layoutManager, String pathImg) {

        super(layoutManager);

        try {
            image = ImageIO.read(new File(pathImg));
        } catch (IOException ex) {
            System.out.println("The path to image is not valid");
            System.exit(1);
        }
    }

    /**
     * Overrides the paintComponent method to paint the image on the panel.
     *
     * @param g the Graphics object used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
