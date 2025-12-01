package model;

import java.util.ArrayList;
import java.util.List;

public class Bau {
    private static List<Itens> itensBau = new ArrayList<>(); 

    public static List<Itens> getItens() {
        return itensBau;
    }

    public static void adicionarItem(Itens item) {
        if (!itensBau.contains(item)) {
            if (itensBau.size() < 12) { 
                int index = itensBau.size();
                int linha = index / 2;
                int coluna = index % 2;

                item.setLinha(linha); 
                item.setColuna(coluna);
                itensBau.add(item);
            } else {
                System.out.println("Baú cheio! Não foi possível adicionar o item: " + item.getNome());
            }
        }
    }

    public static void removerItem(Itens item) {
        itensBau.remove(item);
    }
}