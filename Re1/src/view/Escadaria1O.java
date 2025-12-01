package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class Escadaria1O extends Cenario {

    JButton escadaria;
    JButton safeRoom;

    public Escadaria1O() {
        super("Escadaria");
        this.setIconImage(JogoController.getIconePrincipal());

        JogoController.eventosEscadaria1O(this);

        mostrarImagem("/resources/imgs/escadaria1O.png");

        mostrarTexto(
                "Uma escadaria que leva ao segundo andar, há uma pequena sala atras das escadas e após um corredor há duas portas...");

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

        safeRoom = new JButton("Pequena sala");
        portaDireita = new JButton("Porta direita");
        portaEsquerda = new JButton("Porta ao fundo");
        escadaria = new JButton("Escadaria");

        safeRoom.addActionListener(e -> {
            JogoController.trocaCenario(this, "SafeRoom1O");
        });
        portaDireita.addActionListener(e -> {
            JogoController.checaChaveArmadura(this, "ArmazemArmas");
        });
        portaEsquerda.addActionListener(e -> {
            JogoController.trocaCenario(this, "CorredorCentral");
        });
        escadaria.addActionListener(e -> {
            JogoController.trocaCenario(this, "Escadaria2O");
        });

        safeRoom.setForeground(Color.decode(Config.COR_TEXTO));
        safeRoom.setBackground(Color.decode(Config.COR_BOTAO));
        safeRoom.setFont(Config.FONTE_BOTAO);

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
        navMenu.add(safeRoom);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new Escadaria1O();
    }
}