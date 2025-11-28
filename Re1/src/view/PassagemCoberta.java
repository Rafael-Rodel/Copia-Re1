package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class PassagemCoberta extends Cenario {

    public PassagemCoberta() {
        super("Passagem externo");
        this.setIconImage(JogoController.getIconePrincipal());

        JogoController.eventoPassagemCoberta(this);

        mostrarImagem("/resources/imgs/passagem_coberta.png");

        mostrarTexto("Uma passagem externa com cobertura, na direita há uma porta trancada com uma placa ao lado...");

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
        portaDireita = new JButton("Investigar placa");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "PassagemTraseira");
        });

        portaDireita.addActionListener(e -> {
            JogoController.criaPopupPadrao("Placa", null,
                    "'Quando o sol se põe no oeste e a lua nasce no leste, estrelas aparecem no ceu e o vento vai soprar ao chão.\nEntão os portões da vida abrirão.'",
                    this);
        });

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        portaDireita.setForeground(Color.decode(Config.COR_TEXTO));
        portaDireita.setBackground(Color.decode(Config.COR_BOTAO));
        portaDireita.setFont(Config.FONTE_BOTAO);

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

        navMenu.add(portaDireita);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new PassagemCoberta();
    }
}