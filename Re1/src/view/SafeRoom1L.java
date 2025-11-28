package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class SafeRoom1L extends Cenario {

    JButton bau;
    JButton quimicos;

    public SafeRoom1L() {
        super("bau");
        this.setIconImage(JogoController.getIconePrincipal());

        // JogoController.eventosSafeRoom1L(this); 

        mostrarImagem("/resources/imgs/safe_room_1L.png");

        mostrarTexto("Uma aconchegante com produtos quimicos e um baú...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(450, 120));
        painelMenu.setMaximumSize(new Dimension(450, 120));
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

        voltar = new JButton("Voltar a escadaria");
        quimicos = new JButton("Examinar quimicos");
        bau = new JButton("bau");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "Escadaria1L");
        });
        quimicos.addActionListener(e -> {
            // JogoController.pegarHerbicida(this); 
        });
        bau.addActionListener(e -> {
            // tem q fazer a logica do bau 
        });

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        quimicos.setForeground(Color.decode(Config.COR_TEXTO));
        quimicos.setBackground(Color.decode(Config.COR_BOTAO));
        quimicos.setFont(Config.FONTE_BOTAO);

        bau.setForeground(Color.decode(Config.COR_TEXTO));
        bau.setBackground(Color.decode(Config.COR_BOTAO));
        bau.setFont(Config.FONTE_BOTAO);


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

        navMenu.add(bau);
        navMenu.add(quimicos);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new SafeRoom1L();
    }
}