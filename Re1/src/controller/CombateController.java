package controller;

import javax.swing.*;
import java.awt.*;

import model.*;

public class CombateController {

    private Inimigo inimigo;
    private int distanciaAtual;
    JLabel textoDano;

    public CombateController(Inimigo inimigo) {
        this.inimigo = inimigo;
        this.distanciaAtual = inimigo.getPassosParaAlcancar();
    }

    public void iniciar(Window parent) {
        JDialog popUp = new JDialog(parent, "Combate - " + inimigo.getNome(), Dialog.ModalityType.APPLICATION_MODAL);
        popUp.setSize(500, 500);
        popUp.setLocationRelativeTo(parent);
        popUp.setUndecorated(true);

        JPanel painel = new JPanel();
        painel.setBackground(Color.decode(Config.COR_FUNDO));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ImageIcon icon = new ImageIcon(Itens.class.getResource(inimigo.getCaminhoImagem()));
        Image scaled = icon.getImage().getScaledInstance(215, 265, Image.SCALE_SMOOTH);
        JLabel imagem = new JLabel(new ImageIcon(scaled));
        imagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(imagem);

        painel.add(Box.createVerticalStrut(10));

        JTextArea texto = new JTextArea();
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setOpaque(false);
        texto.setFont(Config.FONTE_PADRAO);
        texto.setForeground(Color.decode(Config.COR_TEXTO));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(texto);

        JPanel painelDano = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelDano.setOpaque(false);
        painelDano.setPreferredSize(new Dimension(400, 30));
        painel.add(painelDano);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        painelBotoes.setOpaque(false);

        JButton atacar = new JButton("Atacar");
        JButton esquivar = new JButton("Esquivar");
        JButton fugir = new JButton("Fugir");

        atacar.setBackground(Color.decode(Config.COR_BOTAO));
        esquivar.setBackground(Color.decode(Config.COR_BOTAO));
        fugir.setBackground(Color.decode(Config.COR_BOTAO));

        atacar.setForeground(Color.decode(Config.COR_DESTAQUE));
        esquivar.setForeground(Color.decode(Config.COR_DESTAQUE));
        fugir.setForeground(Color.decode(Config.COR_DESTAQUE));

        painelBotoes.add(atacar);
        painelBotoes.add(esquivar);
        painelBotoes.add(fugir);

        painel.add(painelBotoes);

        JButton inventario = new JButton("Inventário");
        inventario.setAlignmentX(Component.CENTER_ALIGNMENT);
        inventario.setBackground(Color.decode(Config.COR_BOTAO));
        inventario.setForeground(Color.decode(Config.COR_DESTAQUE));
        painel.add(Box.createVerticalStrut(10));
        painel.add(inventario);

        popUp.add(painel);

        final Timer[] timer = new Timer[1];

        timer[0] = new Timer(1200, e -> {

            distanciaAtual -= inimigo.getVelocidade();
            atualizarTexto(texto);

            if (distanciaAtual <= 0) {
                Personagem.levarDano(inimigo.getDanoInimigo());

                if (textoDano == null) {
                    textoDano = new JLabel("LEVOU " + inimigo.getDanoInimigo() + " DE DANO!");
                    textoDano.setOpaque(false);
                    textoDano.setFont(Config.FONTE_PADRAO);
                    textoDano.setForeground(Color.decode(Config.COR_DESTAQUE));
                    textoDano.setAlignmentX(Component.CENTER_ALIGNMENT);
                    painelDano.add(textoDano);
                    painelDano.revalidate();
                    painelDano.repaint();
                }

                if (Personagem.getVida() <= 0) {
                    timer[0].stop();
                    popUp.dispose();
                    JogoController.criaPopupPadrao("GAME OVER", Config.imgMorte,
                            Config.textoMorte + " pelo " + inimigo.getNome() + "...", parent);
                    System.exit(0);
                }

                distanciaAtual = 8;
                atualizarTexto(texto);
            } else {
                if (textoDano != null) {
                    painelDano.remove(textoDano);
                    textoDano = null;
                    painelDano.revalidate();
                    painelDano.repaint();
                }
            }
        });

        timer[0].start();

        atacar.addActionListener(ev -> {
            Itens arma = Inventario.getEquipado();

            distanciaAtual += 1;
            limitarDistancia();

            if (arma == null) {
                JogoController.criaPopupPadrao("Aviso!", null,
                        "Você não possui arma equipada!", popUp);
                return;
            }

            switch (arma.getNome()) {
                case "Pistola" , "Pistola d'agua" -> {
                    JogoController.criaPopupPadrao("Ataque!", null,
                            "Você atacou com " + arma.getNome() + "!", popUp);
                    inimigo.darDano(10);

                    if (inimigo.getVidaInimigo() <= 0) {
                        timer[0].stop();
                        popUp.dispose();
                        JogoController.criaPopupPadrao("Vitória!", null,
                                "Você derrotou o " + inimigo.getNome() + "!", parent);
                        return;
                    }
                }
                case "Faca", "Pau" -> {
                    if (distanciaAtual > 5) {
                        JogoController.criaPopupPadrao("Muito longe!", null,
                                "O inimigo está muito distante, não consigo atacar com a faca...", parent);
                        return;
                    }
                    JogoController.criaPopupPadrao("Ataque!", null,
                            "Você atacou com " + arma.getNome() + "!", popUp);

                    inimigo.darDano(5);

                    if (inimigo.getVidaInimigo() <= 0) {
                        timer[0].stop();
                        popUp.dispose();
                        JogoController.criaPopupPadrao("Vitória!", null,
                                "Você derrotou o " + inimigo.getNome() + "!", parent);
                        return;
                    }
                }
                default -> {
                    JogoController.criaPopupPadrao("Aviso!", null,
                            "Você não possui arma equipada!", popUp);
                    return;
                }
            }

            atualizarTexto(texto);
        });

        esquivar.addActionListener(ev -> {
            esquivar.setBackground(Color.GRAY);
            esquivar.setForeground(Color.BLACK);
            esquivar.setEnabled(false);
            distanciaAtual += 4;
            limitarDistancia();
            atualizarTexto(texto);

            Timer esquivaTimer = new Timer(3000, e -> {
                timer[0].start();
                esquivar.setEnabled(true);
            esquivar.setBackground(Color.decode(Config.COR_BOTAO));
            esquivar.setForeground(Color.decode(Config.COR_DESTAQUE));
            });

            esquivaTimer.setRepeats(false);
            esquivaTimer.start();
        });

        fugir.addActionListener(ev -> {
            timer[0].stop();
            popUp.dispose();
            Personagem.levarDano(inimigo.getDanoInimigo());
            JogoController.criaPopupPadrao("Fuga!", null, "Você conseguiu fugir, mas o " + inimigo.getNome()
                    + " conseguiu te atacar, você levou " + inimigo.getDanoInimigo() + " de dano...", parent);

            if (Personagem.getVida() <= 0) {
                timer[0].stop();
                popUp.dispose();
                JogoController.criaPopupPadrao("GAME OVER", null,
                        "Você foi morto pelo " + inimigo.getNome() + "...", parent);
                System.exit(0);
            }

        });

        inventario.addActionListener(ev -> {
            InventarioController.exibirInventario(popUp);
        });

        atualizarTexto(texto);
        popUp.setVisible(true);
    }

    private void limitarDistancia() {
        if (distanciaAtual > 15)
            distanciaAtual = 15;
    }

    private void atualizarTexto(JTextArea texto) {

        if (distanciaAtual <= 5) {
            texto.setForeground(Color.RED);
        } else {
            texto.setForeground(Color.decode(Config.COR_TEXTO));
        }

        texto.setText(inimigo.getTextoAproximando() + "\n\nDistância restante: " + distanciaAtual + " passos.");
    }
}
