package wwp;

import net.morbz.osmonaut.osm.Entity;

public class City {

    public String name;
    public int population;
    public double share;
    public Entity entity;

    public City(String name_, int population_) {
        name = name_;
        population = population_;
    }

    public void setShare(double share_) {
        share = share_;
    }

    public void setEntity(Entity entity_) {
        entity = entity_;
    }
}
