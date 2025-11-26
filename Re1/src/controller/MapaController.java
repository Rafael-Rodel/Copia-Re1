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
                case "HallEntrada" -> gifLabel.setBounds(380, 440, larguraGif, alturaGif);

                case "SalaJantar1" -> gifLabel.setBounds(250, 390, larguraGif, alturaGif);

                case "SalaJantar2" -> gifLabel.setBounds(140, 390, larguraGif, alturaGif);

                case "Corredor1Oeste" -> gifLabel.setBounds(125, 327, larguraGif, alturaGif);

                case "SalaCorredor1" -> gifLabel.setBounds(70, 320, larguraGif, alturaGif);

                case "Bar" -> gifLabel.setBounds(195, 260, larguraGif, alturaGif);

                case "BarAberto" -> gifLabel.setBounds(195, 260, larguraGif, alturaGif);

                case "SalaBusto" -> gifLabel.setBounds(195, 185, larguraGif, alturaGif);

                case "SalaEstatua" -> gifLabel.setBounds(510, 430, larguraGif, alturaGif);

                case "SalaQuadros" -> gifLabel.setBounds(575, 375, larguraGif, alturaGif);

                case "CorredorCachorro" -> gifLabel.setBounds(660, 450, larguraGif, alturaGif);

                case "PontaCorredor1L" -> gifLabel.setBounds(690, 245, larguraGif, alturaGif);

                case "Banheiro" -> gifLabel.setBounds(680, 190, larguraGif, alturaGif);

                case "Corredor1Leste" -> gifLabel.setBounds(580, 220, larguraGif, alturaGif);

                case "SalaArmadilha" -> gifLabel.setBounds(580, 220, larguraGif, alturaGif);

                case "SalaEstar" ->gifLabel.setBounds(580, 220, larguraGif, alturaGif);
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
