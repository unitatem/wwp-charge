/*
ref: https://pl.wikipedia.org/wiki/Aglomeracje_w_Polsce
ref: https://pl.wikipedia.org/wiki/Bydgoszcz
ref: https://pl.wikipedia.org/wiki/Toru%C5%84
*/

package wwp;

public enum AgglomerationList {
    KATOWICE("Katowice"),
    WARSAW("Warszawa"),
    CRACOW("Kraków"),
    LODZ("Łódź"),
    TROJMIASTO("Trójmiasto"),   // TODO https://pl.wikipedia.org/wiki/Tr%C3%B3jmiasto
    POZNAN("Poznań"),
    WROCLAW("Wrocław"),
    BYDGOSZCZ("Bydgoszcz"),
    TORUN("Toruń"),
    SZCZECIN("Szczecin"),
    BIELSKO_BIALA("Bielsko-Biała"),
    RYBNIK("Rybnik"),
    LUBLIN("Lublin"),
    BIALYSTOK("Białystok"),
    CZESTOCHOWA("Częstochowa"),
    KIELCE("Kielce"),
    RZESZOW("Rzeszów"),
    RADOM("Radom"),
    OPOLE("Opole"),
    TARNOW("Tarnów"),
    WALBRZYCH("Wałbrzych"),
    OLSZTYN("Olsztyn"),
    PLOCK("Płock"),
    ZIELONA_GORA("Zielona Góra"),
    GORZOW("Gorzów Wielkopolski"),
    KOSZALIN("Koszalin"),
    KONIN("Konin"),
    KALISZ("Kalisz"),
    NOWY_SACZ("Nowy Sącz"),
    NOPE("nope");

    private final String name;

    AgglomerationList(String name_) {
        name = name_;
    }

    public String getName() {
        return name;
    }
}
