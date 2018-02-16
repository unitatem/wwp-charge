package utils;

import net.morbz.osmonaut.osm.LatLon;
import wwp.AgglomerationList;
import wwp.Agglomerations;
import wwp.City;
import wwp.Location;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static HashMap<String, LatLon> centerOfMass(HashMap<String, ArrayList<Location>> country) {
        HashMap<String, LatLon> output = new HashMap<>();
        for (AgglomerationList city : AgglomerationList.values()) {
            ArrayList<Location> locationArrayList = country.get(city.getCityName());
            int length = locationArrayList.size();
            double lat = 0.0;
            double lon = 0.0;
            for (Location location : locationArrayList) {
                LatLon latLon = location.entity.getCenter();
                lat += latLon.getLat();
                lon += latLon.getLon();
            }
            output.put(city.getCityName(), new LatLon(lat / length, lon / length));
        }
        return output;
    }

    public static double minCityDistanceHeuristic(Agglomerations agglomerations, HashMap<String, ArrayList<Location>> country) {
        double distance = 0.0;
        for (int i = 0; i < AgglomerationList.length - 1; ++i) {
            for (int j = i + 1; j < AgglomerationList.length; ++j) {

            }
        }
        return 0.0;
    }
}
