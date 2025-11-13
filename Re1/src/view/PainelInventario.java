package view;
import javax.swing.*;

import controller.InventarioController;
import model.Personagem;

import java.awt.*;

// usei o GridBag pra fazer o "Position Layout" do inventario e ele se organizar automatico
// o Grid comum fica mudando o posicionamento sempre q tu add ou retira um item, ruinzão
// -R

public class PainelInventario extends JPanel {
    private static PainelInventario instancia;
    private Image imagemFundo;
    private JPanel gridItens;
    private InventarioController controller;    

    public PainelInventario() {
        instancia = this;
        setLayout(new GridBagLayout());
        setOpaque(false);

        String caminho = "/resources/imgs/inventario jill.png";
        if (Personagem.getChris()) {
            caminho = "/resources/imgs/inventario chris.png";
        } 

        imagemFundo = new ImageIcon(getClass().getResource(caminho)).getImage();

        gridItens = new JPanel(new GridBagLayout());
        gridItens.setOpaque(false);

        controller = new InventarioController(gridItens);
        controller.criarSlotsFixos();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(13, 487, 0, 0);
        add(gridItens, gbc);
    }

    public static PainelInventario getInstancia() {
        return instancia;
    }

    public InventarioController getController() {
        return controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
    }
}
