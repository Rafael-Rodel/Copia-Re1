package controller;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import model.*;
import view.PainelBau;

public class BauController {

    private final int LARGURA_SLOT = 90;
    private final int ALTURA_SLOT = 70;

    private final int LINHAS_BAU = 3;
    private final int COLUNAS_BAU = 4;
    private JPanel gridItensBau;
    private HashMap<Point, JLabel> slotsImagem = new HashMap<>();
    private HashMap<Point, JButton> slotsBotao = new HashMap<>();
    private HashMap<Point, JLabel> slotsQuantidade = new HashMap<>();

    private final int LINHAS_INV = 4;
    private final int COLUNAS_INV = 2;
    private JPanel gridItensInventario;
    private HashMap<Point, JLabel> slotsImagemInv = new HashMap<>();
    private HashMap<Point, JButton> slotsBotaoInv = new HashMap<>();
    private HashMap<Point, JLabel> slotsQuantidadeInv = new HashMap<>();

    public BauController(JPanel gridItensBau, JPanel gridItensInventario) {
        this.gridItensBau = gridItensBau;
        this.gridItensInventario = gridItensInventario;
    }

    public void criarSlotsFixos() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        for (int linha = 0; linha < LINHAS_BAU; linha++) {
            for (int coluna = 0; coluna < COLUNAS_BAU; coluna++) {

                JPanel slotPanel = new JPanel();
                slotPanel.setLayout(new BorderLayout());
                slotPanel.setPreferredSize(new Dimension(LARGURA_SLOT, ALTURA_SLOT));
                slotPanel.setOpaque(false);

                JLabel imagem = new JLabel();
                JButton botao = new JButton();

                botao.setOpaque(false);
                botao.setContentAreaFilled(false);
                botao.setBorderPainted(false);
                botao.setFocusPainted(false);
                botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

                botao.addActionListener(e -> {
                    Itens item = (Itens) ((JButton) e.getSource()).getClientProperty("item");

                    if (item != null) {
                        Window parent = SwingUtilities.getWindowAncestor(gridItensBau);
                        criaPopupItemBau(item, parent);
                    }
                });

                JLabel quantidadeSlot = new JLabel("");
                quantidadeSlot.setOpaque(false);
                quantidadeSlot.setForeground(Color.green);
                quantidadeSlot.setHorizontalAlignment(SwingConstants.RIGHT);
                quantidadeSlot.setVerticalAlignment(SwingConstants.BOTTOM);
                quantidadeSlot.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 5));

                JPanel overlay = new JPanel();
                overlay.setLayout(new OverlayLayout(overlay));
                overlay.setOpaque(false);

                overlay.add(botao);
                overlay.add(quantidadeSlot);
                overlay.add(imagem);
                slotPanel.add(overlay, BorderLayout.CENTER);

                gbc.gridx = coluna;
                gbc.gridy = linha;

                gridItensBau.add(slotPanel, gbc);

