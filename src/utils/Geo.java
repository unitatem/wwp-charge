package utils;

import com.sun.javafx.geom.Vec2d;
import net.morbz.osmonaut.osm.LatLon;
import wwp.AgglomerationList;
import wwp.Agglomerations;
import wwp.City;
import wwp.Location;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.min;

public class Geo {

    public static double MIN_DISTANE = 250.0;

    private static double PI_2 = 0.5 * Math.PI;
    private static double rad2deg2Meters = 111100.0 * 180.0 / Math.PI;
    private static double deg2rad = Math.PI / 180.0;

    // ref: https://pl.wikibooks.org/wiki/Astronomiczne_podstawy_geografii/Odleg%C5%82o%C5%9Bci
    public static double distance(LatLon latLon1, LatLon latLon2) {
        double AP = (90.0 - latLon1.getLat()) * deg2rad;
        double BP = (90.0 - latLon2.getLat()) * deg2rad;
        double P = (latLon1.getLon() - latLon2.getLon()) * deg2rad;

        double diffRad = Math.acos(Math.cos(AP)*Math.cos(BP) + Math.sin(AP)*Math.sin(BP)*Math.cos(P));
        return diffRad * rad2deg2Meters;
    }

    public static double distance(Location loc1, Location loc2) {
        return distance(loc1.entity.getCenter(), loc2.entity.getCenter());
    }

    public static LatLon centerOfMass(ArrayList<Location> agglomeration) {
            double lat = 0.0;
            double lon = 0.0;
            for (Location location : agglomeration) {
                LatLon latLon = location.entity.getCenter();
                lat += latLon.getLat();
                lon += latLon.getLon();
            }
            int length = agglomeration.size();
            return new LatLon(lat / length, lon / length);
    }

    public static double centerOfMassTotal(Agglomerations agglomerations, HashMap<String, ArrayList<Location>> country) {
        HashMap<String, LatLon> center = new HashMap<>();
        for (AgglomerationList city : AgglomerationList.values()) {
            ArrayList<Location> locationArrayList = country.get(city.getCityName());
            if (locationArrayList == null)
                continue;
            center.put(city.getCityName(), centerOfMass(locationArrayList));
        }

        double totalDistance = 0.0;
        for (City city : agglomerations.cities) {
            LatLon latLon = center.get(city.name);
            if (latLon == null)
                continue;
            totalDistance = distance(city.entity.getCenter(), latLon);
        }
        return totalDistance;
    }

    public static double minCityDistance(HashMap<String, ArrayList<Location>> country) {
        double distance = 0.0;
        for (int i = 0; i < AgglomerationList.length - 1; ++i) {
            String name1 = AgglomerationList.getAt(i).getCityName();
            for (int j = i + 1; j < AgglomerationList.length; ++j) {
                String name2 = AgglomerationList.getAt(j).getCityName();

                double minDistance = Double.MAX_VALUE;
                for (Location location1 : country.get(name1)) {
                    for (Location location2 : country.get(name2)) {
                        double dist = distance(location1.entity.getCenter(), location2.entity.getCenter());
                        minDistance = min(minDistance, dist);
                    }
                }
                distance += minDistance;
            }
        }

        return distance;
    }

    public static double minCityDistanceHeuristic(Agglomerations agglomerations, HashMap<String, ArrayList<Location>> country) {
        double distance = 0.0;
        for (int i = 0; i < AgglomerationList.length - 1; ++i) {
            for (int j = i + 1; j < AgglomerationList.length; ++j) {
                City city1 = agglomerations.cities.get(i);
                City city2 = agglomerations.cities.get(j);
                if (country.get(city1.name) == null || country.get(city2.name) == null)
                    continue;
                LatLon ptx1 = findBestPoint(city1, city2, country);
                LatLon ptx2 = findBestPoint(city2, city1, country);
                distance += distance(ptx1, ptx2);
            }
        }
        return distance;
    }

    private static LatLon findBestPoint(City city1, City city2, HashMap<String, ArrayList<Location>> country) {
        LatLon latLon1 = city1.entity.getCenter();
        LatLon latLon2 = city2.entity.getCenter();
        Vec2d vectFrom1To2 = calculateVector(latLon1, latLon2);
        LatLon bestPoint = country.get(city1.name).get(0).entity.getCenter();
        for (int k = 1; k < country.get(city1.name).size(); ++k) {
            LatLon testPoint = country.get(city1.name).get(k).entity.getCenter();
            Vec2d testVect = new Vec2d(testPoint.getLat() - bestPoint.getLat(), testPoint.getLon() - bestPoint.getLon());
            double dot = vectFrom1To2.x * testVect.x + vectFrom1To2.y * testVect.y;
            if (dot > 0.0) {
                bestPoint = testPoint;
            }
        }
        return bestPoint;
    }

    private static Vec2d calculateVector(LatLon begin, LatLon end) {
        double diffNS = end.getLat() - begin.getLat();
        double diffWE = end.getLon() - begin.getLon();
        return new Vec2d(diffNS, diffWE);
    }
}
