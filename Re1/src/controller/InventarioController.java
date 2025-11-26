package controller;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

import model.*;
import view.PainelInventario;

public class InventarioController {

    private final int LINHAS = 4;
    private final int COLUNAS = 2;
    private final int LARGURA_SLOT = 90;
    private final int ALTURA_SLOT = 70;
    private JPanel gridItens;

    private HashMap<Point, JLabel> slotsImagem = new HashMap<>();
    private HashMap<Point, JButton> slotsBotao = new HashMap<>();
    private HashMap<Point, JLabel> slotsQuantidade = new HashMap<>();

    public InventarioController(JPanel gridItens) {
        this.gridItens = gridItens;
    }

    public void criarSlotsFixos() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        for (int linha = 0; linha < LINHAS; linha++) {
            for (int coluna = 0; coluna < COLUNAS; coluna++) {

                JPanel slotPanel = new JPanel();
                slotPanel.setLayout(new BorderLayout());
                slotPanel.setPreferredSize(new Dimension(LARGURA_SLOT, ALTURA_SLOT));
                slotPanel.setOpaque(false);

                JLabel imagem = new JLabel();
                imagem.setAlignmentX(0.5f);
                imagem.setAlignmentY(0.5f);

                JButton botao = new JButton();

                botao.setOpaque(false);
                botao.setContentAreaFilled(false);
                botao.setBorderPainted(false);
                botao.setFocusPainted(false);

                botao.setAlignmentX(1.0f);
                botao.setAlignmentY(1.0f);
                imagem.setAlignmentX(1.0f);
                imagem.setAlignmentY(1.0f);

                botao.setMinimumSize(new Dimension(0, 0));
                botao.setPreferredSize(null);

                botao.addActionListener(e -> {
                    Itens item = (Itens) ((JButton) e.getSource()).getClientProperty("item");

                    if (item != null) {
                        Window parent = SwingUtilities.getWindowAncestor(gridItens);

                        criaPopupItem(this, item, parent);
                    }
                });

                JLabel quantidadeSlot = new JLabel("");
                quantidadeSlot.setOpaque(false);
                quantidadeSlot.setFont(Config.FONTE_PADRAO.deriveFont(Font.BOLD, 20f));
                quantidadeSlot.setForeground(Color.green);

                quantidadeSlot.setHorizontalAlignment(SwingConstants.RIGHT);
                quantidadeSlot.setVerticalAlignment(SwingConstants.BOTTOM);

                quantidadeSlot.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 5));

                quantidadeSlot.setAlignmentX(1.0f);
                quantidadeSlot.setAlignmentY(1.0f);

                JPanel overlay = new JPanel();
                overlay.setLayout(new OverlayLayout(overlay));
                overlay.setOpaque(false);

                botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
                imagem.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
                overlay.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
                slotPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

                overlay.add(botao);
                overlay.add(quantidadeSlot);
                overlay.add(imagem);

                slotPanel.add(overlay, BorderLayout.CENTER);

                gbc.gridx = coluna;
                gbc.gridy = linha;

                gridItens.add(slotPanel, gbc);

