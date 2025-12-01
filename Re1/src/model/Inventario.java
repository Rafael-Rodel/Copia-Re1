package model;

import java.util.ArrayList;
import java.util.List;

import view.PainelInventario;

public class Inventario {
    private static Itens equipado;
    private static List<Itens> itens = new ArrayList<>();

    public static boolean possui(Itens item) {
        return itens.contains(item);
    }

    public static List<Itens> getItens() {
        return itens;
    }

    public static Itens getEquipado() {
        return equipado;
    }

    public static void setEquipado(Itens novoEquipado) {
        equipado = novoEquipado;

        PainelInventario painel = PainelInventario.getInstancia();
        if (painel != null) {
            painel.atualizarEquipado();
        }
    }
}
