package view;

import javax.swing.*;
import java.awt.*;

import controller.MapaController;
import controller.BauController; 
import model.Inventario;
import model.Itens;
import model.Personagem;

public class PainelBau extends JPanel {
    private static PainelBau instancia;
    private Image imagemFundo;
    
    private JPanel gridItensInventario; 
    private JPanel gridItensBau; 
    
    private BauController bauController; 
    
    private JLabel imagemEquipado;
    private JLabel gifVida;

    public PainelBau() {
        instancia = this;
        setOpaque(false);
        setLayout(null);

        String caminho = "/resources/imgs/bau_jill.png";
        if (Personagem.getChris()) {
            caminho = "/resources/imgs/bau_chris.png";
        }

        imagemFundo = new ImageIcon(getClass().getResource(caminho)).getImage(); 

        gifVida = new JLabel();
        atualizarVida();
        gifVida.setBounds(175, 388, 165, 95);
        add(gifVida);

        imagemEquipado = new JLabel();
        imagemEquipado.setBounds(395, 400, 90, 70);
        imagemEquipado.setOpaque(false);
        add(imagemEquipado);

        
        gridItensInventario = new JPanel(new GridBagLayout());
        gridItensInventario.setOpaque(false);

        gridItensBau = new JPanel(new GridBagLayout());
        gridItensBau.setOpaque(false);

        bauController = new BauController(gridItensBau, gridItensInventario);
        
        bauController.criarSlotsFixos(); 
        bauController.criarSlotsFixosInventario(); 
        JPanel painelContainer = new JPanel(new GridBagLayout());
        painelContainer.setOpaque(false);
        painelContainer.setBounds(0, 0, 785, 640); 
        add(painelContainer);

        GridBagConstraints gbcInventario = new GridBagConstraints();
        gbcInventario.gridx = 0;
        gbcInventario.gridy = 0;
        gbcInventario.anchor = GridBagConstraints.NORTHWEST;
        gbcInventario.insets = new Insets(13, 487, 0, 0); 
        
        painelContainer.add(gridItensInventario, gbcInventario);

        GridBagConstraints gbcBau = new GridBagConstraints();
        gbcBau.gridx = 0;
        gbcBau.gridy = 0;
        gbcBau.anchor = GridBagConstraints.NORTHWEST;
        gbcBau.insets = new Insets(-50, 17, 0, 0); 

        painelContainer.add(gridItensBau, gbcBau);

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

    public static PainelBau getInstancia() {
        return instancia;
    }

    public BauController getBauController() {
        return bauController;
    }

    public void atualizarVida() {
        String gifPath = getClass().getResource("/resources/imgs/vida_fine.gif").toExternalForm();

        if (Personagem.getVida() <= 10) {
            gifPath = getClass().getResource("/resources/imgs/vida_caution.gif").toExternalForm();
        } 
        if (Personagem.getVida() <= 5) {
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