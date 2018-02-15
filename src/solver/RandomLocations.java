package solver;

import wwp.AgglomerationList;
import wwp.Location;

import java.util.ArrayList;
import java.util.Random;

public class RandomLocations {

    // losowanie i zwracanie listy randomowych lokalizacji
    //ArrayList<Location> randLocations = new ArrayList();

    public RandomLocations() {
        ArrayList<Location> randLocations = new ArrayList<Location>();
        Random rand = new Random();
        int known = 0; // istniejące stacje
        int maxPopulation = 6000 - known;
        int max = 0; // (dlugosc listy z ktorej losuje-1)
        int randomNumber = -1;
        for(int i = 0; i<maxPopulation; i++) {
            randomNumber = rand.nextInt(max + 1);
            //randLocations[i] =
        }
    }
}
