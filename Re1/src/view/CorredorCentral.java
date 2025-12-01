package view;
import javax.swing.border.TitledBorder;
import javax.swing.*;
import java.awt.*;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class CorredorCentral extends Cenario {
    JButton caseiro;

    public CorredorCentral() {
        super("Corredor");
        this.setIconImage(JogoController.getIconePrincipal());

        JogoController.eventosCorredorCentral(this);

        mostrarImagem("/resources/imgs/corredor_central.png");

        mostrarTexto("Você vê um grande corredor que se divide em 2, em cada extremidade há uma porta e uma porta no meio do caminho...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(550, 120));
        painelMenu.setMaximumSize(new Dimension(550, 120));
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

        portaEsquerda = new JButton("Porta esquerda");
        portaDireita = new JButton("Porta direita");
        caseiro = new JButton("Sala do meio");
        voltar = new JButton("Escadaria");

        portaDireita.addActionListener(e -> {
            JogoController.abrirPortaC1O(this);
        });
        portaEsquerda.addActionListener(e -> {
            JogoController.trocaCenario(this, "JardimInterno");
        });
        caseiro.addActionListener(e -> {
            JogoController.trocaCenario(this, "QuartoCaseiro");
        });
        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "Escadaria1O");
        });

        portaEsquerda.setForeground(Color.decode(Config.COR_TEXTO));
        portaEsquerda.setBackground(Color.decode(Config.COR_BOTAO));
        portaEsquerda.setFont(Config.FONTE_BOTAO);

        portaDireita.setForeground(Color.decode(Config.COR_TEXTO));
        portaDireita.setBackground(Color.decode(Config.COR_BOTAO));
        portaDireita.setFont(Config.FONTE_BOTAO);

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        caseiro.setForeground(Color.decode(Config.COR_TEXTO));
        caseiro.setBackground(Color.decode(Config.COR_BOTAO));
        caseiro.setFont(Config.FONTE_BOTAO);

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

        navMenu.add(portaEsquerda);
        navMenu.add(portaDireita);
        navMenu.add(caseiro);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new CorredorCentral();
    }
}
