package controller;

import java.util.HashMap;

import javax.swing.*;
import java.awt.*;

import model.Config;
import model.Inventario;
import model.Itens;
import view.PainelInventario;

public class InventarioController {

    private final int LINHAS = 4;
    private final int COLUNAS = 2;
    private final int LARGURA_SLOT = 90;
    private final int ALTURA_SLOT = 70;
    private JPanel gridItens;

    private HashMap<Point, JLabel> slotsImagem = new HashMap<>();
    private HashMap<Point, JButton> slotsBotao = new HashMap<>();

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

                // Garante que o botão não limite o tamanho
                botao.setMinimumSize(new Dimension(0, 0));
                botao.setPreferredSize(null);

                botao.addActionListener(e -> {
                    Itens item = (Itens) ((JButton) e.getSource()).getClientProperty("item");

                    if (item != null) {
                        Window parent = SwingUtilities.getWindowAncestor(gridItens);

                        Config.criaPopupPadrao(
                                item.getNome(),
                                item.getCaminhoImagem(),
                                item.getDescricao(),
                                parent);
                    }
                });

                JPanel overlay = new JPanel();
                overlay.setLayout(new OverlayLayout(overlay));
                overlay.setOpaque(false);

                botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
                imagem.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
                overlay.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
                slotPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

                overlay.add(botao);
                overlay.add(imagem);

                slotPanel.add(overlay, BorderLayout.CENTER);

                gbc.gridx = coluna;
                gbc.gridy = linha;

                gridItens.add(slotPanel, gbc);

                slotsImagem.put(new Point(coluna, linha), imagem);
                slotsBotao.put(new Point(coluna, linha), botao);
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

    public static void exibirInventario(JFrame parent) {
        JDialog popInventario = new JDialog(parent, "Inventário", true);
        popInventario.setSize(800, 600);
        popInventario.setLocationRelativeTo(parent);

        PainelInventario inventario = new PainelInventario();
        inventario.getController().atualizarInventario();
        popInventario.add(inventario);
        popInventario.setVisible(true);
    }

    public void atualizarInventario() {
        for (JLabel slot : slotsImagem.values()) {
            slot.setIcon(null);
        }

        for (Itens item : Inventario.getItens()) {
            colocarItem(item);
        }
    }
}
