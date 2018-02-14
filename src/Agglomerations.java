import java.util.ArrayList;

public class Agglomerations {

    public ArrayList<City> cities;
    public int totalPopulation;

    Agglomerations() {
        setCities();

        totalPopulation = 0;
        for(City city : cities)
            totalPopulation += city.population;

        for(City city : cities)
            city.setShare((double)city.population / totalPopulation);
    }

    private void setCities() {
        cities.add(new City(AgglomerationList.KATOWICE, 2710397));
        cities.add(new City(AgglomerationList.WARSAW, 2660406));
        cities.add(new City(AgglomerationList.CRACOW, 1264322));
        cities.add(new City(AgglomerationList.LODZ, 1163516));
        cities.add(new City(AgglomerationList.TROJMIASTO, 1105203));
        cities.add(new City(AgglomerationList.POZNAN, 1018511));
        cities.add(new City(AgglomerationList.WROCLAW, 1031439));
        cities.add(new City(AgglomerationList.BYDGOSZCZ, 354990));
        cities.add(new City(AgglomerationList.TORUN, 202591));
        cities.add(new City(AgglomerationList.SZCZECIN, 777806));
        cities.add(new City(AgglomerationList.BIELSKO_BIALA, 584000));
        cities.add(new City(AgglomerationList.RYBNIK, 525000 ));
        cities.add(new City(AgglomerationList.LUBLIN, 652642));
        cities.add(new City(AgglomerationList.BIALYSTOK, 523958));
        cities.add(new City(AgglomerationList.CZESTOCHOWA, 466735));
        cities.add(new City(AgglomerationList.KIELCE, 406922));
        cities.add(new City(AgglomerationList.RZESZOW, 331465));
        cities.add(new City(AgglomerationList.RADOM, 371995));
        cities.add(new City(AgglomerationList.OPOLE, 263934));
        cities.add(new City(AgglomerationList.TARNOW, 269000));
        cities.add(new City(AgglomerationList.WALBRZYCH, 248000));
        cities.add(new City(AgglomerationList.OLSZTYN, 286290));
        cities.add(new City(AgglomerationList.PLOCK, 233696));
        cities.add(new City(AgglomerationList.ZIELONA_GORA, 207451));
        cities.add(new City(AgglomerationList.GORZOW, 190251));
        cities.add(new City(AgglomerationList.KOSZALIN, 171469));
        cities.add(new City(AgglomerationList.KONIN, 143305));
        cities.add(new City(AgglomerationList.KALISZ, 409307));
        cities.add(new City(AgglomerationList.NOWY_SACZ, 158620));
    }
}
