package controller;

import javax.swing.*;
import java.awt.*;

import model.Config;
import model.Inimigo;
import model.Itens;
import model.Inventario;
import model.Personagem;

public class CombateController {

    private Inimigo inimigo;
    private int distanciaAtual;

    public CombateController(Inimigo inimigo) {
        this.inimigo = inimigo;
        this.distanciaAtual = inimigo.getPassosParaAlcancar();
    }

    public void iniciar(Window parent) {
        JDialog popUp = new JDialog(parent, "Combate - " + inimigo.getNome(), Dialog.ModalityType.APPLICATION_MODAL);
        popUp.setSize(500, 500);
        popUp.setLocationRelativeTo(parent);

        JPanel painel = new JPanel();
        painel.setBackground(Color.decode(Config.COR_FUNDO));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ImageIcon icon = new ImageIcon(Itens.class.getResource(inimigo.getCaminhoImagem()));
        Image scaled = icon.getImage().getScaledInstance(260, 180, Image.SCALE_SMOOTH);
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

        painel.add(Box.createVerticalStrut(20));

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

                if (Personagem.getVida() <= 0) {
                    timer[0].stop();
                    popUp.dispose();
                    Config.criaPopupPadrao("GAME OVER", null,
                            "Você foi morto pelo " + inimigo.getNome() + "...", parent);
                    return;
                }

                distanciaAtual = 8;
                atualizarTexto(texto);
            }
        });

        timer[0].start();

        atacar.addActionListener(ev -> {
            Itens arma = Inventario.getEquipado();

            distanciaAtual += 1;
            limitarDistancia();

            switch (arma.getNome()) {
                case "Pistola" -> {
                    Config.criaPopupPadrao("Ataque!", null,
                            "Você atacou com " + arma.getNome() + "!", popUp);
                    inimigo.darDano(10);

                    if (inimigo.getVidaInimigo() <= 0) {
                        timer[0].stop();
                        popUp.dispose();
                        Config.criaPopupPadrao("Vitória!", null,
                                "Você derrotou o " + inimigo.getNome() + "!", parent);
                        return;
                    }
                }
                case "Faca" -> {
                    Config.criaPopupPadrao("Ataque!", null,
                            "Você atacou com " + arma.getNome() + "!", popUp);
                    inimigo.darDano(5);

                    if (inimigo.getVidaInimigo() <= 0) {
                        timer[0].stop();
                        popUp.dispose();
                        Config.criaPopupPadrao("Vitória!", null,
                                "Você derrotou o " + inimigo.getNome() + "!", parent);
                        return;
                    }
                }
                default -> {
                    Config.criaPopupPadrao("Aviso!", null,
                            "Você não possui arma equipada!", popUp);
                    return;
                }
            }

            atualizarTexto(texto);
        });

        esquivar.addActionListener(ev -> {
            distanciaAtual += 3;
            limitarDistancia();
            atualizarTexto(texto);
        });

        fugir.addActionListener(ev -> {
            timer[0].stop();
            popUp.dispose();
            Config.criaPopupPadrao("Fuga!", null, "Você conseguiu fugir!", parent);
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

        texto.setText(
                inimigo.getTextoAproximando()
                        + "\n\nDistância restante: "
                        + distanciaAtual
                        + " passos.");
    }
}
