package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class Escadaria1L extends Cenario {

    JButton escadaria;

    public Escadaria1L() {
        super("Escadaria");
        this.setIconImage(JogoController.getIconePrincipal());

        // JogoController.eventosEscadaria1L(this);

        mostrarImagem("/resources/imgs/escadaria1L.png");

        mostrarTexto("Uma escadaria que leva ao segundo andar, após um pequeno corredor há uma porta...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(400, 120));
        painelMenu.setMaximumSize(new Dimension(400, 120));
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

        voltar = new JButton("Voltar ao corredor");
        portaDireita = new JButton("Porta");
        escadaria = new JButton("Escadaria");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "PassagemTraseira");
        });
        portaDireita.addActionListener(e -> {
            JogoController.trocaCenario(this, "SafeRoom1L");
        });
        escadaria.addActionListener(e -> {
            JogoController.criaPopupPadrao("Escadaria", null, "Ainda não posso ir lá...", this);
        });

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        portaDireita.setForeground(Color.decode(Config.COR_TEXTO));
        portaDireita.setBackground(Color.decode(Config.COR_BOTAO));
        portaDireita.setFont(Config.FONTE_BOTAO);

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
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new Escadaria1L();
    }
}