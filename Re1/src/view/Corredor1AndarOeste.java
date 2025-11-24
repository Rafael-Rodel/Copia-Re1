package view;
import javax.swing.border.TitledBorder;
import javax.swing.*;
import java.awt.*;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

    // esse é pra ser aquele corredor onde a gente encontra o primeiro zumbi
    // -R

public class Corredor1AndarOeste extends Cenario {
    JButton portaDupla, portaVermelha, porta, sala, salaJantar;

    public Corredor1AndarOeste() {
        super("Corredor");

        mostrarImagem("/resources/imgs/corredor1O.png");

        mostrarTexto("Você vê uma porta de madeira a sua frente, uma porta dupla ao fundo do corredor e uma porta vermelha ao lado dela, \nA sua esquerda há uma pequena sala e atras de você a porta da sala de jantar...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(700, 120));
        painelMenu.setMaximumSize(new Dimension(700, 120));
        painelMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelMenu.setOpaque(false);
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode(Config.COR_DESTAQUE), 3),
                "Menu",
                TitledBorder.CENTER,
                TitledBorder.TOP);
        borda.setTitleColor(Color.decode(Config.COR_DESTAQUE));
        borda.setTitleFont(Config.FONTE_TITULO_BORDA);
        painelMenu.setBorder(borda);

        porta = new JButton("Porta");
        portaDupla = new JButton("Porta Dupla");
        portaVermelha = new JButton("Porta Vermelha");
        sala = new JButton("Sala a Esquerda");
        salaJantar = new JButton("Sala de jantar");

        porta.addActionListener(e -> {
            Config.criaPopupPadrao("Porta de madeira", null, "Essa porta esta trancada.", this);
        });
        portaDupla.addActionListener(e -> {
            Config.criaPopupPadrao("Porta dupla", null, "Parece trancado por dentro", this);
        });
        portaVermelha.addActionListener(e -> {
            JogoController.trocaCenario(this, JogoController.verificarBar());
        });
        sala.addActionListener(e ->{
            JogoController.trocaCenario(this, "SalaCorredor1");
        });
        salaJantar.addActionListener(e ->{
            JogoController.trocaCenario(this, "SalaJantar2");
        });

        porta.setForeground(Color.decode(Config.COR_TEXTO));
        porta.setBackground(Color.decode(Config.COR_BOTAO));
        porta.setFont(Config.FONTE_BOTAO);

        portaDupla.setForeground(Color.decode(Config.COR_TEXTO));
        portaDupla.setBackground(Color.decode(Config.COR_BOTAO));
        portaDupla.setFont(Config.FONTE_BOTAO);

        portaVermelha.setForeground(Color.decode(Config.COR_TEXTO));
        portaVermelha.setBackground(Color.decode(Config.COR_BOTAO));
        portaVermelha.setFont(Config.FONTE_BOTAO);

        sala.setForeground(Color.decode(Config.COR_TEXTO));
        sala.setBackground(Color.decode(Config.COR_BOTAO));
        sala.setFont(Config.FONTE_BOTAO);

        salaJantar.setForeground(Color.decode(Config.COR_TEXTO));
        salaJantar.setBackground(Color.decode(Config.COR_BOTAO));
        salaJantar.setFont(Config.FONTE_BOTAO);

        JPanel navMenu = new JPanel();
        navMenu.setOpaque(false);

        JPanel navInventario = new JPanel();
        navInventario.setOpaque(false);

        inventario = new JButton("Inventário");
        inventario.addActionListener(e -> {
            InventarioController.exibirInventario(this);
        });
        navInventario.add(inventario);

        inventario.setForeground(Color.decode(Config.COR_DESTAQUE));
        inventario.setBackground(Color.decode(Config.COR_BOTAO));
        inventario.setAlignmentX(Component.CENTER_ALIGNMENT);
        inventario.setFont(Config.FONTE_BOTAO);

        navMenu.add(porta);
        navMenu.add(portaDupla);
        navMenu.add(portaVermelha);
        navMenu.add(sala);
        navMenu.add(salaJantar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new Corredor1AndarOeste();
    }
}
