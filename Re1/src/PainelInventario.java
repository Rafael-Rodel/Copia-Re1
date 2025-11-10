import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

// apenas deus e eu sabiam como eu fiz esse inventario, atualmente só deus sabe
// mentira foi complicado mas funciona, usei o GridBag pra fazer o "Position Layout" do inventario e ele se organizar automatico
// o Grid comum fica mudando o posicionamento sempre q tu add ou retira um item, ruinzão
// -R

public class PainelInventario extends JPanel {
    private static PainelInventario instancia;
    private Image imagemFundo;
    private JPanel gridItens;
    private final int LINHAS = 3;
    private final int COLUNAS = 2;
    private final int LARGURA_SLOT = 90;
    private final int ALTURA_SLOT = 70;

    private HashMap<Point, JLabel> slots = new HashMap<>();

    public PainelInventario() {
        instancia = this;
        setLayout(new GridBagLayout());
        setOpaque(false);

        if (Personagem.getJill()) {
            imagemFundo = new ImageIcon(getClass().getResource("/imgs/inventario jill.png")).getImage();
        } else if (Personagem.getChris()) {
            imagemFundo = new ImageIcon(getClass().getResource("/imgs/inventario chris.png")).getImage();
        } else {
            imagemFundo = new ImageIcon(getClass().getResource("/imgs/inventario jill.png")).getImage();
        }

        gridItens = new JPanel(new GridBagLayout());
        gridItens.setOpaque(false);

        criarSlotsFixos();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(13, 487, 0, 0);
        add(gridItens, gbc);
    }

    private void criarSlotsFixos() {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
    }

    public static void exibirInventario(JFrame parent) {
        JDialog popInventario = new JDialog(parent, "Inventário", true);
        popInventario.setSize(800, 600);
        popInventario.setLocationRelativeTo(parent);

        PainelInventario inventario = new PainelInventario();
        inventario.atualizarInventario();
        popInventario.add(inventario);
        popInventario.setVisible(true);
    }

    public static PainelInventario getInstancia() {
        return instancia;
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
