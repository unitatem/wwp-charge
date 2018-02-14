import net.morbz.osmonaut.IOsmonautReceiver;
import net.morbz.osmonaut.Osmonaut;
import net.morbz.osmonaut.osm.Entity;
import net.morbz.osmonaut.osm.EntityType;
import net.morbz.osmonaut.osm.Tags;

import java.util.Objects;

public class AgglomerationsExtractor {

    private Osmonaut naut;
    private Agglomerations agglomerations;

    AgglomerationsExtractor(Osmonaut naut_, Agglomerations agglomerations_) {
        naut = naut_;
        agglomerations = agglomerations_;

        doScan();
        checkAfterScan();
    }

    private void doScan() {
        naut.scan(new IOsmonautReceiver() {
            @Override
            public boolean needsEntity(EntityType type, Tags tags) {
                // key, key_value
                return (tags.hasKeyValue(OSM.PLACE, OSM.CITY) && tags.hasKey(OSM.NAME));
            }

            @Override
            public void foundEntity(Entity entity) {
                String name = entity.getTags().get(OSM.NAME);
                for (City city : agglomerations.cities)
                    if (Objects.equals(name, city.name)) {
                        city.setEntity(entity);
                        System.out.println("AgglomerationsExtractor => Found: " + name);
                    }
            }
        });
    }

    private void checkAfterScan() {
        for (City city : agglomerations.cities) {
            if (city.entity == null)
                System.out.println("ERROR AgglomerationsExtractor => Not found: " + city.name);
        }
    }
}
