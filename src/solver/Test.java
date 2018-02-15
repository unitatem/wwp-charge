package solver;

import wwp.AgglomerationList;
import wwp.Location;
import wwp.LocationsKeeper;

import java.util.ArrayList;

public class Test {

    public Test(LocationsKeeper lk){
        ArrayList<Location> array =  lk.agglomeration.get(AgglomerationList.WARSAW.getCityName());
        for (Location loc : array)
            System.out.println(loc.address);
    }
}


