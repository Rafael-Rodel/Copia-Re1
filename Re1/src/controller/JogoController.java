package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;

public class JogoController { 
    private static boolean matouCaoCorredor = false;
    private static boolean matouZumbiQuadros = false;
    private static boolean viuZumbiCorredor = false;
    private static boolean passagemBar = false;
    private static String emblemaInseridoBar = "dourado";
    private static String emblemaInseridoLareira = "velho";
    private static String cenarioAtual;

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
        Inventario.adicionarItem(Config.pistola);
        Inventario.adicionarItem(Config.faca);
        Inventario.adicionarItem(Config.spray);
    }

    public static void iniciaChris() {
        Personagem.setChris(true);
        Personagem.setVida(20);
        Inventario.adicionarItem(Config.faca);
        Inventario.adicionarItem(Config.spray);
    }

    public static void trocaCenario(Window parent, String nomeCenario) {
        parent.dispose();

        cenarioAtual = nomeCenario;

        switch (nomeCenario) {
            case "HallEntrada" -> new HallEntrada();

            case "SalaJantar1" -> new SalaJantar1();

            case "SalaJantar2" -> new SalaJantar2();

            case "Corredor1AndarOeste" -> new Corredor1AndarOeste();

            case "SalaCorredor1" -> new SalaCorredor1();

            case "Bar" -> new Bar();

            case "BarAberto" -> new BarAberto();

            case "SalaBusto" -> new SalaBusto();

            case "SalaEstatua" -> new SalaEstatua();

            case "SalaQuadros" -> new SalaQuadros();

            case "CorredorCachorro" -> new CorredorCachorro();

            case "PontaCorredor1L" -> new PontaCorredor1L();
        }
    }

    public static String getCenarioAtual() {
        return cenarioAtual;
    }

    public static void criaPopupPadrao(String titulo, String caminhoImagem, String textoPop, Window parent) {
        JDialog popPadrao = new JDialog(parent, titulo, Dialog.ModalityType.APPLICATION_MODAL);
        popPadrao.setSize(400, 250);
        popPadrao.setLocationRelativeTo(parent);

        JPanel painel = new JPanel();
        painel.setBackground(Color.decode(Config.COR_FUNDO));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        if (caminhoImagem != null) {
            popPadrao.setSize(500, 450);
            popPadrao.setLocationRelativeTo(parent);

            ImageIcon imagem = new ImageIcon(Itens.class.getResource(caminhoImagem));
            Image redimensImage = imagem.getImage().getScaledInstance(300, 200,
                    Image.SCALE_SMOOTH);
            imagem = new ImageIcon(redimensImage);

            JPanel painelImagem = new JPanel();
            painelImagem.setOpaque(false);
            painelImagem.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

            JLabel img = new JLabel(imagem);
            img.setAlignmentX(Component.CENTER_ALIGNMENT);

            painelImagem.add(img);
            painel.add(painelImagem);
        }

        JTextArea texto = new JTextArea(textoPop);
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
            popPadrao.dispose();
        });

        ok.setForeground(Color.decode(Config.COR_DESTAQUE));
        ok.setBackground(Color.decode(Config.COR_BOTAO));
        ok.setFont(Config.FONTE_BOTAO);

        painel.add(Box.createVerticalStrut(20));
        painel.add(texto);
        painel.add(Box.createVerticalStrut(20));
        painel.add(ok);

        popPadrao.add(painel);
        popPadrao.setVisible(true);
    }

    public static void verificarEventosHall(JFrame parent) {
        if (Personagem.getChris() && viuZumbiCorredor) {
            Itens.popupItem("pistola", "Você vê uma pistola no chão!", parent);
            Inventario.adicionarItem(Config.pistola);
        }
    }

    public static void pegarBrasaoLareira(JFrame parent) {
        if (emblemaInseridoLareira == null) {
            JDialog popLareira = new JDialog(parent, "Lareira", true);
            popLareira.setSize(600, 250);
            popLareira.setLocationRelativeTo(parent);

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode(Config.COR_FUNDO));
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            JTextArea texto = new JTextArea("O espaço na lareira esta vazio, deseja colocar algum emblema?");
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

            painel.add(Box.createVerticalStrut(20));
            painel.add(texto);

            if (Inventario.possui(Config.emblemaDourado)) {
                dourado.addActionListener(e -> {
                    Inventario.removerItem(Config.emblemaDourado);
                    emblemaInseridoLareira = "dourado";
                    JogoController.trocaCenario(parent, "SalaJantar2");
                    popLareira.dispose();

                });
                painel.add(Box.createVerticalStrut(10));
                painel.add(dourado);
            }
            if (Inventario.possui(Config.emblemaVelho)) {
                velho.addActionListener(e -> {
                    Inventario.removerItem(Config.emblemaVelho);
                    emblemaInseridoLareira = "velho";
                    JogoController.trocaCenario(parent, "SalaJantar2");
                    popLareira.dispose();
                });
                painel.add(Box.createVerticalStrut(10));
                painel.add(velho);
            }

            popLareira.add(painel);
            popLareira.setVisible(true);
        } else {
            criaPopupPadrao("Lareira", null, "O emblema parece removivel", parent);
            if (emblemaInseridoLareira == "dourado") {
                emblemaInseridoLareira = null;
                Itens.popupItem("Emblema dourado", "Você pegou um emblema dourado com um brasão de familia...", parent);
                Inventario.adicionarItem(Config.emblemaDourado);
            } else {
                emblemaInseridoLareira = null;
                Itens.popupItem("Emblema velho", "Você pegou um emblema velho com um brasão de familia...", parent);
                Inventario.adicionarItem(Config.emblemaVelho);
            }
        }
    }

    public static void verificarEventosCorredor1(JFrame parent) {
        if (!viuZumbiCorredor) {
            viuZumbiCorredor = true;
            criaPopupPadrao("Criatura", Config.imgPrimeiroZumbi,
                    "Que criatura é essa? \nEle está devorando meu colega!", parent);
            new CombateController(Config.zumbi).iniciar(parent);
        }
    }

    public static void pegarPartitura(JFrame parent) {
        if (Inventario.possui(Config.partitura)) {
            criaPopupPadrao("Estante", "/resources/imgs/estante_vazia.png",
                    "Uma estante com livros estranhos...", parent);
        } else {
            criaPopupPadrao("Estante", "/resources/imgs/estante_bar.png", "Na estante há uma partitura...",
                    parent);
            Itens.popupItem("Partitura", "Você pegou uma partitura de piano...", parent);
            Inventario.adicionarItem(Config.partitura);
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
        if (Inventario.possui(Config.partitura)) {
            JDialog popPiano = new JDialog(parent, "Piano", true);
            popPiano.setSize(600, 250);
            popPiano.setLocationRelativeTo(parent);

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode(Config.COR_FUNDO));
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            JTextArea texto = new JTextArea("Deseja tocar a musica da partitura?");
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
                criaPopupPadrao("Passagem Secreta!", null, "Uma passagem se abriu na parede do bar!", parent);
                criaPopupPadrao("Partitura", null, "Essa partitura não é mais util e será descartada.", parent);
                Inventario.removerItem(Config.partitura);
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
            criaPopupPadrao("Piano", null, "Um belo piano grande.", parent);
        }
    }

    public static void trocarBustoBar(JFrame parent) {
        if (emblemaInseridoBar == null) {
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

            painel.add(Box.createVerticalStrut(20));
            painel.add(texto);

            if (Inventario.possui(Config.emblemaDourado)) {
                dourado.addActionListener(e -> {
                    Inventario.removerItem(Config.emblemaDourado);
                    emblemaInseridoBar = "dourado";
                    JogoController.trocaCenario(parent, "SalaBusto");
                    popBusto.dispose();

                });
                painel.add(Box.createVerticalStrut(10));
                painel.add(dourado);
            }
            if (Inventario.possui(Config.emblemaVelho)) {
                velho.addActionListener(e -> {
                    Inventario.removerItem(Config.emblemaVelho);
                    emblemaInseridoBar = "velho";
                    JogoController.trocaCenario(parent, "SalaBusto");
                    popBusto.dispose();
                });
                painel.add(Box.createVerticalStrut(10));
                painel.add(velho);
            }

            popBusto.add(painel);
            popBusto.setVisible(true);
        } else {
            criaPopupPadrao("Busto", null, "O emblema parece removivel", parent);
            if (emblemaInseridoBar == "dourado") {
                emblemaInseridoBar = null;
                Itens.popupItem("Emblema dourado", "Você pegou um emblema dourado com um brasão de familia...", parent);
                Inventario.adicionarItem(Config.emblemaDourado);
            } else {
                emblemaInseridoBar = null;
                Itens.popupItem("Emblema velho", "Você pegou um emblema velho com um brasão de familia...", parent);
                Inventario.adicionarItem(Config.emblemaVelho);
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

    public static void checarRelogio(Window parent) {
        if (emblemaInseridoLareira == "dourado") {
            if (Inventario.possui(Config.chaveEscudo)) {
                criaPopupPadrao("Relogio", "/resources/imgs/relogio_aberto.png",
                        "Atras do relógio há um cofre aberto vazio.", parent);
            } else {
                criaPopupPadrao("Relogio", "/resources/imgs/relogio_aberto.png",
                        "O relógio se moveu sozinho! Atrás há um cofre agora aberto. \nHá uma chave no cofre.", parent);
                Itens.popupItem("Chave escudo", "Você achou uma chave!", null);
                Inventario.adicionarItem(Config.chaveEscudo);
            }
        } else {
            criaPopupPadrao("Relógio", "/resources/imgs/relogio.png",
                    "Um velho relógio \nParece ter algo atrás mas não consigo move-lo...", parent);
        }
    }

    public static void pegarMapa(Window parent) {
        if (!MapaController.getPossuiMapa()) {
            criaPopupPadrao("Mapa", null,
                    "Você achou um mapa do primeiro andar da mansão! \nO mapa está disponivel no inventario.", parent);
            MapaController.setPossuiMapa(true);
            trocaCenario(parent, "SalaEstatua");
        } else {
            criaPopupPadrao("Estatua", null,
                    "A estatua não possui mais nada.", parent);
        }
    }

    public static void eventosSalaQuadros(Window parent) {
        if (!matouZumbiQuadros) {
            matouZumbiQuadros = true;
            criaPopupPadrao("Sala Quadros", null, "Ao entrar na sala você se depara com um " + Config.textoZumbi, parent);
            new CombateController(Config.zumbi).iniciar(parent);;
        }
    }

    public static void eventosCorredorCachorro(Window parent) {
        if (!matouCaoCorredor) {
            matouCaoCorredor = true;
            criaPopupPadrao("Corredor", null, "Ao entrar no corredor você se depara com um " + Config.textoZumbi, parent);
            new CombateController(Config.caoZumbi).iniciar(parent);;
        }
    }
}
