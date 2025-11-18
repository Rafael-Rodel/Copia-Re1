package view;

import javax.swing.*;
import java.awt.*;

import controller.MapaController;
import controller.InventarioController;
import model.Inventario;
import model.Itens;
import model.Personagem;

// usei o GridBag pra fazer o "Position Layout" do inventario e ele se organizar automatico
// o Grid comum fica mudando o posicionamento sempre q tu add ou retira um item, ruinzão
// -R

public class PainelInventario extends JPanel {
    private static PainelInventario instancia;
    private Image imagemFundo;
    private JPanel gridItens;
    private InventarioController controller;
    private JLabel imagemEquipado;
    private JLabel gifVida;

    public PainelInventario() {
        instancia = this;
        setOpaque(false);
        setLayout(null);

        String caminho = "/resources/imgs/inventario_jill.png";
        if (Personagem.getChris()) {
            caminho = "/resources/imgs/inventario_chris.png";
        }

        imagemFundo = new ImageIcon(getClass().getResource(caminho)).getImage();

        gifVida = new JLabel();
        atualizarVida();
        gifVida.setBounds(174, 388, 165, 100);
        add(gifVida);

        imagemEquipado = new JLabel();
        imagemEquipado.setBounds(395, 400, 90, 70);
        imagemEquipado.setOpaque(false);
        add(imagemEquipado);

        gridItens = new JPanel(new GridBagLayout());
        gridItens.setOpaque(false);

        controller = new InventarioController(gridItens);
        controller.criarSlotsFixos();

        JPanel painelInventario = new JPanel(new GridBagLayout());
        painelInventario.setOpaque(false);
        painelInventario.setBounds(0, 0, 785, 640);
        add(painelInventario);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(13, 487, 0, 0);

        painelInventario.add(gridItens, gbc);

        JPanel painelBotoes = new JPanel(null);
        painelBotoes.setOpaque(false);
        painelBotoes.setBounds(0, 0, 1000, 200);

        JButton fechaInventario = new JButton();
        fechaInventario.setBounds(635, 95, 117, 40);

        fechaInventario.setOpaque(false);
        fechaInventario.setContentAreaFilled(false);
        fechaInventario.setBorderPainted(false);
        fechaInventario.setFocusPainted(false);

        JButton abreMapa = new JButton();
        abreMapa.setBounds(525, 55, 110, 40);

        abreMapa.setOpaque(false);
        abreMapa.setContentAreaFilled(false);
        abreMapa.setBorderPainted(false);
        abreMapa.setFocusPainted(false);

        painelBotoes.add(fechaInventario);
        painelBotoes.add(abreMapa);

        abreMapa.addActionListener(e -> {
            MapaController.exibirMapa(this);
        });

        fechaInventario.addActionListener(e -> {
            JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(this);
            dialog.dispose();
        });

        setLayout(null);
        add(painelBotoes);
    }

    public static PainelInventario getInstancia() {
        return instancia;
    }

    public InventarioController getController() {
        return controller;
    }

    public void atualizarVida() {
        String gifPath = getClass().getResource("/resources/imgs/vida_fine.gif").toExternalForm();

        if (Personagem.getVida() < 10) {
            gifPath = getClass().getResource("/resources/imgs/vida_danger.gif").toExternalForm();
        }

        gifVida.setText("<html><img src='" + gifPath + "' width='165' height='100'></html>");
    }

    public void atualizarEquipado() {
        Itens item = Inventario.getEquipado();

        if (item == null) {
            imagemEquipado.setIcon(null);
            return;
        }

        ImageIcon icon = new ImageIcon(getClass().getResource(item.getCaminhoImagem()));
        Image img = icon.getImage().getScaledInstance(90, 70, Image.SCALE_SMOOTH);

        imagemEquipado.setIcon(new ImageIcon(img));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
    }
}
