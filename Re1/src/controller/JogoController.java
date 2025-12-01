package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;

public class JogoController {
    private static boolean matouZumbiSalajantar = false;
    private static boolean matouCaseiro = false;
    private static boolean matouPlanta = false;
    private static boolean matouZumbiCorredorCentral = false;
    private static boolean portaC1O = false;
    private static boolean matouZumbiEscadaria2O = false;
    private static boolean matouZumbiEscadaria1O = false;
    private static boolean matouCaoCorredor = false;
    private static boolean matouZumbiQuadros = false;
    private static boolean matouZumbiPassagem = false;
    private static boolean matouZumbiEscadaria1L = false;
    private static boolean matouCaoPassagemCoberta = false;
    private static boolean viuZumbiCorredor = false;
    private static boolean passagemBar = false;
    private static boolean primeiraVezHall = true;
    private static String emblemaInseridoBar = "dourado";
    private static String emblemaInseridoLareira = "velho";
    private static String shotgunInserida = "nova";
    private static int distanciaArmadilha = 5;
    private static String cenarioAtual;
    private static final int CAPACIDADE_MAXIMA = 8;

    private static boolean shotgunQuebradaColetada = false;
    private static boolean municaoSArmazemColetada = false;
    private static boolean municaoPArmazemColetada = false;
    private static boolean municaoPCaseiroColetada = false;
    private static boolean municaoSCaseiroColetada = false;
    private static boolean municaoSafeRoom1LColetada = false;
    private static boolean chaveArmaduraColetada = false;
    private static boolean sprayPlantaColetado = false;
    private static boolean pistolaColetadaHall = false;
    private static boolean sprayCorredor1LColetado = false;
    private static boolean partituraColetada = false;
    private static boolean chaveEscudoColetada = false;
    private static boolean mapaColetado = false;
    private static boolean chaveMesaColetada = false;
    private static boolean herbicidaColetado = false;

    public void iniciarJogo() {
        new PainelInventario();

        new TelaInicial();
    }

    public static void iniciaJill() {
        Personagem.setJill(true);
        Personagem.setVida(25);
        InventarioController.adicionarItem(Config.pistola);
        InventarioController.adicionarItem(Config.faca);
        InventarioController.adicionarItem(Config.spray);
    }

    public static void iniciaChris() {
        Personagem.setChris(true);
        Personagem.setVida(20);
        InventarioController.adicionarItem(Config.faca);
        InventarioController.adicionarItem(Config.spray);
    }

    public static void trocaCenario(Window parent, String nomeCenario) {
        parent.dispose();

        cenarioAtual = nomeCenario;

        switch (nomeCenario) {
            case "HallEntrada" -> new HallEntrada();

            case "SalaJantar1" -> new SalaJantar1();

            case "SalaJantar2" -> new SalaJantar2();

            case "Corredor1Oeste" -> new Corredor1Oeste();

            case "SalaCorredor1" -> new SalaCorredor1();

            case "Bar" -> new Bar();

            case "BarAberto" -> new BarAberto();

            case "SalaBusto" -> new SalaBusto();

            case "SalaEstatua" -> new SalaEstatua();

            case "SalaQuadros" -> new SalaQuadros();

            case "CorredorCachorro" -> new CorredorCachorro();

            case "PontaCorredor1L" -> new PontaCorredor1L();

            case "Banheiro" -> new Banheiro();

            case "Corredor1Leste" -> new Corredor1Leste();

            case "SalaArmadilha" -> new SalaArmadilha();

            case "SalaEstar" -> new SalaEstar();

            case "PassagemTraseira" -> new PassagemTraseira();

            case "PassagemCoberta" -> new PassagemCoberta();

            case "Escadaria1L" -> new Escadaria1L();

            case "EscadariaHall" -> new EscadariaHall();

            case "SafeRoom1L" -> new SafeRoom1L();

            case "SafeRoom1O" -> new SafeRoom1O();

            case "SalaJantar2Andar" -> new SalaJantar2Andar();

            case "Escadaria1O" -> new Escadaria1O();

            case "Escadaria2O" -> new Escadaria2O();

            case "CorredorCentral" -> new CorredorCentral();

            case "JardimInterno" -> new JardimInterno();

            case "QuartoCaseiro" -> new QuartoCaseiro();

            case "ArmazemArmas" -> new ArmazemArmas();
        }
    }

