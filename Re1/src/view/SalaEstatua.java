package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import controller.MapaController;
import model.Cenario;
import model.Config;

public class SalaEstatua extends Cenario {
    JButton estatua;

    public SalaEstatua() {
        super("Sala com Estatua");

        String imgSala = "/resources/imgs/sala_estatua_mapa.png";
        if (MapaController.getPossuiMapa()) {
            imgSala = "/resources/imgs/sala_estatua.png";
        }

        mostrarImagem(imgSala);

        mostrarTexto("Uma sala com algumas pinturas duas portas e uma estatua de uma mulher com um vaso...");

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

        voltar = new JButton("Voltar ao hall");
        portaDireita = new JButton("Porta a direita");
        portaEsquerda = new JButton("Porta a esquerda");
        estatua = new JButton("Estatua");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "HallEntrada");
        });

        portaDireita.addActionListener(e -> {
            JogoController.trocaCenario(this, "SalaQuadros");
        });

        portaEsquerda.addActionListener(e -> {
            JogoController.trocaCenario(this, "CorredorCachorro");
        });

        estatua.addActionListener(e -> {
            JogoController.pegarMapa(this);
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

        estatua.setForeground(Color.decode(Config.COR_TEXTO));
        estatua.setBackground(Color.decode(Config.COR_BOTAO));
        estatua.setFont(Config.FONTE_BOTAO);

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
        navMenu.add(estatua);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new SalaEstatua();
    }
}