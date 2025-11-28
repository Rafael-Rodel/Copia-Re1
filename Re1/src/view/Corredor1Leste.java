package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class Corredor1Leste extends Cenario{

    public Corredor1Leste(){
        super("Corredor leste");
        this.setIconImage(JogoController.getIconePrincipal());

        mostrarImagem("/resources/imgs/corredor1L.png");

        mostrarTexto("Final do corredor, há apenas 2 portas...");

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

        portaDireita = new JButton("Porta direita");
        portaEsquerda = new JButton("Porta esquerda");
        voltar = new JButton("Voltar no corredor");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "PontaCorredor1L");
        });

        portaEsquerda.addActionListener(e -> {
            JogoController.checkArmadilha(this);
        });

        portaDireita.addActionListener(e -> {
            JogoController.trocaCenario(this, "PassagemTraseira");
        });

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        portaEsquerda.setForeground(Color.decode(Config.COR_TEXTO));
        portaEsquerda.setBackground(Color.decode(Config.COR_BOTAO));
        portaEsquerda.setFont(Config.FONTE_BOTAO);

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

        navMenu.add(portaEsquerda);
        navMenu.add(portaDireita);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }
    public static void main(String[] args) {
        new Corredor1Leste();
    }
}