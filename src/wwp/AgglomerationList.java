/*
ref: https://pl.wikipedia.org/wiki/Aglomeracje_w_Polsce
ref: https://pl.wikipedia.org/wiki/Bydgoszcz
ref: https://pl.wikipedia.org/wiki/Toru%C5%84
*/

package wwp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum AgglomerationList {
    KATOWICE("Katowice", "katowice"),
    WARSAW("Warszawa", "warszawa"),
    CRACOW("Kraków", "krakow"),
    LODZ("Łódź", "lodz"),
    TROJMIASTO("Trójmiasto", "trojmiasto"),   // TODO https://pl.wikipedia.org/wiki/Tr%C3%B3jmiasto
    POZNAN("Poznań", "poznan"),
    WROCLAW("Wrocław", "wroclaw"),
    BYDGOSZCZ("Bydgoszcz", "bydgoszcz"),
    TORUN("Toruń", "torun"),
    SZCZECIN("Szczecin", "szczecin"),
    BIELSKO_BIALA("Bielsko-Biała", "bielsko_biala"),
    RYBNIK("Rybnik", "rybnik"),
    LUBLIN("Lublin", "lublin"),
    BIALYSTOK("Białystok", "bialystok"),
    CZESTOCHOWA("Częstochowa", "czestochowa"),
    KIELCE("Kielce", "kielce"),
    RZESZOW("Rzeszów", "rzeszow"),
    RADOM("Radom", "radom"),
    OPOLE("Opole", "opole"),
    TARNOW("Tarnów", "tarnow"),
    WALBRZYCH("Wałbrzych", "walbrzych"),
    OLSZTYN("Olsztyn", "olsztyn"),
    PLOCK("Płock", "plock"),
    ZIELONA_GORA("Zielona Góra", "zielona_gora"),
    GORZOW("Gorzów Wielkopolski", "gorzow_wielkopolski"),
    KOSZALIN("Koszalin", "koszalin"),
    KONIN("Konin", "konin"),
    KALISZ("Kalisz", "kalisz"),
    NOWY_SACZ("Nowy Sącz", "nowy_sacz");

    private final String cityName;
    private final String fileName;

    AgglomerationList(String cityName_, String fileName_) {
        cityName = cityName_;
        fileName = fileName_;
    }

    public static final int length = AgglomerationList.values().length;

    private static final List<AgglomerationList> valuesList =
            Collections.unmodifiableList(Arrays.asList(values()));

    public static AgglomerationList getAt(int idx)  {
        return valuesList.get(idx);
    }

    public String getCityName() {
        return cityName;
    }

    public String getFileName() {
        return fileName;
    }
}
