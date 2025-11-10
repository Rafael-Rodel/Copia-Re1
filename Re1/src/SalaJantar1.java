import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SalaJantar1 extends Cenario {

    JButton relogio, quadro, portaDupla, prosseguir;
    JPanel painelMenu;

    public SalaJantar1() {
        super("Sala de Jantar");

        mostrarImagem("/imgs/sala jantar 1.png");
        mostrarTexto("Primeira metade da sala: \nVocê vê um relogio antigo, uma porta dupla e um quadro...");
        configurarBotoes();
        setVisible(true);
    }

    @Override
    public void configurarBotoes() {
        painelMenu = new JPanel();
        painelMenu.setPreferredSize(new Dimension(550, 120));
        painelMenu.setMaximumSize(new Dimension(550, 120));
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

        relogio = new JButton("Relógio");
        quadro = new JButton("Quadro");
        portaDupla = new JButton("Porta Dupla");
        prosseguir = new JButton("Prosseguir na sala");

        relogio.addActionListener(e -> {
            Config.criaPopupPadrao("Relógio", "/imgs/relogio.png", "Um velho relógio \nParece ter algo atrás mas não consigo mover...", this);
        });
        quadro.addActionListener(e -> {
            Config.criaPopupPadrao("Quadro", null, "Uma pintura a óleo \nNão tem importância...", this);
        });
        portaDupla.addActionListener(e -> {
            this.dispose();
            new HallEntrada();
        });
        prosseguir.addActionListener(e -> {
            this.dispose();
            new SalaJantar2();
        });

        relogio.setForeground(Color.decode(Config.COR_TEXTO));
        relogio.setBackground(Color.decode(Config.COR_BOTAO));
        relogio.setFont(Config.FONTE_BOTAO);

        quadro.setForeground(Color.decode(Config.COR_TEXTO));
        quadro.setBackground(Color.decode(Config.COR_BOTAO));
        quadro.setFont(Config.FONTE_BOTAO);

        portaDupla.setForeground(Color.decode(Config.COR_TEXTO));
        portaDupla.setBackground(Color.decode(Config.COR_BOTAO));
        portaDupla.setFont(Config.FONTE_BOTAO);

        prosseguir.setForeground(Color.decode(Config.COR_TEXTO));
        prosseguir.setBackground(Color.decode(Config.COR_BOTAO));
        prosseguir.setFont(Config.FONTE_BOTAO);

        JPanel navMenu = new JPanel();
        navMenu.setOpaque(false);

        JPanel navInventario = new JPanel();
        navInventario.setOpaque(false);

        inventario = new JButton("Inventário");
        inventario.addActionListener(e -> {
            PainelInventario.exibirInventario(this);
        });
        navInventario.add(inventario);

        inventario.setForeground(Color.decode(Config.COR_DESTAQUE));
        inventario.setBackground(Color.decode(Config.COR_BOTAO));
        inventario.setAlignmentX(Component.CENTER_ALIGNMENT);
        inventario.setFont(Config.FONTE_BOTAO);

        navMenu.add(relogio);
        navMenu.add(quadro);
        navMenu.add(portaDupla);
        navMenu.add(prosseguir);

        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new SalaJantar1();
    }
}
