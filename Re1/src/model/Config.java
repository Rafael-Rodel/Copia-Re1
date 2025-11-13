package model;
import java.awt.*;
import javax.swing.*;

// cria os construtores dos itens aqui pra ficar melhor de usar no resto do codigo
// tmb tem um metodo aq pra cria os popup padrão com texto, se n quiser botar imagem só coloca null no construtor
// -R

public class Config {
    public static final int LARGURA_TELA = 1280;
    public static final int ALTURA_TELA = 720;

    public static final int LARGURA_IMAGEM = 1000;
    public static final int ALTURA_IMAGEM = 450;

    public static final String COR_FUNDO = "#110c12";
    public static final String COR_BOTAO = "#4b1b63";
    public static final String COR_TEXTO = "#FFFFFF";
    public static final String COR_DESTAQUE = "#FF0000";

    public static final Font FONTE_BOTAO = new Font("Arial", Font.BOLD, 16);
    public static final Font FONTE_TITULO_BORDA = new Font("Arial", Font.BOLD, 18);
    public static final Font FONTE_PADRAO = new Font("Arial", Font.BOLD, 20);

    // construtor dos Itens:
    public static final Itens PISTOLA = new Itens("pistola", "/resources/imgs/pistola.png");
    public static final Itens CHAVE = new Itens("chave", "/resources/imgs/chave.png");
    public static final Itens FACA = new Itens("faca", "/resources/imgs/faca.png");
    public static final Itens SPRAY = new Itens("spray", "/resources/imgs/spray.png");
    public static final Itens EMBLEMA_DOURADO = new Itens("emblema dourado", "/resources/imgs/emblema dourado.png");

    public static void criaPopupPadrao(String titulo, String caminhoImagem, String textoPop, JFrame parent) {
        JDialog popPadrao = new JDialog(parent, titulo, true);
        popPadrao.setSize(400, 250);
        popPadrao.setLocationRelativeTo(parent);

        JPanel painel = new JPanel();
        painel.setBackground(Color.decode(Config.COR_FUNDO));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        if (caminhoImagem != null) {
            popPadrao.setSize(500, 450);
            popPadrao.setLocationRelativeTo(parent);

            ImageIcon imagem = new ImageIcon(Itens.class.getResource(caminhoImagem));
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

        JTextArea texto = new JTextArea(textoPop);
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setOpaque(false);
        texto.setFont(Config.FONTE_PADRAO);
        texto.setForeground(Color.decode(Config.COR_TEXTO));

        JButton ok = new JButton("Prosseguir");
        ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        ok.addActionListener(ev -> {
            popPadrao.dispose();
        });

        ok.setForeground(Color.decode(Config.COR_DESTAQUE));
        ok.setBackground(Color.decode(Config.COR_BOTAO));
        ok.setFont(Config.FONTE_BOTAO);

        painel.add(Box.createVerticalStrut(20));
        painel.add(texto);
        painel.add(Box.createVerticalStrut(20));
        painel.add(ok);

        popPadrao.add(painel);
        popPadrao.setVisible(true);
    }
}
