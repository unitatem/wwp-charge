package solver;

import wwp.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomLocations {

    public HashMap<String, ArrayList<Location>> randomLocations;

    public RandomLocations(Agglomerations agglomerations, LocationsKeeper oldKeeper, int totalLocal4Agglomeration) {

        LocationsKeeper locationsKeeper = new LocationsKeeper();

        for(City city : agglomerations.cities) {
            for (int i = 0; i < (int)city.maxChargers; i++) {
                int randomNumber = new Random().nextInt(totalLocal4Agglomeration);
                locationsKeeper.addLocation(city.name, oldKeeper.agglomeration.get(city).get(randomNumber));
        }
        }
        randomLocations = locationsKeeper.agglomeration;
        System.out.println(randomLocations);
    }
}
//System.out.println(AgglomerationList.getAt(1));