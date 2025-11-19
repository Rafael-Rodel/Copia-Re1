package controller;

import model.Inventario;
import model.Itens;
import model.Personagem;
import view.*;

import javax.swing.*;
import java.awt.*;

import model.Config;

public class JogoController {

    private static boolean viuZumbiCorredor = false;
    private static boolean passagemBar = false;
    private static String emblemaInseridoBar = "dourado";

    public void iniciarJogo() {
        new PainelInventario();

        // Inventario.adicionarItem(Config.TESTE1);
        // Inventario.adicionarItem(Config.TESTE2);
        // Inventario.adicionarItem(Config.TESTE3);

        new TelaInicial();
    }

    public static void iniciaJill() {
        Personagem.setJill(true);
        Personagem.setVida(25);
        Inventario.adicionarItem(Config.PISTOLA);
        Inventario.adicionarItem(Config.FACA);
        Inventario.adicionarItem(Config.SPRAY);
    }

    public static void iniciaChris() {
        Personagem.setChris(true);
        Personagem.setVida(20);
        Inventario.adicionarItem(Config.FACA);
        Inventario.adicionarItem(Config.SPRAY);
    }

    public static void trocaCenario(JFrame parent, String nomeCenario) {
        parent.dispose();

        switch (nomeCenario) {
            case "HallEntrada" -> new HallEntrada();

            case "SalaJantar1" -> new SalaJantar1();

            case "SalaJantar2" -> new SalaJantar2();

            case "Corredor1AndarOeste" -> new Corredor1AndarOeste();

            case "SalaCorredor1" -> new SalaCorredor1();

            case "Bar" -> new Bar();

            case "BarAberto" -> new BarAberto();

            case "SalaBusto" -> new SalaBusto();
        }
    }

    public static void verificarEventosHall(JFrame parent) {
        if (Personagem.getChris() && viuZumbiCorredor) {
            Itens.popupItem("Pistola", "Você vê uma pistola no chão!", parent);
            Inventario.adicionarItem(Config.PISTOLA);
        }
    }

    public static void pegarBrasao(JFrame parent) {
        if (Inventario.possui(Config.EMBLEMA_VELHO)) {
            Config.criaPopupPadrao("Lareira", "/resources/imgs/lareira_vazia.png", "O brasão foi removido...", parent);
        } else {
            Config.criaPopupPadrao("Lareira", "/resources/imgs/emblema_parede.png", "O brasão parece removivel...",
                    parent);
            Itens.popupItem("Emblema velho", "Você pegou um emblema velho com um brasão de familia...", parent);
            Inventario.adicionarItem(Config.EMBLEMA_VELHO);
        }
    }

    public static void verificarEventosCorredor1(JFrame parent) {
        if (!viuZumbiCorredor) {
            viuZumbiCorredor = true;
            Config.criaPopupPadrao("Zumbi", "/resources/imgs/primeiro_zumbi.png",
                    "Que criatura é essa? \nEle está devorando meu colega!", parent);
            new CombateController(Config.ZUMBI).iniciar(parent);
        }
    }

    public static void pegarPartitura(JFrame parent) {
        if (Inventario.possui(Config.PARTITURA)) {
            Config.criaPopupPadrao("Estante", "/resources/imgs/estante_vazia.png",
                    "Uma estante com livros estranhos...", parent);
        } else {
            Config.criaPopupPadrao("Estante", "/resources/imgs/estante_bar.png", "Na estante há uma partitura...",
                    parent);
            Itens.popupItem("Partitura", "Você pegou uma partitura de piano...", parent);
            Inventario.adicionarItem(Config.PARTITURA);
        }
    }

    public static String verificarBar() {
        String classeBar;
        if (passagemBar) {
            classeBar = "BarAberto";
        } else {
            classeBar = "Bar";
        }
        return classeBar;
    }

