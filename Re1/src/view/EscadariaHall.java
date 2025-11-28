package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class EscadariaHall extends Cenario {

    JButton direita2;

    public EscadariaHall() {
        super("Escadaria");
        this.setIconImage(JogoController.getIconePrincipal());

        mostrarImagem("/resources/imgs/escadaria_hall.png");

        mostrarTexto("A escadaria se divide em dois, há uma porta a esquerda e duas à direita...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(850, 120));
        painelMenu.setMaximumSize(new Dimension(850, 120));
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

        voltar = new JButton("Voltar ao hall");
        portaEsquerda = new JButton("Porta a esquerda");
        portaDireita = new JButton("Porta a direita mais proxima");
        direita2 = new JButton("Porta a direita mais distante");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "HallEntrada");
        });

        portaEsquerda.addActionListener(e -> {
            JogoController.trocaCenario(this, "SalaJantarSegundoAndar");
        });

        portaDireita.addActionListener(e -> {
            JogoController.criaPopupPadrao("trancado", null, "Ainda não posso ir lá...", this);
        });

        direita2.addActionListener(e -> {
            JogoController.criaPopupPadrao("trancado", null, "Ainda não posso ir lá...", this);
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

        direita2.setForeground(Color.decode(Config.COR_TEXTO));
        direita2.setBackground(Color.decode(Config.COR_BOTAO));
        direita2.setFont(Config.FONTE_BOTAO);

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
        navMenu.add(direita2);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new EscadariaHall();
    }
}