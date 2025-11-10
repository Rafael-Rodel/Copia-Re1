import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class HallEntrada extends Cenario {

    private static boolean primeiraVezHall = true;
    private JButton escadaria, maquina, portaPequena;
    private JPanel painelMenuHall;

    public HallEntrada() {
        super("Hall de Entrada");

        if (primeiraVezHall) {
            JDialog popHistoria = new JDialog(this, "História", true);
            popHistoria.setSize(600, 250);
            popHistoria.setLocationRelativeTo(this);

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode(Config.COR_FUNDO));
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            JTextArea texto = new JTextArea(
                    "Ao entrar, sua equipe ouve um barulho de tiro na parte oeste da mansão,\nVocês decidem se separar para explorar...");
            texto.setWrapStyleWord(true);
            texto.setLineWrap(true);
            texto.setEditable(false);
            texto.setFocusable(false);
            texto.setOpaque(false);
            texto.setFont(Config.FONTE_PADRAO);
            texto.setForeground(Color.decode(Config.COR_TEXTO));

            JButton ok = new JButton("Prosseguir");
            ok.setAlignmentX(Component.CENTER_ALIGNMENT);
            ok.addActionListener(ev -> {
                primeiraVezHall = false;
                popHistoria.dispose();
            });

            ok.setForeground(Color.decode(Config.COR_DESTAQUE));
            ok.setBackground(Color.decode(Config.COR_BOTAO));
            ok.setFont(Config.FONTE_BOTAO);

            painel.add(Box.createVerticalStrut(20));
            painel.add(texto);
            painel.add(Box.createVerticalStrut(20));
            painel.add(ok);

            popHistoria.add(painel);
            popHistoria.setVisible(true);
        }

        if (Personagem.getChris() && CorredorPrimeiroZumbi.getViuZumbi()) {
            Itens.popupItem("pistola", "Você vê uma pistola no chão!", this);
            Inventario.adicionarItem(Config.PISTOLA);
        }

        mostrarImagem("/imgs/hall.png");

        mostrarTexto(
                "Você está no hall principal... \nVocê vê duas portas grandes, uma porta pequena, uma escadaria e uma velha maquina de escrever... \nA porta à direita parece trancada...");

        configurarBotoes();

        setVisible(true);
    }

    @Override
    public void configurarBotoes() {

        painelMenuHall = new JPanel();
        painelMenuHall.setPreferredSize(new Dimension(800, 120));
        painelMenuHall.setMaximumSize(new Dimension(800, 120));
        painelMenuHall.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelMenuHall.setOpaque(false);
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode(Config.COR_DESTAQUE), 3),
                "Menu",
                TitledBorder.CENTER,
                TitledBorder.TOP);
        borda.setTitleColor(Color.decode(Config.COR_DESTAQUE));
        borda.setTitleFont(Config.FONTE_TITULO_BORDA);
        painelMenuHall.setBorder(borda);

        portaEsquerda = new JButton("Porta à Esquerda");
        portaDireita = new JButton("Porta à Direita");
        portaPequena = new JButton("Porta pequena");
        escadaria = new JButton("Escadaria");
        maquina = new JButton("Maquina de Escrever");

        portaEsquerda.addActionListener(e -> {
            this.dispose();
            new SalaJantar1();
        });
        portaDireita.addActionListener(e -> {
            Config.criaPopupPadrao("Porta grande à direita", null, "Não posso ir pra lá ainda.", this);
        });
        portaPequena.addActionListener(e -> {
            Config.criaPopupPadrao("Porta pequena à direita", null, "Não posso ir pra lá ainda.", this);
        });
        escadaria.addActionListener(e -> {
            Config.criaPopupPadrao("Escadaria", null, "Não posso ir pra lá ainda.", this);
        });
        maquina.addActionListener(e -> {
            Config.criaPopupPadrao("Maquina de escrever", "/imgs/maquina.png",
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
            PainelInventario.exibirInventario(this);
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

        painelMenuHall.add(navMenu);
        painelMenuHall.add(navInventario);
        painelPrincipal.add(painelMenuHall);
    }

    public static void main(String[] args) {
        new HallEntrada();
    }
}