                Point pos = new Point(coluna, linha);
                slotsImagem.put(pos, imagem);
                slotsBotao.put(pos, botao);
                slotsQuantidade.put(pos, quantidadeSlot);
            }
        }
    }

    public void colocarItem(Itens item) {
        Point pos = new Point(item.getColuna(), item.getLinha());

        JLabel imagem = slotsImagem.get(pos);
        JButton botao = slotsBotao.get(pos);

        if (imagem != null) {
            ImageIcon icon = new ImageIcon(getClass().getResource(item.getCaminhoImagem()));
            Image img = icon.getImage().getScaledInstance(LARGURA_SLOT, ALTURA_SLOT, Image.SCALE_SMOOTH);
            imagem.setIcon(new ImageIcon(img));
        }

        if (botao != null) {
            botao.putClientProperty("item", item);
        }
    }

    public void removerItem(int linha, int coluna) {
        JLabel slot = slotsImagem.get(new Point(coluna, linha));
        if (slot != null) {
            slot.setIcon(null);
        }
    }

    public static void exibirInventario(Window parent) {
        JDialog popInventario = new JDialog(parent, "Inventário", Dialog.ModalityType.APPLICATION_MODAL);
        popInventario.setSize(800, 600);
        popInventario.setLocationRelativeTo(parent);

        PainelInventario inventario = new PainelInventario();
        inventario.getController().atualizarInventario();
        inventario.atualizarEquipado();
        PainelInventario.getInstancia().atualizarVida();
        popInventario.add(inventario);
        popInventario.setVisible(true);
    }

    public void atualizarInventario() {
        for (Point p : slotsImagem.keySet()) {
            JLabel img = slotsImagem.get(p);
            JButton btn = slotsBotao.get(p);

            img.setIcon(null);
            btn.putClientProperty("item", null);

            JLabel qty = slotsQuantidade.get(p);
            if (qty != null) {
                qty.setText("");
            }
        }

        int index = 0;
        for (Itens item : Inventario.getItens()) {

            int linha = index / COLUNAS;
            int coluna = index % COLUNAS;
            Point pos = new Point(coluna, linha);

            JLabel img = slotsImagem.get(pos);
            JButton btn = slotsBotao.get(pos);
            JLabel qty = slotsQuantidade.get(pos);

            if (img != null) {
                ImageIcon icon = new ImageIcon(getClass().getResource(item.getCaminhoImagem()));
                Image scaled = icon.getImage().getScaledInstance(90, 70, Image.SCALE_SMOOTH);
                img.setIcon(new ImageIcon(scaled));
            }

            if (btn != null) {
                btn.putClientProperty("item", item);
            }

            if (qty != null) {
                if (item.getQuantidade() > 1) {
                    qty.setText(String.valueOf(item.getQuantidade()));
                } else {
                    qty.setText("");
                }
            }
            index++;
        }

        gridItens.revalidate();
        gridItens.repaint();
    }

    public static void criaPopupItem(InventarioController controller, Itens item, Window parent) {
        JDialog popPadrao = new JDialog(parent, item.getNome(), Dialog.ModalityType.APPLICATION_MODAL);
        popPadrao.setSize(400, 250);
        popPadrao.setLocationRelativeTo(parent);

        JPanel painel = new JPanel();
        painel.setBackground(Color.decode(Config.COR_FUNDO));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        if (item.getCaminhoImagem() != null) {
            popPadrao.setSize(500, 500);
            popPadrao.setLocationRelativeTo(parent);

            ImageIcon imagem = new ImageIcon(Itens.class.getResource(item.getCaminhoImagem()));
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

        JTextArea texto = new JTextArea(item.getDescricao());
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setOpaque(false);
        texto.setFont(Config.FONTE_PADRAO);
        texto.setForeground(Color.decode(Config.COR_TEXTO));

        painel.add(Box.createVerticalStrut(20));
        painel.add(texto);

        JButton usar;

        switch (item.getTipoItem()) {
            case "arma":
                usar = new JButton("Equipar");
                usar.setAlignmentX(Component.CENTER_ALIGNMENT);
                usar.addActionListener(ev -> {
                    Inventario.setEquipado(item);
                    popPadrao.dispose();
                });

                usar.setForeground(Color.decode(Config.COR_DESTAQUE));
                usar.setBackground(Color.decode(Config.COR_BOTAO));
                usar.setFont(Config.FONTE_BOTAO);

                painel.add(Box.createVerticalStrut(10));
                painel.add(usar);
                break;
            case "consumivel":
                usar = new JButton("Usar");
                usar.setAlignmentX(Component.CENTER_ALIGNMENT);
                usar.addActionListener(ev -> {
                    if (Personagem.getChris()) {
                        Personagem.setVida(20);
                    } else {
                        Personagem.setVida(25);
                    }
                    Inventario.consumirItem(item);
                    PainelInventario.getInstancia().atualizarVida();
                    popPadrao.dispose();
                });

                usar.setForeground(Color.decode(Config.COR_DESTAQUE));
                usar.setBackground(Color.decode(Config.COR_BOTAO));
                usar.setFont(Config.FONTE_BOTAO);

                painel.add(Box.createVerticalStrut(10));
                painel.add(usar);
                break;
        }

        JButton ok = new JButton("Prosseguir");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.addActionListener(ev -> {
            popPadrao.dispose();
        });

        ok.setForeground(Color.decode(Config.COR_DESTAQUE));
        ok.setBackground(Color.decode(Config.COR_BOTAO));
        ok.setFont(Config.FONTE_BOTAO);

        painel.add(Box.createVerticalStrut(10));
        painel.add(ok);

        popPadrao.add(painel);
        popPadrao.setVisible(true);
    }
}
