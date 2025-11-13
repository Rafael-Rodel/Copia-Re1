package controller;

import java.util.HashMap;

import javax.swing.*;
import java.awt.*;

import model.Inventario;
import model.Itens;
import view.PainelInventario;

public class InventarioController {

    private final int LINHAS = 3;
    private final int COLUNAS = 2;
    private final int LARGURA_SLOT = 90;
    private final int ALTURA_SLOT = 70;
    private JPanel gridItens;

    private HashMap<Point, JLabel> slots = new HashMap<>();

    public InventarioController(JPanel gridItens) {
        this.gridItens = gridItens;
    }

    public void criarSlotsFixos() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);

        for (int linha = 0; linha < LINHAS; linha++) {
            for (int coluna = 0; coluna < COLUNAS; coluna++) {
                JLabel slot = new JLabel(); // vazio por padrão
                slot.setPreferredSize(new Dimension(LARGURA_SLOT, ALTURA_SLOT));
                slot.setHorizontalAlignment(SwingConstants.CENTER);
                slot.setVerticalAlignment(SwingConstants.CENTER);
                gbc.gridx = coluna;
                gbc.gridy = linha;
                gridItens.add(slot, gbc);
                slots.put(new Point(coluna, linha), slot);
            }
        }
    }

    public void colocarItem(String caminhoImagem, int linha, int coluna) {
        JLabel slot = slots.get(new Point(coluna, linha));
        if (slot != null) {
            ImageIcon icon = new ImageIcon(getClass().getResource(caminhoImagem));
            Image img = icon.getImage().getScaledInstance(LARGURA_SLOT, ALTURA_SLOT, Image.SCALE_SMOOTH);
            slot.setIcon(new ImageIcon(img));
        }
    }

    public void removerItem(int linha, int coluna) {
        JLabel slot = slots.get(new Point(coluna, linha));
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
        // limpa todos os slots primeiro
        for (JLabel slot : slots.values()) {
            slot.setIcon(null);
        }

        // reposiciona todos os itens atuais
        for (Itens item : Inventario.getItens()) {
            colocarItem(item.getCaminhoImagem(), item.getLinha(), item.getColuna());
        }

    }
}
