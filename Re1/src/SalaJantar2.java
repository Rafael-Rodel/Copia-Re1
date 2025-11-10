import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SalaJantar2 extends Cenario {

    JButton vaso, lareira, voltar;
    JPanel painelMenu;

    public SalaJantar2() {
        super("Sala de Jantar");
        
        mostrarImagem("/imgs/sala jantar 2.png");
        mostrarTexto("Segunda metade da sala: \nVocê vê uma porta a direita, um vaso e uma lareira com um emblema em cima...");
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

        vaso = new JButton("Vaso");
        lareira = new JButton("Lareira");
        portaDireita = new JButton("Porta à direita");
        voltar = new JButton("Voltar na sala");

        vaso.addActionListener(e -> {
            Config.criaPopupPadrao("Vaso", null, "Apenas um vaso comum \nnão há nada dentro...", this);
        });
        lareira.addActionListener(e -> {
            Config.criaPopupPadrao("Lareira", "/imgs/emblema na parede.png", "O brasão parece removivel...", this);
            Itens.popupItem("emblema dourado", "Você pegou um emblema dourado com um brasão de familia...", this);
            Inventario.adicionarItem(Config.EMBLEMA_DOURADO);
        });
        portaDireita.addActionListener(e -> {
            this.dispose();
            new CorredorPrimeiroZumbi();
        });
        voltar.addActionListener(e -> {
            this.dispose();
            new SalaJantar1();
        });

        vaso.setForeground(Color.decode(Config.COR_TEXTO));
        vaso.setBackground(Color.decode(Config.COR_BOTAO));
        vaso.setFont(Config.FONTE_BOTAO);

        lareira.setForeground(Color.decode(Config.COR_TEXTO));
        lareira.setBackground(Color.decode(Config.COR_BOTAO));
        lareira.setFont(Config.FONTE_BOTAO);

        portaDireita.setForeground(Color.decode(Config.COR_TEXTO));
        portaDireita.setBackground(Color.decode(Config.COR_BOTAO));
        portaDireita.setFont(Config.FONTE_BOTAO);

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);

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
        
        navMenu.add(vaso);
        navMenu.add(lareira);
        navMenu.add(portaDireita);
        navMenu.add(voltar);
        
        painelMenu.add(navMenu);
        painelMenu.add(navInventario);
        painelPrincipal.add(painelMenu);
    }

    public static void main(String[] args) {
        new SalaJantar2();
    }
}