    public static Image getIconePrincipal() {
        Image iconePrincipal = new ImageIcon(JogoController.class.getResource("/resources/imgs/icone.png")).getImage();

        return iconePrincipal;
    }

    public static String getCenarioAtual() {
        return cenarioAtual;
    }

    public static void criaPopupPadrao(String titulo, String caminhoImagem, String textoPop, Window parent) {
        JDialog popPadrao = new JDialog(parent, titulo, Dialog.ModalityType.APPLICATION_MODAL);
        popPadrao.setSize(500, 250);
        popPadrao.setLocationRelativeTo(parent);

        JPanel painel = new JPanel();
        painel.setBackground(Color.decode(Config.COR_FUNDO));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
        if (Personagem.getChris() && viuZumbiCorredor && !pistolaColetadaHall) {

            Itens.popupItem(Config.textoPistola, "Você vê uma pistola no chão!", parent);

            if (InventarioController.tentarAdicionarItem(Config.pistola, parent)) {
                pistolaColetadaHall = true;
            }
        }

        if (primeiraVezHall) {
            primeiraVezHall = false;
            criaPopupPadrao("História", null,
                    "Ao entrar, sua equipe ouve um barulho de tiro na parte oeste da mansão,\nVocês decidem se separar para explorar...",
                    parent);
        }
    }

