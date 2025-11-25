package model;

import java.awt.*;

public class Config {
        public static Boolean censurado = false;

        public static String imgZumbi = "/resources/imgs/zumbi.png";
        public static String imgCaoZumbi = "/resources/imgs/cao_zumbi.png";
        public static String imgPistola = "/resources/imgs/pistola.png";
        public static String imgFaca = "/resources/imgs/faca.png";
        public static String imgMorte = "/resources/imgs/game_over.jpg";
        public static String imgShotgun = "/resources/imgs/shotgun.png";
        public static String imgPrimeiroZumbi = "/resources/imgs/primeiro_zumbi.png";

        public static String textoZumbi = "Zumbi";
        public static String textoCaoZumbi = "Cão Zumbi";
        public static String textoPistola = "Pistola";
        public static String textoFaca = "Faca";
        public static String textoMorte = "Você foi morto";
        public static String textoShotgun = "Shotgun";

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

        public static Itens pistola, faca, spray, emblemaDourado, emblemaVelho, partitura, chaveEscudo;
        public static Inimigo zumbi, caoZumbi;

        static {
                inicializaItensEInimigos();
        }

        private static void inicializaItensEInimigos() {
                pistola = new Itens(textoPistola, imgPistola,
                                "Uma " + textoPistola
                                                + " semiautomática 9mm. Baixo poder de fogo, mas boa para conter inimigos comuns. Causa 10 de dano.",
                                "arma", 15);
                faca = new Itens(textoFaca, imgFaca,
                                "Um " + textoFaca
                                                + " padrão das Forças Especiais. Tem pouco alcance e causa pouco dano, mas pode salvar em situações críticas. Causa 5 de dano.",
                                "arma", 1);
                spray = new Itens("Spray", "/resources/imgs/spray.png",
                                "Restaura totalmente a saúde. Um item valioso em momentos de perigo.", "consumivel", 1);
                emblemaDourado = new Itens("Emblema dourado", "/resources/imgs/emblema_dourado.png",
                                "Um pesado emblema dourado com entalhes de família. Parece encaixar em algum mecanismo especial.",
                                "chave", 1);
                emblemaVelho = new Itens("Emblema velho", "/resources/imgs/emblema_velho.png",
                                "Um estranho emblema de madeira com entalhes de família. Parece encaixar em algum mecanismo especial.",
                                "chave", 1);
                partitura = new Itens("Partitura", "/resources/imgs/partitura.png",
                                "O titulo diz 'a sonata do luar' de Beethoven.", "chave", 1);
                chaveEscudo = new Itens("Chave escudo", "/resources/imgs/chave_escudo.png",
                                "Uma chave com um escudo entalhado", "chave", 1);

                zumbi = new Inimigo(textoZumbi, imgZumbi,
                                "O " + textoZumbi + " se aproxima lentamente...", 40, 5, 6, 1);
                caoZumbi = new Inimigo(textoCaoZumbi, imgCaoZumbi,
                                "O " + textoCaoZumbi + " avança rapidamente!", 25, 8, 4, 2);
        }

        public static void setCensurado(boolean censura) {
                censurado = censura;

                if (censurado) {
                        imgZumbi = "/resources/imgs/zumbi_censurado.png";
                        imgCaoZumbi = "/resources/imgs/cao_zumbi_censurado.png";
                        imgPistola = "/resources/imgs/pistola_censurada.png";
                        imgFaca = "/resources/imgs/faca_censurada.png";
                        imgMorte = "/resources/imgs/game_over_censurado.jpg";
                        imgShotgun = "/resources/imgs/shotgun_censurada.png";
                        imgPrimeiroZumbi = "/resources/imgs/zumbi_censurado.png";
                        textoZumbi = "Ursinho";
                        textoCaoZumbi = "Cão fofinho";
                        textoPistola = "Pistola d'agua";
                        textoFaca = "Pau";
                        textoMorte = "Você foi abraçado";
                        textoShotgun = "Arma de dardo";
                } else {
                        imgZumbi = "/resources/imgs/zumbi.png";
                        imgCaoZumbi = "/resources/imgs/cao_zumbi.png";
                        imgPistola = "/resources/imgs/pistola.png";
                        imgFaca = "/resources/imgs/faca.png";
                        imgMorte = "/resources/imgs/game_over.jpg";
                        imgShotgun = "/resources/imgs/shotgun.png";
                        imgPrimeiroZumbi = "/resources/imgs/primeiro_zumbi.png";
                        textoZumbi = "Zumbi";
                        textoCaoZumbi = "Cão Zumbi";
                        textoPistola = "Pistola";
                        textoFaca = "Faca";
                        textoMorte = "Você foi morto";
                        textoShotgun = "Shotgun";
                }

                inicializaItensEInimigos();
        }
}
