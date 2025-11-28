package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class HallEntrada extends Cenario {

    
    private JButton escadaria, maquina, portaPequena;

    public HallEntrada() {
        super("Hall de Entrada");
        this.setIconImage(JogoController.getIconePrincipal());

        JogoController.verificarEventosHall(this);

        mostrarImagem("/resources/imgs/hall.png");

        mostrarTexto(
                "Você está no hall principal... \nVocê vê duas portas grandes, uma porta pequena, uma escadaria e uma velha maquina de escrever... \nA porta à direita parece trancada...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {

        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(800, 120));
        painelMenu.setMaximumSize(new Dimension(800, 120));
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

        portaEsquerda = new JButton("Porta à Esquerda");
        portaDireita = new JButton("Porta à Direita");
        portaPequena = new JButton("Porta pequena");
        escadaria = new JButton("Escadaria");
        maquina = new JButton("Maquina de Escrever");

        portaEsquerda.addActionListener(e -> {
            JogoController.trocaCenario(this, "SalaJantar1");
        });
        portaDireita.addActionListener(e -> {
            JogoController.trocaCenario(this, "SalaEstatua");
        });
        portaPequena.addActionListener(e -> {
            JogoController.criaPopupPadrao("Porta pequena à direita", null, "A porta está trancada, há uma gravura de armadura.", this);
        });
        escadaria.addActionListener(e -> {
            JogoController.criaPopupPadrao("Escadaria", null, "Não posso ir pra lá ainda.", this);
        });
        maquina.addActionListener(e -> {
            JogoController.criaPopupPadrao("Maquina de escrever", "/resources/imgs/maquina.png",
                    "Uma velha maquina de escrever.\n" + "Se eu tivesse tinta poderia salvar o jogo.", this);
        });

        portaEsquerda.setForeground(Color.decode(Config.COR_TEXTO));
        portaEsquerda.setBackground(Color.decode(Config.COR_BOTAO));
        portaEsquerda.setFont(Config.FONTE_BOTAO);

        portaDireita.setForeground(Color.decode(Config.COR_TEXTO));
        portaDireita.setBackground(Color.decode(Config.COR_BOTAO));
        portaDireita.setFont(Config.FONTE_BOTAO);

        portaPequena.setForeground(Color.decode(Config.COR_TEXTO));
        portaPequena.setBackground(Color.decode(Config.COR_BOTAO));
        portaPequena.setFont(Config.FONTE_BOTAO);

        escadaria.setForeground(Color.decode(Config.COR_TEXTO));
        escadaria.setBackground(Color.decode(Config.COR_BOTAO));
        escadaria.setFont(Config.FONTE_BOTAO);

        maquina.setForeground(Color.decode(Config.COR_TEXTO));
        maquina.setBackground(Color.decode(Config.COR_BOTAO));
        maquina.setFont(Config.FONTE_BOTAO);

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
        navMenu.add(portaPequena);
        navMenu.add(escadaria);
        navMenu.add(maquina);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new HallEntrada();
    }
}