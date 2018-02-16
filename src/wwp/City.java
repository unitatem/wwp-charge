package wwp;

import net.morbz.osmonaut.osm.Entity;

public class City {

    public String name;
    public int population;
    public double maxChargers;
    public Entity entity;

    public City(AgglomerationList name_, int population_) {
        name = name_.getCityName();
        population = population_;
    }

    public void setMaxChargers(double maxChargers_) {
        maxChargers = maxChargers_;
    }

    public void setEntity(Entity entity_) {
        entity = entity_;
    }
}
