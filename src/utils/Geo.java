package utils;

import net.morbz.osmonaut.osm.LatLon;
import wwp.Location;

public class Geo {

    public static double MIN_DISTANE = 250.0;

    private static double PI_2 = 0.5 * Math.PI;
    private static double rad2Meters = 111100.0 * Math.PI / 180.0;

    // ref: https://pl.wikibooks.org/wiki/Astronomiczne_podstawy_geografii/Odleg%C5%82o%C5%9Bci
    private static double distance(LatLon latLon1, LatLon latLon2) {
        double AP = PI_2 - latLon1.getLat();
        double BP = PI_2 - latLon2.getLat();
        double P = latLon1.getLon() - latLon2.getLon();

        double diffRad = Math.acos(Math.cos(AP)*Math.cos(BP) + Math.sin(AP)*Math.sin(BP)*Math.cos(P));
        return diffRad * rad2Meters;
    }

    public static double distance(Location loc1, Location loc2) {
        return distance(loc1.entity.getCenter(), loc2.entity.getCenter());
    }
}
