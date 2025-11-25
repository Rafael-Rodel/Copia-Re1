package controller;

import javax.swing.*;
import java.awt.*;

import view.PainelMapa;

public class MapaController extends JPanel {

    private static final int larguraGif = 40;
    private static final int alturaGif = 40;
    private static boolean possuiMapa = false;

    public static void setPossuiMapa(boolean temMapa) {
        possuiMapa = temMapa;
    }

    public static boolean getPossuiMapa() {
        return possuiMapa;
    }

    public static void exibirMapa(JPanel parent) {
        if (possuiMapa) {
            Window janela = SwingUtilities.getWindowAncestor(parent);
            JDialog popMapa = new JDialog(janela, "Mapa", Dialog.ModalityType.APPLICATION_MODAL);
            popMapa.setSize(800, 600);
            popMapa.setLocationRelativeTo(parent);

            PainelMapa mapa = new PainelMapa();

            ImageIcon localAtual = new ImageIcon(
                    MapaController.class.getResource("/resources/imgs/pontoVermelhoMapa.gif"));
            Image localRedimens = localAtual.getImage().getScaledInstance(larguraGif, alturaGif, Image.SCALE_DEFAULT);
            ImageIcon iconRedimensionado = new ImageIcon(localRedimens);
            JLabel gifLabel = new JLabel(iconRedimensionado);

            switch (JogoController.getCenarioAtual()) {
                case "HallEntrada" -> gifLabel.setBounds(400, 420, larguraGif, alturaGif);

                case "SalaJantar1" -> gifLabel.setBounds(280, 400, larguraGif, alturaGif);

                case "SalaJantar2" -> gifLabel.setBounds(180, 400, larguraGif, alturaGif);

                case "Corredor1AndarOeste" -> gifLabel.setBounds(140, 345, larguraGif, alturaGif);

                case "SalaCorredor1" -> gifLabel.setBounds(85, 330, larguraGif, alturaGif);

                case "Bar" -> gifLabel.setBounds(240, 295, larguraGif, alturaGif);

                case "BarAberto" -> gifLabel.setBounds(240, 295, larguraGif, alturaGif);

                case "SalaBusto" -> gifLabel.setBounds(230, 220, larguraGif, alturaGif);

                case "SalaEstatua" -> gifLabel.setBounds(510, 420, larguraGif, alturaGif);

                case "SalaQuadros" -> gifLabel.setBounds(510, 420, larguraGif, alturaGif);

                case "CorredorCachorro" -> gifLabel.setBounds(510, 420, larguraGif, alturaGif);

                case "PontaCorredor1L" -> gifLabel.setBounds(510, 420, larguraGif, alturaGif);
            }

            popMapa.add(mapa);
            mapa.add(gifLabel);
            popMapa.setVisible(true);
        } else {
            Window janela = SwingUtilities.getWindowAncestor(parent);
            JogoController.criaPopupPadrao("Mapa", null, "Ainda não possuo o mapa da mansão.", janela);
        }
    }
}
