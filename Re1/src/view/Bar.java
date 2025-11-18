package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class Bar extends Cenario {
    
    JButton piano, bar, estante, voltar;
    JPanel painelMenu;

    public Bar() {
        super("Bar");

        mostrarImagem(JogoController.verificarBar());

        mostrarTexto("Você vê um bar com bebidas classicas, um piano e um estante no canto da sala...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(320, 120));
        painelMenu.setMaximumSize(new Dimension(320, 120));
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
        voltar = new JButton("voltar");

        piano.addActionListener(e -> {
            JogoController.tocarPiano(this);
        });
        bar.addActionListener(e -> {
            Config.criaPopupPadrao("Bar", null, "Apenas bebidas comuns, nada util.", this);
        });
        estante.addActionListener(e -> {
            JogoController.pegarPartitura(this);
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

        navMenu.add(piano);
        navMenu.add(bar);
        navMenu.add(estante);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }
    public static void main(String[] args) {
        new Bar();
    }
}