    public static void tocarPiano(JFrame parent) {
        if (Inventario.possui(Config.PARTITURA)) {
            JDialog popPiano = new JDialog(parent, "Piano", true);
            popPiano.setSize(600, 250);
            popPiano.setLocationRelativeTo(parent);

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode(Config.COR_FUNDO));
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            JTextArea texto = new JTextArea("Deseja dourado a musica da partitura?");
            texto.setWrapStyleWord(true);
            texto.setLineWrap(true);
            texto.setEditable(false);
            texto.setFocusable(false);
            texto.setOpaque(false);
            texto.setFont(Config.FONTE_PADRAO);
            texto.setForeground(Color.decode(Config.COR_TEXTO));

            JButton dourado = new JButton("Tocar");
            JButton velho = new JButton("Sair");

            dourado.setForeground(Color.decode(Config.COR_TEXTO));
            dourado.setBackground(Color.decode(Config.COR_BOTAO));
            dourado.setFont(Config.FONTE_BOTAO);
            dourado.setAlignmentX(Component.CENTER_ALIGNMENT);

            velho.setForeground(Color.decode(Config.COR_DESTAQUE));
            velho.setBackground(Color.decode(Config.COR_BOTAO));
            velho.setFont(Config.FONTE_BOTAO);
            velho.setAlignmentX(Component.CENTER_ALIGNMENT);

            dourado.addActionListener(e -> {
                Config.criaPopupPadrao("Passagem Secreta!", null, "Uma passagem se abriu na parede do bar!", parent);
                Config.criaPopupPadrao("Partitura", null, "Essa partitura não é mais util e será descartada.", parent);
                Inventario.removerItem(Config.PARTITURA);
                passagemBar = true;
                popPiano.dispose();
                trocaCenario(parent, "BarAberto");
            });
            velho.addActionListener(e -> {
                popPiano.dispose();
            });

            painel.add(Box.createVerticalStrut(20));
            painel.add(texto);
            painel.add(Box.createVerticalStrut(10));
            painel.add(dourado);
            painel.add(Box.createVerticalStrut(10));
            painel.add(velho);

            popPiano.add(painel);
            popPiano.setVisible(true);
        } else {
            Config.criaPopupPadrao("Piano", null, "Um belo piano grande.", parent);
        }
    }

    public static void trocarBustoBar(JFrame parent) {
        if (Inventario.possui(Config.EMBLEMA_DOURADO) && Inventario.possui(Config.EMBLEMA_VELHO)) {
            JDialog popBusto = new JDialog(parent, "Busto", true);
            popBusto.setSize(600, 250);
            popBusto.setLocationRelativeTo(parent);

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode(Config.COR_FUNDO));
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            JTextArea texto = new JTextArea("O busto esta vazio, deseja colocar algum emblema?");
            texto.setWrapStyleWord(true);
            texto.setLineWrap(true);
            texto.setEditable(false);
            texto.setFocusable(false);
            texto.setOpaque(false);
            texto.setFont(Config.FONTE_PADRAO);
            texto.setForeground(Color.decode(Config.COR_TEXTO));

            JButton dourado = new JButton("Emblema dourado");
            JButton velho = new JButton("Emblema velho");

            dourado.setForeground(Color.decode(Config.COR_TEXTO));
            dourado.setBackground(Color.decode(Config.COR_BOTAO));
            dourado.setFont(Config.FONTE_BOTAO);
            dourado.setAlignmentX(Component.CENTER_ALIGNMENT);

            velho.setForeground(Color.decode(Config.COR_TEXTO));
            velho.setBackground(Color.decode(Config.COR_BOTAO));
            velho.setFont(Config.FONTE_BOTAO);
            velho.setAlignmentX(Component.CENTER_ALIGNMENT);

            dourado.addActionListener(e -> {
                Inventario.removerItem(Config.EMBLEMA_DOURADO);
                emblemaInseridoBar = "dourado";
                JogoController.trocaCenario(parent, "SalaBusto");
                popBusto.dispose();
            });
            velho.addActionListener(e -> {
                Inventario.removerItem(Config.EMBLEMA_VELHO);
                emblemaInseridoBar = "velho";
                JogoController.trocaCenario(parent, "SalaBusto");
                popBusto.dispose();
            });

            painel.add(Box.createVerticalStrut(20));
            painel.add(texto);
            painel.add(Box.createVerticalStrut(10));
            painel.add(dourado);
            painel.add(Box.createVerticalStrut(10));
            painel.add(velho);

            popBusto.add(painel);
            popBusto.setVisible(true);
        } else {
            Config.criaPopupPadrao("Busto", null, "O emblema parece removivel", parent);
            if (emblemaInseridoBar == "dourado") {
                emblemaInseridoBar = null;
                Itens.popupItem("Emblema dourado", "Você pegou um emblema dourado com um brasão de familia...", parent);
                Inventario.adicionarItem(Config.EMBLEMA_DOURADO);
            } else {
                emblemaInseridoBar = null;
                Itens.popupItem("Emblema dourado", "Você pegou um emblema velho com um brasão de familia...", parent);
                Inventario.adicionarItem(Config.EMBLEMA_VELHO);
            }
        }
    }

    public static Boolean checarPortaTrancadaBar() {
        Boolean portaTrancada;
        if (emblemaInseridoBar == null) {
            portaTrancada = true;
            return portaTrancada;
        } else {
            portaTrancada = false;
            return portaTrancada;
        }
    }
}