                Point pos = new Point(coluna, linha);
                slotsImagem.put(pos, imagem);
                slotsBotao.put(pos, botao);
                slotsQuantidade.put(pos, quantidadeSlot);
            }
        }
        atualizarBau();
    }

    public void atualizarBau() {
        for (Point p : slotsImagem.keySet()) {
            slotsImagem.get(p).setIcon(null);
            slotsBotao.get(p).putClientProperty("item", null);
            JLabel qty = slotsQuantidade.get(p);
            if (qty != null) {
                qty.setText("");
            }
        }

        List<Itens> itensBau = Bau.getItens();

        int index = 0;
        for (Itens item : itensBau) {
            int linha = index / COLUNAS_BAU;
            int coluna = index % COLUNAS_BAU;
            Point pos = new Point(coluna, linha);

            JLabel img = slotsImagem.get(pos);
            JButton btn = slotsBotao.get(pos);
            JLabel qty = slotsQuantidade.get(pos);

            if (img != null) {
                ImageIcon icon = new ImageIcon(getClass().getResource(item.getCaminhoImagem()));
                Image scaled = icon.getImage().getScaledInstance(LARGURA_SLOT, ALTURA_SLOT, Image.SCALE_SMOOTH);
                img.setIcon(new ImageIcon(scaled));
            }

            if (btn != null) {
                btn.putClientProperty("item", item);
            }

            if (qty != null && item.getQuantidade() > 1) {
                qty.setText(String.valueOf(item.getQuantidade()));
            }
            index++;
        }

        gridItensBau.revalidate();
        gridItensBau.repaint();
    }

    public void criaPopupItemBau(Itens item, Window parent) {
        JDialog popPadrao = new JDialog(parent, item.getNome(), Dialog.ModalityType.APPLICATION_MODAL);
        popPadrao.setSize(300, 300);
        popPadrao.setLocationRelativeTo(parent);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        painel.setBackground(Color.decode(Config.COR_FUNDO));

        ImageIcon imagemItem = new ImageIcon(Itens.class.getResource(item.getCaminhoImagem()));
        Image redimensImage = imagemItem.getImage().getScaledInstance(200, 150,
                Image.SCALE_SMOOTH);
        imagemItem = new ImageIcon(redimensImage);

        JPanel painelImagem = new JPanel();
        painelImagem.setOpaque(false);
        painelImagem.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JLabel labelItem = new JLabel(imagemItem);
        labelItem.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelImagem.add(labelItem);
        painel.add(painelImagem);

        painel.revalidate();
        painel.repaint();

        JButton transferir = new JButton("Remover do Baú");
        transferir.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferir.setForeground(Color.decode(Config.COR_TEXTO));
        transferir.setBackground(Color.decode(Config.COR_BOTAO));
        transferir.setFont(Config.FONTE_BOTAO);

        transferir.addActionListener(ev -> {
            Window parentPop = SwingUtilities.getWindowAncestor(painel);

            if (InventarioController.tentarAdicionarItem(item, parentPop)) {

                Bau.removerItem(item);

                atualizarBau();
                atualizarInventario();

                popPadrao.dispose();
            }
        });

        JButton ok = new JButton("Fechar");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.setForeground(Color.decode(Config.COR_TEXTO));
        ok.setBackground(Color.decode(Config.COR_BOTAO));
        ok.setFont(Config.FONTE_BOTAO);

        ok.addActionListener(ev -> popPadrao.dispose());

        painel.add(transferir);
        painel.add(Box.createVerticalStrut(10));
        painel.add(ok);

        popPadrao.add(painel);
        popPadrao.setVisible(true);
    }

    public void criarSlotsFixosInventario() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 4, 2, 4);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        for (int linha = 0; linha < LINHAS_INV; linha++) {
            for (int coluna = 0; coluna < COLUNAS_INV; coluna++) {

                JPanel slotPanel = new JPanel();
                slotPanel.setLayout(new BorderLayout());
                slotPanel.setPreferredSize(new Dimension(LARGURA_SLOT, ALTURA_SLOT));
                slotPanel.setOpaque(false);

                JLabel imagem = new JLabel();
                JButton botao = new JButton();

                botao.setOpaque(false);
                botao.setContentAreaFilled(false);
                botao.setBorderPainted(false);
                botao.setFocusPainted(false);
                botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

                botao.addActionListener(e -> {
                    Itens item = (Itens) ((JButton) e.getSource()).getClientProperty("item");

                    if (item != null) {
                        Window parent = SwingUtilities.getWindowAncestor(gridItensInventario);
                        criaPopupItemGuardar(item, parent);
                    }
                });

                JLabel quantidadeSlot = new JLabel("");
                quantidadeSlot.setOpaque(false);
                quantidadeSlot.setForeground(Color.green);
                quantidadeSlot.setHorizontalAlignment(SwingConstants.RIGHT);
                quantidadeSlot.setVerticalAlignment(SwingConstants.BOTTOM);
                quantidadeSlot.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 5));

                JPanel overlay = new JPanel();
                overlay.setLayout(new OverlayLayout(overlay));
                overlay.setOpaque(false);

                overlay.add(botao);
                overlay.add(quantidadeSlot);
                overlay.add(imagem);
                slotPanel.add(overlay, BorderLayout.CENTER);

                gbc.gridx = coluna;
                gbc.gridy = linha;

                gridItensInventario.add(slotPanel, gbc);

                Point pos = new Point(coluna, linha);
                slotsImagemInv.put(pos, imagem);
                slotsBotaoInv.put(pos, botao);
                slotsQuantidadeInv.put(pos, quantidadeSlot);
            }
        }
        atualizarInventario();
    }

    public void atualizarInventario() {
        for (Point p : slotsImagemInv.keySet()) {
            slotsImagemInv.get(p).setIcon(null);
            slotsBotaoInv.get(p).putClientProperty("item", null);
            JLabel qty = slotsQuantidadeInv.get(p);
            if (qty != null) {
                qty.setText("");
            }
        }

        int index = 0;
        for (Itens item : Inventario.getItens()) {

            int linha = index / COLUNAS_INV;
            int coluna = index % COLUNAS_INV;
            Point pos = new Point(coluna, linha);

            JLabel img = slotsImagemInv.get(pos);
            JButton btn = slotsBotaoInv.get(pos);
            JLabel qty = slotsQuantidadeInv.get(pos);

            if (img != null) {
                ImageIcon icon = new ImageIcon(getClass().getResource(item.getCaminhoImagem()));
                Image scaled = icon.getImage().getScaledInstance(LARGURA_SLOT, ALTURA_SLOT, Image.SCALE_SMOOTH);
                img.setIcon(new ImageIcon(scaled));
            }

            if (btn != null) {
                btn.putClientProperty("item", item);
            }

            if (qty != null && item.getQuantidade() > 1) {
                qty.setText(String.valueOf(item.getQuantidade()));
            } else if (qty != null) {
                qty.setText("");
            }
            index++;
        }

        gridItensInventario.revalidate();
        gridItensInventario.repaint();
    }

    public void criaPopupItemGuardar(Itens item, Window parent) {
        JDialog popPadrao = new JDialog(parent, item.getNome(), Dialog.ModalityType.APPLICATION_MODAL);
        popPadrao.setSize(300, 300);
        popPadrao.setLocationRelativeTo(parent);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        painel.setBackground(Color.decode(Config.COR_FUNDO));

        ImageIcon imagemItem = new ImageIcon(Itens.class.getResource(item.getCaminhoImagem()));
        Image redimensImage = imagemItem.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        imagemItem = new ImageIcon(redimensImage);

        JPanel painelImagem = new JPanel();
        painelImagem.setOpaque(false);
        painelImagem.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JLabel labelItem = new JLabel(imagemItem);
        labelItem.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelImagem.add(labelItem);
        painel.add(painelImagem);

        painel.revalidate();
        painel.repaint();

        JButton guardar = new JButton("Guardar no Baú");
        guardar.setAlignmentX(Component.CENTER_ALIGNMENT);

        guardar.setForeground(Color.decode(Config.COR_TEXTO));
        guardar.setBackground(Color.decode(Config.COR_BOTAO));
        guardar.setFont(Config.FONTE_BOTAO);

        guardar.addActionListener(ev -> {
            InventarioController.removerItem(item);
            Bau.adicionarItem(item);

            atualizarInventario();
            atualizarBau();

            popPadrao.dispose();
        });

        JButton voltar = new JButton("Voltar");
        voltar.setAlignmentX(Component.CENTER_ALIGNMENT);

        voltar.setForeground(Color.decode(Config.COR_TEXTO));
        voltar.setBackground(Color.decode(Config.COR_BOTAO));
        voltar.setFont(Config.FONTE_BOTAO);
        voltar.addActionListener(ev -> popPadrao.dispose());

        painel.add(guardar);
        painel.add(Box.createVerticalStrut(10));
        painel.add(voltar);

        popPadrao.add(painel);
        popPadrao.setVisible(true);
    }

    public static void exibirBau(Window parent) {
        JDialog popBau = new JDialog(parent, "Baú", Dialog.ModalityType.APPLICATION_MODAL);
        popBau.setSize(800, 600);
        popBau.setLocationRelativeTo(parent);

        PainelBau painelBau = new PainelBau();

        BauController controller = painelBau.getBauController();
        controller.atualizarInventario();
        controller.atualizarBau();

        painelBau.atualizarEquipado();
        painelBau.atualizarVida();

        popBau.add(painelBau);
        popBau.setVisible(true);
    }
}