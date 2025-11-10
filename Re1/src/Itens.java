import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

// aq tem um pop up pra mostrar quando acha um item 
// -R

public class Itens {
    private String nome;
    private String caminhoImagem;
    private int linha;
    private int coluna;
    private boolean possuido = false;
    private static List<Itens> listaItens = new ArrayList<>();

    public Itens(String nome, String Imagem) {
        this.nome = nome;
        this.caminhoImagem = Imagem;
        listaItens.add(this);
    }

    public String getNome() {
        return nome;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public boolean getPossuido() {
        return this.possuido;
    }

    public void setPossuido(boolean possuido) {
        this.possuido = possuido;
    }

    public static void popupItem(String nomeItem, String textoPop, JFrame parent) {
        String caminhoImagem = null;

        for (Itens i : listaItens) {
            if (i.getNome().equalsIgnoreCase(nomeItem)) {
                caminhoImagem = i.getCaminhoImagem();
                i.setPossuido(true);
                break;
            }
        }
        JDialog popup = new JDialog(parent, "Achou Item", true);
        popup.setSize(800, 600);
        popup.setLocationRelativeTo(parent);

        JPanel painelPop = new JPanel();
        painelPop.setBackground(Color.decode(Config.COR_FUNDO));
        painelPop.setLayout(new BoxLayout(painelPop, BoxLayout.Y_AXIS));
        painelPop.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        ImageIcon imagemItem = new ImageIcon(Itens.class.getResource(caminhoImagem));
        Image redimensImage = imagemItem.getImage().getScaledInstance(500, 400,
                Image.SCALE_SMOOTH);
        imagemItem = new ImageIcon(redimensImage);

        JPanel painelImagem = new JPanel();
        painelImagem.setOpaque(false);
        painelImagem.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JLabel item = new JLabel(imagemItem);
        item.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelImagem.add(item);
        painelPop.add(painelImagem);

        painelPop.revalidate();
        painelPop.repaint();

        JTextArea texto = new JTextArea(textoPop);
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setOpaque(false);
        texto.setFont(Config.FONTE_PADRAO);
        texto.setForeground(Color.decode(Config.COR_TEXTO));

        JButton ok = new JButton("Pegar");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.addActionListener(ev -> {
            popup.dispose();
        });

        ok.setForeground(Color.decode(Config.COR_DESTAQUE));
        ok.setBackground(Color.decode(Config.COR_BOTAO));
        ok.setFont(Config.FONTE_BOTAO);

        painelPop.add(Box.createVerticalStrut(20));
        painelPop.add(texto);
        painelPop.add(Box.createVerticalStrut(20));
        painelPop.add(ok);

        popup.add(painelPop);
        popup.setVisible(true);
    }
}