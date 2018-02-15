// ref: http://akademia.icm.edu.pl/wp-content/uploads/sites/2/2018/02/Zalacznik-1-WWP_II_v3.pdf

package wwp;

public interface OSM {
    String NAME = "name";
    String ADDRESS_CITY = "addr:city";
    String ADDRESS_STREET = "addr:street";
    String ADDRESS_HOUSENUMBER = "addr:housenumber";

    String BOUNDARY = "boundary";
    String ADMINISTRATIVE = "administrative";

    String ADMIN_LEVEL = "admin_level";
    String ADMIN_LEVEL_CITY = "9";

    String PLACE = "place";
    String CITY = "city";

    String BUILDING = "building";
    String TRANSFORMER_TOWER = "transformer_tower";
    String CIVIC = "civic";

    String SHOP = "shop";
    String SUPERMARKET = "supermarket";

    String OFFICE = "office";
    String GOVERNMENT = "government";

    String AMENITY = "amenity";
    String CHARGING_STATION = "charging_station";
}
