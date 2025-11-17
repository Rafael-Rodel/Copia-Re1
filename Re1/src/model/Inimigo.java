package model;

public class Inimigo {
    private String nome;
    private String caminhoImagem;
    private String textoAproximando;
    private int vidaInimigo;
    private int danoInimigo;
    private int passosParaAlcancar;
    private int velocidade;

    public Inimigo(String nome, String caminhoImagem, String textoAproximando, int vida,int dano, int passosParaAlcancar, int velocidade) {
        this.nome = nome;
        this.caminhoImagem = caminhoImagem;
        this.textoAproximando = textoAproximando;
        this.vidaInimigo = vida;
        this.danoInimigo = dano;
        this.passosParaAlcancar = passosParaAlcancar;
        this.velocidade = velocidade;
    }

    public String getNome() {
        return nome;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public String getTextoAproximando() {
        return textoAproximando;
    }

    public int getPassosParaAlcancar() {
        return passosParaAlcancar;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public int getVidaInimigo() {
        return this.vidaInimigo;
    }

    public void setVidaInimigo(int vidaInimigo) {
        this.vidaInimigo = vidaInimigo;
    }

    public int getDanoInimigo() {
        return this.danoInimigo;
    }

    public void setDanoInimigo(int danoInimigo) {
        this.danoInimigo = danoInimigo;
    }

    public void darDano(int dano) {
        vidaInimigo = vidaInimigo - dano;
    }
}
