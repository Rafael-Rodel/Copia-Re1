package controller;

import model.Inventario;
import model.Itens;
import model.Personagem;
import view.Corredor1AndarOeste;
import view.HallEntrada;
import view.PainelInventario;
import view.SalaJantar1;
import view.SalaJantar2;
import view.TelaInicial;

import javax.swing.JFrame;

import model.Config;

public class JogoController {

    private static boolean viuZumbi = false;

    public void iniciarJogo() {
        new PainelInventario();

        Personagem.iniciarPersonagem();

        new TelaInicial();
    }

    public static void iniciaJill() {
        Personagem.setJill(true);
        Inventario.adicionarItem(Config.PISTOLA);
        Inventario.adicionarItem(Config.FACA);
        Inventario.adicionarItem(Config.SPRAY);
    }

    public static void iniciaChris() {
        Personagem.setChris(true);
        Inventario.adicionarItem(Config.FACA);
        Inventario.adicionarItem(Config.SPRAY);
    }

    public static void trocaCenario(JFrame parent, String nomeCenario) {
        parent.dispose();

        switch (nomeCenario) {
            case "HallEntrada":
                new HallEntrada();
                break;
            case "SalaJantar1":
                new SalaJantar1();
                break;
            case "SalaJantar2":
                new SalaJantar2();
                break;
            case "Corredor1AndarOeste":
                new Corredor1AndarOeste();
                break;
        }
    }

    public static void verificarEventosHall(JFrame tela) {
        if (Personagem.getChris() && viuZumbi) {
            Itens.popupItem("pistola", "Você vê uma pistola no chão!", tela);
            Inventario.adicionarItem(Config.PISTOLA);
        }
    }

    public static void pegarBrasao(JFrame parent) {
        if (Inventario.possui(Config.EMBLEMA_DOURADO)) {
            Config.criaPopupPadrao("Lareira", "/resources/imgs/lareira vazia.png", "O brasão foi removido...", parent);
        } else {
            Config.criaPopupPadrao("Lareira", "/resources/imgs/emblema na parede.png", "O brasão parece removivel...", parent);
            Itens.popupItem("emblema dourado", "Você pegou um emblema dourado com um brasão de familia...", parent);
            Inventario.adicionarItem(Config.EMBLEMA_DOURADO);
        }
    }
}
