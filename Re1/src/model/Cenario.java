package model;
import javax.swing.*;

import java.awt.*;

// usem essa classe pra cirar os cenarios ja ta tudo configuradinho
// -R

public abstract class Cenario extends JFrame {
    protected JButton portaEsquerda, portaDireita, inventario;
    protected ImageIcon imagemFundo;
    protected JTextArea texto;
    protected JPanel painelPrincipal, painelTexto;

    public Cenario(String titulo) {
        setTitle(titulo);
        getContentPane().setBackground(Color.decode(Config.COR_FUNDO));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setLocationRelativeTo(null);

        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.setOpaque(false);
        painelPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(painelPrincipal, BorderLayout.NORTH);
    }

    protected void mostrarImagem(String caminhoImagem) {
        imagemFundo = new ImageIcon(getClass().getResource(caminhoImagem));
        Image redimensImage = imagemFundo.getImage().getScaledInstance(Config.LARGURA_IMAGEM, Config.ALTURA_IMAGEM,
                Image.SCALE_SMOOTH);
        imagemFundo = new ImageIcon(redimensImage);
        
        JPanel painelImagem = new JPanel();
        painelImagem.setOpaque(false);
        painelImagem.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        JLabel fundo = new JLabel(imagemFundo);
        fundo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        painelImagem.add(fundo);
        painelPrincipal.add(painelImagem);
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

    protected void mostrarTexto(String textoCenario) {
        texto = new JTextArea(textoCenario);
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setOpaque(false);
        texto.setFont(Config.FONTE_PADRAO);
        texto.setForeground(Color.decode(Config.COR_TEXTO));
        texto.setMaximumSize(new Dimension(1000, 80));
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelTexto = new JPanel();
        painelTexto.setLayout(new BoxLayout(painelTexto, BoxLayout.Y_AXIS));
        painelTexto.setOpaque(false);
        painelTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelTexto.add(texto);
        painelPrincipal.add(painelTexto);
    }

    public abstract void configurarBotoes();
}