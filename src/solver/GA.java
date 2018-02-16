package solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GA {

    public ArrayList<Pair> pairs = new ArrayList<Pair>();

    public GA(){

    }
    
    public Filling(double distance, HashMap Country){
        Pair<Double, HashMap> pair = Pair.createPair(distance, Country);
        pairs.add(pair);
    }

    void selection(){
        //Arrays.sort(pairs); //sortowanie par
        for(int i=pairs.size(); i>0.5*pairs.size(); i--){
            pairs.remove(Pair )
        }


    }

}
