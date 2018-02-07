
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */
import java.io.File;
import edu.duke.*;
import java.util.*;

public class ClosestQuakes {
    //The next two methods are part of Assignment 4:
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        //This method iterates over the whole list and adds the closest value to our Location to an arraylist once.
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        for(int j=0; j< howMany; j++){
            int minIndex = 0;
            for(int k=1; k < copy.size();k++){
              QuakeEntry quake = copy.get(k);
              Location loc = quake.getLocation();
              System.out.println("loc: " +loc);
              if (loc.distanceTo(current) < 
                  copy.get(minIndex).getLocation().distanceTo(current) ){
                  minIndex = k;
                }
            }
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        //This method is a tester for the last method.
        EarthQuakeParser parser = new EarthQuakeParser();
        
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        
        ArrayList<QuakeEntry> quakesData  = parser.read(source);
        System.out.println("read data for "+quakesData.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(quakesData,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    //End of assignment 4, coded by Abraham Ferrero
}
