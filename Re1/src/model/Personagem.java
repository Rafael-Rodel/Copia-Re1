package model;
public class Personagem {
    private static boolean Jill = false;
    private static boolean Chris = false;

    // N sei se essa classe é necessaria, se pa da pra colocar isso na Config
    // -R

    public static void iniciarPersonagem() {
        Config.FACA.setPossuido(true);
        Config.SPRAY.setPossuido(true);
    }

    public static boolean getJill() {
        return Jill;
    }

    public static void setJill(boolean valor) {
        Jill = valor;
    }

    public static boolean getChris() {
        return Chris;
    }

    public static void setChris(boolean valor) {
        Chris = valor;
    }
}
