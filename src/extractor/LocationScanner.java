package extractor;

import net.morbz.osmonaut.IOsmonautReceiver;
import net.morbz.osmonaut.osm.EntityType;
import net.morbz.osmonaut.osm.Tags;
import wwp.OSM;

public abstract class LocationScanner implements IOsmonautReceiver {

    @Override
    public boolean needsEntity(EntityType type, Tags tags) {
        // key, key_value
        return tags.hasKeyValue(OSM.SHOP, OSM.SUPERMARKET)
                || tags.hasKeyValue(OSM.OFFICE, OSM.GOVERNMENT)
                || tags.hasKeyValue(OSM.BUILDING, OSM.CIVIC)
                || tags.hasKeyValue(OSM.BUILDING, OSM.TRANSFORMER_TOWER)
                || tags.hasKeyValue(OSM.AMENITY, OSM.CINEMA)
                || tags.hasKeyValue(OSM.AMENITY, OSM.THEATRE)
                || tags.hasKeyValue(OSM.AMENITY, OSM.RESTAURANT)
                || tags.hasKeyValue(OSM.AMENITY, OSM.CHARGING_STATION);
    }
}
