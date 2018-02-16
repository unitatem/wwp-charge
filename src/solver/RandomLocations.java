package solver;

import utils.Geo;
import wwp.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomLocations {

    public HashMap<String, ArrayList<Location>> randomLocations;

    public RandomLocations(Agglomerations agglomerations, LocationsKeeper oldKeeper) {

        LocationsKeeper locationsKeeper = new LocationsKeeper();

        for (City city : agglomerations.cities) {
            int numberOfLocationsInCity = oldKeeper.agglomeration.get(city.name).size();
            for (int i = 0; i < (int) city.maxChargers; i += 1) {
                int randomNumber = new Random().nextInt(numberOfLocationsInCity);
                Location possibleLocation = oldKeeper.agglomeration.get(city.name).get(randomNumber);
                boolean isGoodCandidate = true;
                // check if candidate is not closer than MIN_DISTANCE to other chargers
                if (locationsKeeper.agglomeration.size() != 0 && locationsKeeper.agglomeration.get(city.name) != null)
                    for (Location otherLocation : locationsKeeper.agglomeration.get(city.name)) {
                        if (otherLocation != null && Geo.distance(otherLocation, possibleLocation) < Geo.MIN_DISTANE) {
                            isGoodCandidate = false;
                            break;
                        }
                    }

                if (isGoodCandidate)
                    locationsKeeper.addLocation(city.name, possibleLocation);
                // here should be else --i but removing it prevent crash if there is not enough places for chargers
            }
        }
        randomLocations = locationsKeeper.agglomeration;
    }

    void RandomOnes(HashMap hashMap) { //use: RandomOnes(RandomLocations rl.randomLocations)


    }
}
//System.out.println(AgglomerationList.getAt(1));
