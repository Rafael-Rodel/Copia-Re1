package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.InventarioController;
import controller.JogoController;
import model.Cenario;
import model.Config;

public class SalaBusto extends Cenario {

    JButton busto;

    public SalaBusto() {
        super("Sala com busto");
        this.setIconImage(JogoController.getIconePrincipal());

        mostrarImagem("/resources/imgs/sala_busto.png");

        mostrarTexto(
                "Você vê uma pequena sala com uma grande janela para um jardim, no fundo da sala há um busto...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(250, 120));
        painelMenu.setMaximumSize(new Dimension(250, 120));
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

        busto = new JButton("Busto");
        voltar = new JButton("Voltar ao bar");

        busto.addActionListener(e -> {
            JogoController.trocarBustoBar(this);
        });
        voltar.addActionListener(e -> {
            if (JogoController.checarPortaTrancadaBar()) {
                JogoController.criaPopupPadrao("Porta trancada!", null,
                        "A porta está trancada! \nO mecanismo foi ativado quando removi o emblema...", this);
            } else {
                JogoController.trocaCenario(this, "BarAberto");
            }
        });

        busto.setForeground(Color.decode(Config.COR_TEXTO));
        busto.setBackground(Color.decode(Config.COR_BOTAO));
        busto.setFont(Config.FONTE_BOTAO);

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

        navMenu.add(busto);
        navMenu.add(voltar);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new SalaBusto();
    }
}
