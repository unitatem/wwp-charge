package solver;

import java.util.HashMap;

public class Pair<distanceSum, individual> {

    private final distanceSum element0;
    private final individual element1;

    public static <distanceSum, individual> Pair<distanceSum, individual> createPair(distanceSum element0, individual element1){
        return new Pair<distanceSum, individual>(element0, element1);
    }

    public Pair(distanceSum element0, individual element1){
        this.element0 = element0;
        this.element1 = element1;
    }

    public distanceSum getElement0(){
        return element0;
    }

    public individual getElement1(){
        return element1;
    }
}
