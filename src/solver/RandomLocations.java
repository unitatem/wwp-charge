package solver;

import utils.Geo;
import wwp.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomLocations {

    public HashMap<String, ArrayList<Location>> randomLocations;

    public RandomLocations(Agglomerations agglomerations, LocationsKeeper oldKeeper, int totalLocal4Agglomeration) {

        LocationsKeeper locationsKeeper = new LocationsKeeper();

        for(City city : agglomerations.cities) {
            for (int i = 0; i < (int)city.maxChargers; --i) {
                int randomNumber = new Random().nextInt(totalLocal4Agglomeration);
                Location possibleLocation = oldKeeper.agglomeration.get(city).get(randomNumber);
                boolean isGoodCandidate = true;
                // check if candidate is not closer than MIN_DISTANCE to other chargers
                for (Location otherLocations : locationsKeeper.agglomeration.get(city.name)) {
                    if (Geo.distance(otherLocations, possibleLocation) < Geo.MIN_DISTANE) {
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
        System.out.println(randomLocations);
    }

    void RandomOnes(HashMap hashMap){ //use: RandomOnes(RandomLocations rl.randomLocations)

        

    }
}
//System.out.println(AgglomerationList.getAt(1));
