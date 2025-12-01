package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class Escadaria2O extends Cenario {

    JButton escadaria;

    public Escadaria2O() {
        super("Escadaria");
        this.setIconImage(JogoController.getIconePrincipal());

        JogoController.eventosEscadaria2O(this);

        mostrarImagem("/resources/imgs/escadaria2O.png");

        mostrarTexto("Uma escadaria que leva ao primeiro andar, após um corredor há duas portas...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(600, 120));
        painelMenu.setMaximumSize(new Dimension(600, 120));
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

        voltar = new JButton("Sala de jantar");
        portaDireita = new JButton("Porta ao fundo");
        portaEsquerda = new JButton("Porta a esquerda");
        escadaria = new JButton("Escadaria");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "SalaJantar2Andar");
        });
        portaDireita.addActionListener(e -> {
            JogoController.criaPopupPadrao("Porta trancada", null, "A porta está trancada, um capacete entalhado...", this);
        });
        portaEsquerda.addActionListener(e -> {
            JogoController.criaPopupPadrao("Porta trancada", null, "A porta está trancada, há um painel numérico mas eu não sei qual a senha...", this);
        });
        escadaria.addActionListener(e -> {
            JogoController.trocaCenario(this, "Escadaria1O");
        });

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        portaDireita.setForeground(Color.decode(Config.COR_TEXTO));
        portaDireita.setBackground(Color.decode(Config.COR_BOTAO));
        portaDireita.setFont(Config.FONTE_BOTAO);
        
        portaEsquerda.setForeground(Color.decode(Config.COR_TEXTO));
        portaEsquerda.setBackground(Color.decode(Config.COR_BOTAO));
        portaEsquerda.setFont(Config.FONTE_BOTAO);

        escadaria.setForeground(Color.decode(Config.COR_TEXTO));
        escadaria.setBackground(Color.decode(Config.COR_BOTAO));
        escadaria.setFont(Config.FONTE_BOTAO);


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

        navMenu.add(escadaria);
        navMenu.add(portaDireita);
        navMenu.add(portaEsquerda);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new Escadaria2O();
    }
}