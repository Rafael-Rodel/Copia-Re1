package controller;

import javax.swing.*;

import view.PainelMapa;

import java.awt.*;

public class MapaController extends JPanel {

    public static void exibirMapa(JPanel parent) {
        Window janela = SwingUtilities.getWindowAncestor(parent);
        JDialog popMapa = new JDialog(janela, "Mapa", Dialog.ModalityType.APPLICATION_MODAL);
        popMapa.setSize(800, 600);
        popMapa.setLocationRelativeTo(parent);

        PainelMapa mapa = new PainelMapa();
        popMapa.add(mapa);
        popMapa.setVisible(true);
    }
}
