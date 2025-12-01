package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class QuartoCaseiro extends Cenario{

    JButton municao, mesa, armario;

    public QuartoCaseiro(){
        super("Quarto do Caseiro");
        this.setIconImage(JogoController.getIconePrincipal());

        JogoController.eventoQuartoCaseiro(this);

        mostrarImagem("/resources/imgs/quarto_caseiro.png");

        mostrarTexto("Um quarto simples com um armario, uma mesa com documentos e um carregador na cama...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(360, 120));
        painelMenu.setMaximumSize(new Dimension(360, 120));
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

        mesa = new JButton("Mesa");
        municao = new JButton("cama");
        armario = new JButton("armario");
        voltar = new JButton("Voltar");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "CorredorCentral");
        });

        mesa.addActionListener(e -> {
            JogoController.criaPopupPadrao("documento", null, "", this);
        });

        armario.addActionListener(e -> {
            JogoController.pegarMunicaoPistolaCaseiro(this);
        });

        municao.addActionListener(e -> {
            JogoController.pegarMunicaoShotgunCaseiro(this);
        });
        
        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        mesa.setForeground(Color.decode(Config.COR_TEXTO));
        mesa.setBackground(Color.decode(Config.COR_BOTAO));
        mesa.setFont(Config.FONTE_BOTAO);

        armario.setForeground(Color.decode(Config.COR_TEXTO));
        armario.setBackground(Color.decode(Config.COR_BOTAO));
        armario.setFont(Config.FONTE_BOTAO);

        municao.setForeground(Color.decode(Config.COR_TEXTO));
        municao.setBackground(Color.decode(Config.COR_BOTAO));
        municao.setFont(Config.FONTE_BOTAO);

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

        navMenu.add(mesa);
        navMenu.add(municao);
        navMenu.add(armario);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }
    public static void main(String[] args) {
        new QuartoCaseiro();
    }
}