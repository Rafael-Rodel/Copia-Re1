import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

// executa por aq pra rodar o jogo todo
// -R

public class Re1 extends JFrame {

    private JButton Jill, Chris, Sair;

    public Re1() {
        new PainelInventario();

        Inventario.adicionarItem(Config.FACA);
        Inventario.adicionarItem(Config.SPRAY);

        setTitle("Copia Resident Evil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Config.LARGURA_TELA, Config.ALTURA_TELA);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.decode(Config.COR_FUNDO));

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setOpaque(false);
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ImageIcon imagem = new ImageIcon(getClass().getResource("/imgs/capa.png"));
        Image redimensImage = imagem.getImage().getScaledInstance(Config.LARGURA_IMAGEM, Config.ALTURA_IMAGEM,
                Image.SCALE_SMOOTH);
        imagem = new ImageIcon(redimensImage);

        JLabel labelComImagem = new JLabel(imagem);
        labelComImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(labelComImagem, BorderLayout.CENTER);
 
        JPanel nav = new JPanel();

        JPanel painelRotas = new JPanel();
        painelRotas.setPreferredSize(new Dimension(160, 80));
        painelRotas.setMaximumSize(new Dimension(160, 80));
        painelRotas.setLayout(new FlowLayout(FlowLayout.LEFT));
        TitledBorder borda = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode(Config.COR_DESTAQUE), 3),
                "Rotas",
                TitledBorder.CENTER,
                TitledBorder.TOP);
        borda.setTitleColor(Color.decode(Config.COR_DESTAQUE));
        borda.setTitleFont(Config.FONTE_TITULO_BORDA);
        painelRotas.setBorder(borda);

        Jill = new JButton("Jill");
        Chris = new JButton("Chris");

        Personagem.iniciarPersonagem();

        Jill.addActionListener(e -> {
            Personagem.setJill(true);
            popHistoria(this);
            Inventario.adicionarItem(Config.PISTOLA);
        });

        Chris.addActionListener(e -> {
            Personagem.setChris(true);
            popHistoria(this);
        });

        Jill.setForeground(Color.decode(Config.COR_TEXTO));
        Jill.setBackground(Color.decode(Config.COR_BOTAO));
        Jill.setFont(Config.FONTE_BOTAO);
        Chris.setForeground(Color.decode(Config.COR_TEXTO));
        Chris.setBackground(Color.decode(Config.COR_BOTAO));
        Chris.setFont(Config.FONTE_BOTAO);

        painelRotas.add(Jill);
        painelRotas.add(Chris);
        painelRotas.setOpaque(false);

        Sair = new JButton("Sair");

        Sair.setForeground(Color.decode(Config.COR_DESTAQUE));
        Sair.setBackground(Color.decode(Config.COR_BOTAO));
        Sair.setFont(Config.FONTE_BOTAO);
        Sair.addActionListener(e -> System.exit(0));

        nav.setOpaque(false);
        nav.add(painelRotas);
        nav.add(Box.createHorizontalStrut(150));
        nav.add(Sair);

        painelPrincipal.add(nav);

        JTextArea explicacaoRotas = new JTextArea(
                "Jill possui itens iniciais melhores e encontra melhores itens pelo caminho\n" +
                        "Chris recebe menos itens durante sua jornada.");
        explicacaoRotas.setOpaque(false);
        explicacaoRotas.setFont(Config.FONTE_PADRAO);
        explicacaoRotas.setForeground(Color.decode(Config.COR_TEXTO));
        explicacaoRotas.setEditable(false);
        explicacaoRotas.setFocusable(false);

        JPanel PainelTexto = new JPanel(new FlowLayout(FlowLayout.CENTER));
        PainelTexto.setOpaque(false);
        PainelTexto.add(explicacaoRotas);

        painelPrincipal.add(PainelTexto);

        add(painelPrincipal);
        setVisible(true);
    }

    public void popHistoria(JFrame parent) {
        JDialog popup = new JDialog(parent, "História", true);
        popup.setSize(800, 600);
        popup.setLocationRelativeTo(parent);

        JPanel painel = new JPanel();
        painel.setBackground(Color.decode(Config.COR_FUNDO));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JTextArea texto = new JTextArea(
                "A história começa em julho de 1998, nas montanhas Arklay, nos arredores da cidade de Raccoon City, onde uma série de assassinatos brutais está acontecendo — as vítimas foram parcialmente devoradas.\n"
                        +
                        "\nA S.T.A.R.S., unidade de elite da polícia local, envia o Bravo Team para investigar, mas o contato com eles é perdido. Em seguida, o Alpha Team - formado por Chris Redfield, Jill Valentine, Barry Burton, Albert Wesker e outros - é enviado para procurá-los.\n"
                        +
                        "\nDurante a busca, o grupo encontra o helicóptero do Bravo Team abandonado e o piloto morto. Logo depois, são atacados por cães monstruosos. Em meio ao pânico, o piloto do Alpha Team, Brad Vickers, foge com o helicóptero, deixando o resto da equipe para trás.\n"
                        +
                        "\nO grupo corre para escapar dos cães e se refugia em uma mansão misteriosa próxima ...");
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
            popup.dispose();
            parent.dispose();
            new HallEntrada();
        });

        ok.setForeground(Color.decode(Config.COR_DESTAQUE));
        ok.setBackground(Color.decode(Config.COR_BOTAO));
        ok.setFont(Config.FONTE_BOTAO);

        painel.add(Box.createVerticalStrut(20));
        painel.add(texto);
        painel.add(Box.createVerticalStrut(20));
        painel.add(ok);

        popup.add(painel);
        popup.setVisible(true);
    }

    public static void main(String[] args) {
        new Re1();
    }
}