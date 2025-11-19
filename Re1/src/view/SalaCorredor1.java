package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class SalaCorredor1 extends Cenario{
    JPanel painelMenu;

    public SalaCorredor1(){
        super("Sala Corredor");

        JogoController.verificarEventosCorredor1(this);

        mostrarImagem("/resources/imgs/sala_corredor1.png");

        mostrarTexto("O que era aquela criatura? \nEsta sala não possui nada util...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(200, 120));
        painelMenu.setMaximumSize(new Dimension(200, 120));
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

        voltar = new JButton("Voltar ao corredor");
        

        voltar.addActionListener(e -> {
            JogoController.trocaCenario(this, "Corredor1AndarOeste");
        });

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

        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }
    public static void main(String[] args) {
        new SalaCorredor1();
    }
}