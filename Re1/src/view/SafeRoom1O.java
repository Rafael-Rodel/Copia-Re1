package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.BauController;
import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class SafeRoom1O extends Cenario {

    JButton bau;
    JButton estante;

    public SafeRoom1O() {
        super("Sala segura");
        this.setIconImage(JogoController.getIconePrincipal());

        JogoController.eventoSafeRoom1O(this);

        mostrarImagem("/resources/imgs/safe_room_1O.png");

        mostrarTexto("Um aconchegante quarto com um baú e uma estante...");

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
        estante = new JButton("Examinar estante");
        bau = new JButton("bau");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "Escadaria1O");
        });
        estante.addActionListener(e -> {
            JogoController.criaPopupPadrao("Estante", null, "Uma estante com soros e outras velharias, não é util agora...", this);
        });
        bau.addActionListener(e -> {
            BauController.exibirBau(this);
        });

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        estante.setForeground(Color.decode(Config.COR_TEXTO));
        estante.setBackground(Color.decode(Config.COR_BOTAO));
        estante.setFont(Config.FONTE_BOTAO);

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
        navMenu.add(estante);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new SafeRoom1O();
    }
}