    public static void pegarBrasaoLareira(JFrame parent) {
        if (emblemaInseridoLareira == null) {
            JDialog popLareira = new JDialog(parent, "Lareira", true);
            popLareira.setSize(600, 450);
            popLareira.setLocationRelativeTo(parent);

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode(Config.COR_FUNDO));
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            ImageIcon imagem = new ImageIcon(Itens.class.getResource("/resources/imgs/lareira_vazia.png"));
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
                    InventarioController.removerItem(Config.emblemaDourado);
                    emblemaInseridoLareira = "dourado";
                    JogoController.trocaCenario(parent, "SalaJantar2");
                    popLareira.dispose();

                });
                painel.add(Box.createVerticalStrut(10));
                painel.add(dourado);
            }
            if (Inventario.possui(Config.emblemaVelho)) {
                velho.addActionListener(e -> {
                    InventarioController.removerItem(Config.emblemaVelho);
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

            if (emblemaInseridoLareira.equals("dourado")) {
                Itens.popupItem("Emblema dourado", "Você pegou um emblema dourado com um brasão de familia...", parent);

                if (InventarioController.tentarAdicionarItem(Config.emblemaDourado, parent)) {
                    emblemaInseridoLareira = null;
                }
            } else {
                Itens.popupItem("Emblema velho", "Você pegou um emblema velho com um brasão de familia...", parent);

                if (InventarioController.tentarAdicionarItem(Config.emblemaVelho, parent)) {
                    emblemaInseridoLareira = null;
                }
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
        if (Inventario.possui(Config.partitura) || partituraColetada) {
            criaPopupPadrao("Estante", "/resources/imgs/estante_vazia.png",
                    "Uma estante com livros estranhos...", parent);
        } else {
            criaPopupPadrao("Estante", "/resources/imgs/estante_bar.png", "Na estante há uma partitura...",
                    parent);

            if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                Itens.popupItem("Partitura", "Você pegou uma partitura de piano...", parent);
                InventarioController.tentarAdicionarItem(Config.partitura, parent);
                partituraColetada = true;
            } else {
                InventarioController.exibirAvisoInventarioCheio(parent);
            }
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

            JButton tocar = new JButton("Tocar");
            JButton sair = new JButton("Sair");

            tocar.setForeground(Color.decode(Config.COR_TEXTO));
            tocar.setBackground(Color.decode(Config.COR_BOTAO));
            tocar.setFont(Config.FONTE_BOTAO);
            tocar.setAlignmentX(Component.CENTER_ALIGNMENT);

            sair.setForeground(Color.decode(Config.COR_DESTAQUE));
            sair.setBackground(Color.decode(Config.COR_BOTAO));
            sair.setFont(Config.FONTE_BOTAO);
            sair.setAlignmentX(Component.CENTER_ALIGNMENT);

            tocar.addActionListener(e -> {
                criaPopupPadrao("Passagem Secreta!", null, "Uma passagem se abriu na parede do bar!", parent);
                criaPopupPadrao("Partitura", null, "Essa partitura não é mais util e será descartada.", parent);
                InventarioController.removerItem(Config.partitura);
                passagemBar = true;
                popPiano.dispose();
                trocaCenario(parent, "BarAberto");
            });
            sair.addActionListener(e -> {
                popPiano.dispose();
            });

            painel.add(Box.createVerticalStrut(20));
            painel.add(texto);
            painel.add(Box.createVerticalStrut(10));
            painel.add(tocar);
            painel.add(Box.createVerticalStrut(10));
            painel.add(sair);

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
                    InventarioController.removerItem(Config.emblemaDourado);
                    emblemaInseridoBar = "dourado";
                    JogoController.trocaCenario(parent, "SalaBusto");
                    popBusto.dispose();

                });
                painel.add(Box.createVerticalStrut(10));
                painel.add(dourado);
            }
            if (Inventario.possui(Config.emblemaVelho)) {
                velho.addActionListener(e -> {
                    InventarioController.removerItem(Config.emblemaVelho);
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
                InventarioController.tentarAdicionarItem(Config.emblemaDourado, parent);
            } else {
                emblemaInseridoBar = null;
                Itens.popupItem("Emblema velho", "Você pegou um emblema velho com um brasão de familia...", parent);
                InventarioController.tentarAdicionarItem(Config.emblemaVelho, parent);
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

            if (!chaveEscudoColetada) {
                criaPopupPadrao("Relogio", "/resources/imgs/relogio_aberto.png",
                        "O relógio se moveu sozinho! Atrás há um cofre agora aberto. \nHá uma chave no cofre.", parent);

                if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                    Itens.popupItem("Chave escudo", "Você achou uma chave!", parent);
                    InventarioController.tentarAdicionarItem(Config.chaveEscudo, parent);

                    chaveEscudoColetada = true;
                } else {
                    InventarioController.exibirAvisoInventarioCheio(parent);
                }

            } else {
                criaPopupPadrao("Relogio", "/resources/imgs/relogio_aberto.png",
                        "Atras do relógio há um cofre aberto vazio.", parent);
            }

        } else {
            criaPopupPadrao("Relógio", "/resources/imgs/relogio.png",
                    "Um velho relógio \nParece ter algo atrás mas não consigo move-lo...", parent);
        }
    }

    public static void pegarMapa(Window parent) {
        if (!mapaColetado) {
            criaPopupPadrao("Mapa", null,
                    "Você achou um mapa do primeiro andar da mansão! \nO mapa está disponivel no inventario.", parent);

            MapaController.setPossuiMapa(true);

            mapaColetado = true;

            trocaCenario(parent, "SalaEstatua");
        } else {
            criaPopupPadrao("Estatua", null,
                    "A estatua não possui mais nada.", parent);
        }
    }

    public static void eventosSalaQuadros(Window parent) {
        if (!matouZumbiQuadros) {
            matouZumbiQuadros = true;
            criaPopupPadrao("Sala Quadros", null, "Ao entrar na sala você se depara com um " + Config.textoZumbi,
                    parent);
            new CombateController(Config.zumbi).iniciar(parent);
            ;
        }
    }

    public static void eventosCorredorCachorro(Window parent) {
        if (!matouCaoCorredor) {
            matouCaoCorredor = true;
            criaPopupPadrao("Corredor", null, "Ao entrar no corredor você se depara com um " + Config.textoCaoZumbi,
                    parent);
            new CombateController(Config.caoZumbi).iniciar(parent);
            ;
        }
    }

    public static void eventosCorredor1L(Window parent) {
        Itens itemSpray = Config.spray;

        if (!sprayCorredor1LColetado) {
            Itens.popupItem("spray", "Você encontra uma lata de spray no chão!", parent);

            boolean adicionadoComSucesso = false;

            if (Inventario.possui(itemSpray)) {
                itemSpray.setQuantidade(itemSpray.getQuantidade() + 1);
                adicionadoComSucesso = true;
            } else {
                if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                    InventarioController.tentarAdicionarItem(Config.spray, parent);
                    adicionadoComSucesso = true;
                } else {
                    InventarioController.exibirAvisoInventarioCheio(parent);
                }
            }

            if (adicionadoComSucesso) {
                sprayCorredor1LColetado = true;
            }
        }
    }

    public static void eventosSalaArmadilha(Window parent) {
        if (shotgunInserida == null) {
            criaPopupPadrao("Armadilha!", null, "A " + Config.textoShotgun + " era uma armadilha!", parent);

            JDialog popUp = new JDialog(parent, "Armadilha!", Dialog.ModalityType.APPLICATION_MODAL);
            popUp.setSize(500, 400);
            popUp.setLocationRelativeTo(parent);
            popUp.setUndecorated(true);

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode(Config.COR_FUNDO));
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            ImageIcon icon = new ImageIcon(JogoController.class.getResource("/resources/imgs/teto.png"));
            Image scaled = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            JLabel imagem = new JLabel(new ImageIcon(scaled));
            imagem.setAlignmentX(Component.CENTER_ALIGNMENT);
            painel.add(imagem);

            painel.add(Box.createVerticalStrut(10));

            JTextArea texto = new JTextArea(
                    "As portas estão trancadas!\nO teto está a " + distanciaArmadilha + " metros de distancia!");
            texto.setWrapStyleWord(true);
            texto.setLineWrap(true);
            texto.setEditable(false);
            texto.setFocusable(false);
            texto.setOpaque(false);
            texto.setFont(Config.FONTE_PADRAO);
            texto.setForeground(Color.decode(Config.COR_TEXTO));
            texto.setAlignmentX(Component.CENTER_ALIGNMENT);
            painel.add(texto);

            final Timer armadilhaTimer[] = new Timer[1];

            armadilhaTimer[0] = new Timer(1500, e -> {
                distanciaArmadilha--;

                texto.setText(
                        "As portas estão trancadas!\nO teto está a " + distanciaArmadilha + " metros de distancia!");

                popUp.revalidate();
                popUp.repaint();

                if (Personagem.getJill()) {
                    if (distanciaArmadilha == 2) {
                        armadilhaTimer[0].stop();
                        popUp.dispose();
                        criaPopupPadrao("Ajuda!", null, "'Jill você está ai? Se afaste vou derrubar a porta!'", parent);
                        criaPopupPadrao("História", "/resources/imgs/jill_sanduiche.png",
                                "Jill - Muito obrigada Barry, se você não estivesse aqui eu poderia ter morrido!\nBarry - Se eu não chegasse a tempo você teria virado um sanduiche de Jill, mas chega de conversa, vamos continuar a exploração!",
                                parent);
                    }
                }

                if (distanciaArmadilha == 0) {
                    armadilhaTimer[0].stop();
                    popUp.dispose();
                    JogoController.criaPopupPadrao("GAME OVER", Config.imgMorte, "Você morreu na armadilha...", parent);
                    System.exit(0);
                }
            });

            armadilhaTimer[0].start();
            popUp.add(painel);
            popUp.setVisible(true);
        }
    }

    public static void pegarShotgun(JFrame parent) {
        if (shotgunInserida == null) {
            JDialog popShotgun = new JDialog(parent, "Moldura", true);
            popShotgun.setSize(600, 450);
            popShotgun.setLocationRelativeTo(parent);

            JPanel painel = new JPanel();
            painel.setBackground(Color.decode(Config.COR_FUNDO));
            painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
            painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            ImageIcon imagem = new ImageIcon(Itens.class.getResource("/resources/imgs/moldura_vazia.png"));
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

            JTextArea texto = new JTextArea("O espaço na moldura esta vazio, deseja colocar alguma coisa?");
            texto.setWrapStyleWord(true);
            texto.setLineWrap(true);
            texto.setEditable(false);
            texto.setFocusable(false);
            texto.setOpaque(false);
            texto.setFont(Config.FONTE_PADRAO);
            texto.setForeground(Color.decode(Config.COR_TEXTO));

            JButton nova = new JButton(Config.textoShotgun + " nova");
            JButton velho = new JButton(Config.textoShotgunQuebrada);

            nova.setForeground(Color.decode(Config.COR_TEXTO));
            nova.setBackground(Color.decode(Config.COR_BOTAO));
            nova.setFont(Config.FONTE_BOTAO);
            nova.setAlignmentX(Component.CENTER_ALIGNMENT);

            velho.setForeground(Color.decode(Config.COR_TEXTO));
            velho.setBackground(Color.decode(Config.COR_BOTAO));
            velho.setFont(Config.FONTE_BOTAO);
            velho.setAlignmentX(Component.CENTER_ALIGNMENT);

            painel.add(Box.createVerticalStrut(20));
            painel.add(texto);

            if (Inventario.possui(Config.shotgun)) {
                nova.addActionListener(e -> {
                    InventarioController.removerItem(Config.shotgun);
                    shotgunInserida = "nova";
                    JogoController.trocaCenario(parent, "SalaEstar");
                    popShotgun.dispose();

                });
                painel.add(Box.createVerticalStrut(10));
                painel.add(nova);
            }
            if (Inventario.possui(Config.shotgunQuebrada)) {
                velho.addActionListener(e -> {
                    InventarioController.removerItem(Config.shotgunQuebrada);
                    shotgunInserida = "velho";
                    JogoController.trocaCenario(parent, "SalaEstar");
                    popShotgun.dispose();
                });
                painel.add(Box.createVerticalStrut(10));
                painel.add(velho);
            }

            popShotgun.add(painel);
            popShotgun.setVisible(true);
        } else {
            criaPopupPadrao("Moldura", null, "A " + Config.textoShotgun
                    + " na moldura parece real, \nAo tirar a arma você ouve um barulho de mecanismo ativando, sera uma armadilha?",
                    parent);

            if (shotgunInserida.equals("nova")) {
                Itens.popupItem(Config.textoShotgun, "Você pegou uma " + Config.textoShotgun, parent);
                if (InventarioController.tentarAdicionarItem(Config.shotgun, parent)) {
                    shotgunInserida = null;
                }
            } else {
                Itens.popupItem(Config.textoShotgunQuebrada, "Você pegou uma " + Config.textoShotgunQuebrada, parent);
                if (InventarioController.tentarAdicionarItem(Config.shotgunQuebrada, parent)) {
                    shotgunInserida = null;
                }
            }
        }
    }

    public static void pegarMunicaoPistola(Window parent) {
        Itens itemPistola = Config.pistola;

        criaPopupPadrao("Munição", "/resources/imgs/carregador.png", "Você achou um carregador de pistola!", parent);
        itemPistola.setQuantidade(itemPistola.getQuantidade() + 7);
    }

    public static void pegarMunicaoShotgun(Window parent) {
        Itens itemShotgun = Config.shotgun;

        criaPopupPadrao("Munição", "/resources/imgs/cartucho.png", "Você achou uma caixa de cartuchos de Shotgun!",
                parent);
        itemShotgun.setQuantidade(itemShotgun.getQuantidade() + 4);
    }

    public static void eventosBanheiro(Window parent) {
        if (Personagem.getChris()) {

            if (!chaveMesaColetada) {

                if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                    Itens.popupItem("Chave de mesa", "Esvaziando a banheira você acha uma chave de mesa.", parent);
                    InventarioController.tentarAdicionarItem(Config.chaveMesa, parent);

                    chaveMesaColetada = true;
                } else {
                    InventarioController.exibirAvisoInventarioCheio(parent);
                }

            } else {
                criaPopupPadrao("Banheira", null, "Não há mais nada dentro da banheira.", parent);
            }

        } else {
            criaPopupPadrao("Banheira", null, "Não há nada dentro da banheira.", parent);
        }
    }

    public static void checkArmadilha(Window parent) {
        if (shotgunInserida == null) {
            criaPopupPadrao("Bloqueado", null, "A armadilha agora está bloqueando a porta...", parent);
        } else {
            trocaCenario(parent, "SalaArmadilha");
        }
    }

    public static void eventoPassagem(Window parent) {
        if (!matouZumbiPassagem) {
            matouZumbiPassagem = true;
            criaPopupPadrao("Passagem", null, "Ao entrar na passagem você se depara com um " + Config.textoZumbi,
                    parent);
            new CombateController(Config.zumbi).iniciar(parent);
        }
    }

    public static void eventoPassagemCoberta(Window parent) {
        if (!matouCaoPassagemCoberta) {
            matouCaoPassagemCoberta = true;
            criaPopupPadrao("Passagem", null, "Ao entrar na passagem você se depara com um " + Config.textoCaoZumbi,
                    parent);
            new CombateController(Config.caoZumbi).iniciar(parent);
        }
    }

    public static void eventosEscadaria1L(Window parent) {
        Itens itemSpray = Config.spray;

        if (!matouZumbiEscadaria1L) {
            matouZumbiEscadaria1L = true;
            criaPopupPadrao("Escadaria", null, "Ao entrar na escadaria você se depara com um " + Config.textoZumbi,
                    parent);
            new CombateController(Config.zumbi).iniciar(parent);

            Itens.popupItem("spray", "Você encontra uma lata de spray no chão!", parent);

            if (Inventario.possui(itemSpray)) {
                itemSpray.setQuantidade(itemSpray.getQuantidade() + 1);
            } else {
                InventarioController.tentarAdicionarItem(Config.spray, parent);
            }
        }
    }

    public static void eventosSalaJantar2Andar(Window parent) {
        if (!matouZumbiSalajantar) {
            matouZumbiSalajantar = true;
            criaPopupPadrao("Sala Jantar", null,
                    "Ao entrar na sala de jantar você se depara com um " + Config.textoZumbi,
                    parent);
            new CombateController(Config.zumbi).iniciar(parent);
        }
    }

    public static void pegarHerbicida(JFrame parent) {
        if (Inventario.possui(Config.herbicida) || herbicidaColetado) {
            criaPopupPadrao("Quimicos", null, "Uma pilha de produtos quimicos...", parent);
        } else {
            criaPopupPadrao("Quimicos", null, "Uma pilha de produtos quimicos...", parent);

            if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                Itens.popupItem("Herbicida", "Você pegou um pacote de herbicida...", parent);
                InventarioController.tentarAdicionarItem(Config.herbicida, parent);

                herbicidaColetado = true;
            } else {
                InventarioController.exibirAvisoInventarioCheio(parent);
            }
        }
    }

    public static void eventoSafeRoom1O(JFrame parent) {

        if (!Inventario.possui(Config.chaveEspada) && Personagem.getChris()) {
            if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                Itens.popupItem("Chave espada", "Você achou uma chave com entalhe de espada.", parent);

                InventarioController.tentarAdicionarItem(Config.chaveEspada, parent);
            } else {
                InventarioController.exibirAvisoInventarioCheio(parent);
            }
        }
        if (!municaoSafeRoom1LColetada) {
            pegarMunicaoPistola(parent);

            municaoSafeRoom1LColetada = true;
        }
    }

    public static void eventosEscadaria2O(Window parent) {
        if (!matouZumbiEscadaria2O) {
            matouZumbiEscadaria2O = true;
            criaPopupPadrao("Escadaria", null, "Ao entrar na escadaria você se depara com um " + Config.textoZumbi,
                    parent);
            new CombateController(Config.zumbi).iniciar(parent);
        }
    }

    public static void eventosEscadaria1O(Window parent) {
        if (!matouZumbiEscadaria1O) {
            matouZumbiEscadaria1O = true;
            criaPopupPadrao("Escadaria", null, "Ao entrar na escadaria você se depara com um " + Config.textoZumbi,
                    parent);
            new CombateController(Config.zumbi).iniciar(parent);
        }
    }

    public static void checaChaveArmadura(Window parent, String sala) {
        Itens chave = Config.chaveArmadura;

        if (Inventario.possui(chave)) {
            criaPopupPadrao("Porta aberta", null, "Você usou a chave armadura para abrir a porta",
                    parent);
            trocaCenario(parent, sala);
        } else {
            criaPopupPadrao("Porta trancada", null, "A porta está trancada, uma armadura entalhada...",
                    parent);
        }
    }

    public static void checaChaveEspada(Window parent, String sala) {
        Itens chave = Config.chaveEspada;

        if (Inventario.possui(chave)) {
            criaPopupPadrao("Porta aberta", null, "Você usou a chave espada para abrir a porta",
                    parent);
            trocaCenario(parent, sala);
        } else {
            criaPopupPadrao("Porta trancada", null, "A porta está trancada, uma espada entalhada...",
                    parent);
        }
    }

    public static void verificarPortaC1O(Window parent) {
        if (portaC1O) {
            trocaCenario(parent, "CorredorCentral");
        } else {
            criaPopupPadrao("Porta de madeira", null, "Parece trancado por dentro.", parent);
        }
    }

    public static void eventosCorredorCentral(Window parent) {
        if (!matouZumbiCorredorCentral) {
            matouZumbiCorredorCentral = true;
            criaPopupPadrao("corredor", null, "Ao entrar no corredor você se depara com um " + Config.textoZumbi,
                    parent);
            new CombateController(Config.zumbi).iniciar(parent);
        }
    }

    public static void abrirPortaC1O(Window parent) {
        portaC1O = true;
        criaPopupPadrao("Abriu", null, "Você abriu a porta que estava trancada por dentro!", parent);
        trocaCenario(parent, "Corredor1Oeste");
    }

    public static void matarPlanta(JFrame parent) {
        if (!matouPlanta) {
            if (Inventario.possui(Config.herbicida)) {
                JDialog popPlanta = new JDialog(parent, "Planta", true);
                popPlanta.setSize(600, 250);
                popPlanta.setLocationRelativeTo(parent);

                JPanel painel = new JPanel();
                painel.setBackground(Color.decode(Config.COR_FUNDO));
                painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
                painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

                JTextArea texto = new JTextArea("Deseja usar herbicida para matar a planta?");
                texto.setWrapStyleWord(true);
                texto.setLineWrap(true);
                texto.setEditable(false);
                texto.setFocusable(false);
                texto.setOpaque(false);
                texto.setFont(Config.FONTE_PADRAO);
                texto.setForeground(Color.decode(Config.COR_TEXTO));

                JButton herbicida = new JButton("Matar planta");
                JButton sair = new JButton("Sair");

                herbicida.setForeground(Color.decode(Config.COR_TEXTO));
                herbicida.setBackground(Color.decode(Config.COR_BOTAO));
                herbicida.setFont(Config.FONTE_BOTAO);
                herbicida.setAlignmentX(Component.CENTER_ALIGNMENT);

                sair.setForeground(Color.decode(Config.COR_DESTAQUE));
                sair.setBackground(Color.decode(Config.COR_BOTAO));
                sair.setFont(Config.FONTE_BOTAO);
                sair.setAlignmentX(Component.CENTER_ALIGNMENT);

                herbicida.addActionListener(e -> {
                    criaPopupPadrao("Planta!", null, "Você matou a planta monstruosa, parece haver algo atras dela!",
                            parent);
                    criaPopupPadrao("Partitura", null, "O herbicida acabou...", parent);
                    InventarioController.removerItem(Config.herbicida);
                    popPlanta.dispose();
                    matouPlanta = true;
                });
                sair.addActionListener(e -> {
                    popPlanta.dispose();
                });

                painel.add(Box.createVerticalStrut(20));
                painel.add(texto);
                painel.add(Box.createVerticalStrut(10));
                painel.add(herbicida);
                painel.add(Box.createVerticalStrut(10));
                painel.add(sair);

                popPlanta.add(painel);
                popPlanta.setVisible(true);
            } else {
                criaPopupPadrao("Planta gigante", null, "Uma planta gigante e agressiva, nãp consigo passar por ela.",
                        parent);
            }
        } else {
            criaPopupPadrao("chave", null, "Atras da planta havia uma chave escondida e uma lata de spray.", parent);

            if (!chaveArmaduraColetada) {
                if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                    Itens.popupItem("Chave armadura", "Você achou uma chave com gravura de armadura!", parent);
                    InventarioController.tentarAdicionarItem(Config.chaveArmadura, parent);

                    chaveArmaduraColetada = true;
                } else {
                    InventarioController.exibirAvisoInventarioCheio(parent);
                }
            }

            if (!sprayPlantaColetado) {
                if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                    Itens.popupItem("Spray", "Você achou uma lata de spray!", parent);
                    InventarioController.tentarAdicionarItem(Config.herbicida, parent);

                    sprayPlantaColetado = true;
                } else {
                    InventarioController.exibirAvisoInventarioCheio(parent);
                }
            }
        }
    }

    public static void pegarMunicaoPistolaCaseiro(Window parent) {
        if (!municaoPCaseiroColetada) {
            pegarMunicaoPistola(parent);

            municaoPCaseiroColetada = true;
        } else {
            criaPopupPadrao("vazio", null, "Não há mais nada.", parent);
        }
    }

    public static void pegarMunicaoShotgunCaseiro(Window parent) {
        if (!municaoSCaseiroColetada) {
            pegarMunicaoShotgun(parent);

            municaoSCaseiroColetada = true;
        } else {
            criaPopupPadrao("vazio", null, "Não há mais nada.", parent);
        }
    }

    public static void eventoQuartoCaseiro(Window parent) {
        if (!matouCaseiro) {
            matouCaseiro = true;
            criaPopupPadrao("Quarto caseiro", null, "Ao entrar no quarto você se depara com um " + Config.textoZumbi,
                    parent);
            new CombateController(Config.zumbi).iniciar(parent);
        }
    }

    public static void pegarShotgunVelha(Window parent) {
        if (Inventario.possui(Config.shotgunQuebrada) || shotgunQuebradaColetada) {
            criaPopupPadrao("Armario", null, "Um armario para armazenar armas...", parent);
        } else {
            criaPopupPadrao("Armario", null, "Um armario para armazenar armas...", parent);

            pegarMunicaoPistolaArmazem(parent);

            if (Inventario.getItens().size() < CAPACIDADE_MAXIMA) {
                Itens.popupItem(Config.textoShotgunQuebrada, "Você pegou uma " + Config.textoShotgunQuebrada, parent);
                InventarioController.tentarAdicionarItem(Config.shotgunQuebrada, parent);

                shotgunQuebradaColetada = true;
            } else {
                InventarioController.exibirAvisoInventarioCheio(parent);
            }
        }
    }

    public static void pegarMunicaoShotgunArmazem(Window parent) {
        if (!municaoSArmazemColetada) {
            pegarMunicaoShotgun(parent);

            municaoSArmazemColetada = true;
        } else {
            criaPopupPadrao("vazio", null, "Não há mais nada.", parent);
        }
    }

    public static void pegarMunicaoPistolaArmazem(Window parent) {
        if (!municaoPArmazemColetada) {
            pegarMunicaoPistola(parent);

            municaoPArmazemColetada = true;
        } else {
            criaPopupPadrao("vazio", null, "Não há mais nada.", parent);
        }
    }

    public static void checarChaveMesa(Window parent) {
        Itens chave = Config.chaveArmadura;

        if (Inventario.possui(chave)) {
            criaPopupPadrao("Gaveta aberta", null, "Você usou a chave para abrir a gaveta!", parent);
            pegarMunicaoShotgunArmazem(parent);
        } else {
            criaPopupPadrao("Gaveta trancada", null, "A gaveta está trancada...", parent);
        }
    }
}
