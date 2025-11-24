package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class BarAberto extends Cenario {
    
    JButton piano, bar, estante, passagem, voltar;

    public BarAberto() {
        super("Bar");

        mostrarImagem("/resources/imgs/bar_aberto.png");

        mostrarTexto("Você vê um bar com bebidas classicas, um piano e um estante no canto da sala \nAgora também há uma passagem na parede...");

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

        piano = new JButton("Piano");
        bar = new JButton("Bar");
        estante = new JButton("estante");
        passagem = new JButton("Passagem");
        voltar = new JButton("voltar");

        piano.addActionListener(e -> {
            Config.criaPopupPadrao("Piano", null, "Um belo piano grande que abre passagens secretas.", this);
        });
        bar.addActionListener(e -> {
            Config.criaPopupPadrao("Bar", null, "Apenas bebidas comuns, nada util.", this);
        });
        estante.addActionListener(e -> {
            Config.criaPopupPadrao("Estante", "/resources/imgs/estante_vazia.png",
                    "Uma estante com livros estranhos...", this);
        });
        passagem.addActionListener(e -> {
            JogoController.trocaCenario(this, "SalaBusto");
        });
        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "Corredor1AndarOeste");
        });

        piano.setForeground(Color.decode(Config.COR_TEXTO));
        piano.setBackground(Color.decode(Config.COR_BOTAO));
        piano.setFont(Config.FONTE_BOTAO);

        bar.setForeground(Color.decode(Config.COR_TEXTO));
        bar.setBackground(Color.decode(Config.COR_BOTAO));
        bar.setFont(Config.FONTE_BOTAO);

        estante.setForeground(Color.decode(Config.COR_TEXTO));
        estante.setBackground(Color.decode(Config.COR_BOTAO));
        estante.setFont(Config.FONTE_BOTAO);

        passagem.setForeground(Color.decode(Config.COR_TEXTO));
        passagem.setBackground(Color.decode(Config.COR_BOTAO));
        passagem.setFont(Config.FONTE_BOTAO);

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

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

        navMenu.add(passagem);
        navMenu.add(piano);
        navMenu.add(bar);
        navMenu.add(estante);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }
    public static void main(String[] args) {
        new BarAberto();
    }
}

