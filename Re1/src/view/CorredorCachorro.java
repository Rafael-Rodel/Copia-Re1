package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class CorredorCachorro extends Cenario {

    JButton armario;

    public CorredorCachorro() {
        super("Corredor");
        this.setIconImage(JogoController.getIconePrincipal());

        JogoController.eventosCorredorCachorro(this);

        mostrarImagem("/resources/imgs/corredor_cachorro.png");

        mostrarTexto(
                "Um corredor com as janelas quebradas e repleto de velharia bizarra, parece haver algo embaixo de um dos armarios. \nHá apenas uma porta à esquerda...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(500, 120));
        painelMenu.setMaximumSize(new Dimension(500, 120));
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
        armario = new JButton("Checar armario");
        voltar = new JButton("Sala com Estatua");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "SalaEstatua");
        });

        armario.addActionListener(e -> {
            JogoController.pegarMunicaoPistola(this);
        });

        portaEsquerda.addActionListener(e -> {
            JogoController.trocaCenario(this, "PontaCorredor1L");
        });

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        armario.setForeground(Color.decode(Config.COR_TEXTO));
        armario.setBackground(Color.decode(Config.COR_BOTAO));
        armario.setFont(Config.FONTE_BOTAO);

        portaEsquerda.setForeground(Color.decode(Config.COR_TEXTO));
        portaEsquerda.setBackground(Color.decode(Config.COR_BOTAO));
        portaEsquerda.setFont(Config.FONTE_BOTAO);

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
        navMenu.add(armario);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new CorredorCachorro();
    }
}