package view;
import javax.swing.border.TitledBorder;
import javax.swing.*;
import java.awt.*;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class PassagemTraseira extends Cenario {
    JButton estudos, galeria, fundo, direita, corredor;

    public PassagemTraseira() {
        super("Passagem");

        JogoController.eventoPassagem(this);

        mostrarImagem("/resources/imgs/passagem_traseira.png");

        mostrarTexto("Você ve uma passagem com uma porta entitulada 'galeria', outra 'Sala de estudos', uma porta não entitulada à direita e outra ao fundo...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(750, 120));
        painelMenu.setMaximumSize(new Dimension(750, 120));
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

        estudos = new JButton("Sala de estudos");
        galeria = new JButton("Galeria");
        fundo = new JButton("Porta ao fundo");
        direita = new JButton("Porta a direita");
        voltar = new JButton("Voltar ao corredor");

        estudos.addActionListener(e -> {
            JogoController.trocaCenario(this, "SalaEstudos");
        });
        galeria.addActionListener(e -> {
            JogoController.trocaCenario(this, "Galeria");
        });
        fundo.addActionListener(e -> {
            JogoController.trocaCenario(this, "PassagemCoberta");
        });
        direita.addActionListener(e ->{
            JogoController.trocaCenario(this, "Escadaria1Leste");
        });
        voltar.addActionListener(e ->{
            JogoController.trocaCenario(this, "Corredor1Leste");
        });

        estudos.setForeground(Color.decode(Config.COR_TEXTO));
        estudos.setBackground(Color.decode(Config.COR_BOTAO));
        estudos.setFont(Config.FONTE_BOTAO);

        galeria.setForeground(Color.decode(Config.COR_TEXTO));
        galeria.setBackground(Color.decode(Config.COR_BOTAO));
        galeria.setFont(Config.FONTE_BOTAO);

        fundo.setForeground(Color.decode(Config.COR_TEXTO));
        fundo.setBackground(Color.decode(Config.COR_BOTAO));
        fundo.setFont(Config.FONTE_BOTAO);

        direita.setForeground(Color.decode(Config.COR_TEXTO));
        direita.setBackground(Color.decode(Config.COR_BOTAO));
        direita.setFont(Config.FONTE_BOTAO);

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

        navMenu.add(estudos);
        navMenu.add(galeria);
        navMenu.add(fundo);
        navMenu.add(direita);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new PassagemTraseira();
    }
}
