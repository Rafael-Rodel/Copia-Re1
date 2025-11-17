package model;

import java.util.ArrayList;
import java.util.List;

import view.PainelInventario;

// usa o metodo que ta aq pra add os itens no inventario a outra classe é só pra parte visual funcionar
// -R

public class Inventario {
    private static Itens equipado;
    private static List<Itens> itens = new ArrayList<>();

    public static void adicionarItem(Itens item) {
        if (!itens.contains(item)) {
            int index = itens.size();
            int linha = index / 2;
            int coluna = index % 2;

            item.setLinha(linha);
            item.setColuna(coluna);
            itens.add(item);

            PainelInventario painel = PainelInventario.getInstancia();
            if (painel != null) {
                painel.getController().atualizarInventario();
            }
        }
    }

    public static void removerItem(Itens item) {
        itens.remove(item);
    }

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
