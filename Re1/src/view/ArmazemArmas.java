package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;
import model.Personagem;

public class ArmazemArmas extends Cenario {

    JButton mesa, armario;

    public ArmazemArmas() {
        super("Armazem de Armas");
        this.setIconImage(JogoController.getIconePrincipal());

        mostrarImagem("/resources/imgs/armazem_armas.png");

        mostrarTexto("Um deposito de armas com um armadio e uma mesinha...");

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
        armario = new JButton("Examinar armario");
        mesa = new JButton("mesa");

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "Escadaria1O");
        });
        armario.addActionListener(e -> {
            JogoController.pegarShotgunVelha(this); 
        });
        mesa.addActionListener(e -> {
            if (Personagem.getChris()) {
                JogoController.checarChaveMesa(this);
            } else{
                JogoController.pegarMunicaoShotgunArmazem(this);
            }
        });

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

        armario.setForeground(Color.decode(Config.COR_TEXTO));
        armario.setBackground(Color.decode(Config.COR_BOTAO));
        armario.setFont(Config.FONTE_BOTAO);

        mesa.setForeground(Color.decode(Config.COR_TEXTO));
        mesa.setBackground(Color.decode(Config.COR_BOTAO));
        mesa.setFont(Config.FONTE_BOTAO);


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
        navMenu.add(armario);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new ArmazemArmas();
    }
}