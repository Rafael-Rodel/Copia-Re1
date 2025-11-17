package model;
public class Personagem {
    private static boolean Jill = false;
    private static boolean Chris = false;
    private static int vida;

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

    public static int getVida() {
		return vida;
	}

    public static void setVida(int vlrVida) {
		vida = vlrVida;
	}

    public static void levarDano(int dano) {
        vida = vida - dano;
    }
}
