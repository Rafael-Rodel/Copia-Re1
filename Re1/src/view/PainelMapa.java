package view;

import javax.swing.*;
import java.awt.*;

public class PainelMapa extends JPanel{

    private Image imagemFundo;
    
    public PainelMapa() {
        setOpaque(false);

        imagemFundo = new ImageIcon(getClass().getResource("/resources/imgs/mapa.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
