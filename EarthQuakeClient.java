import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TO DO:
        for(QuakeEntry qe: quakeData){
            if (qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        //done by Abraham Ferrero
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
         for(QuakeEntry qe: quakeData){
             if (qe.getLocation().distanceTo(from) < distMax){
                 answer.add(qe);
             }
         }
        return answer;
        //done by Abraham Ferrero
    }
    //This is an assignment 2 method created by Abraham Ferrero
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
    double minDepth, double maxDepth){
        /*In this method, we create a new arraylist, add every QuakeEntry with a depth
         * between the max and min given by the parameter, and add it to this new arraylist.
         * Then, we return it for later use. 
         */
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
   
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }
    /*In this method, we will apply our filterByMagnitude method and filter them by magnitude 5.0
     * 
     */
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        System.out.println("Filtering by Magnitude:");
        for(QuakeEntry qe : listBig){
            System.out.println(qe);
        }
        //Done by Abraham Ferrero
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        
        // TODO
        /*In the exercise, the example is wrong, as the distance is not given to us by kilometres
         * but by metres,so we need to add 3 zeros to our parameter:
         */
        ArrayList<QuakeEntry> listdis = filterByDistanceFrom(list,1000000,city);
        System.out.println("Filtering by distance:");
        for(QuakeEntry qe : listdis){
            System.out.println(qe.getLocation().distanceTo(city)+ " " + qe.getInfo());
        }
        //Done by Abraham Ferrero
    }
    
    //This method is part of Assignment 2 requirements:
    public void quakesOfDepth(){
       EarthQuakeParser parser = new EarthQuakeParser();
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);
       System.out.println("read data for "+list.size()+" quakes");
       ArrayList<QuakeEntry> depthList = filterByDepth(list,-4000.0,-2000.0);
       int count = 0;
       for(QuakeEntry qe : depthList){
            System.out.println(qe);
            count++;
        }
        System.out.println("Found "+ count + " quakes that match that criteria");
       
    }
    
    //The next 2 methods are part of assignment 3:
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        /*This method checks the quake info for a given phrase, and if it is at the start, end, or any,
         * depending on the parameter, adds the quake to the Arraylist "answer" and returns it.
         */
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(where.equals("start")){
                if (qe.getInfo().startsWith(phrase)){
                    answer.add(qe);
                }
            }
            if(where.equals("end")){
                if (qe.getInfo().endsWith(phrase)){
                    answer.add(qe);
                }
            }
            if(where.equals("any")){
                if (qe.getInfo().contains(phrase)){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }
    
    public void quakesByPhrase(){
        //Test method for the last method. 
        EarthQuakeParser parser = new EarthQuakeParser();
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);
       String where = "any";
       String phrase = "Can";
       System.out.println("read data for "+list.size()+" quakes");
       ArrayList<QuakeEntry> phraseFilter = filterByPhrase(list, where, phrase);
       int count=0;
       for(QuakeEntry qe : phraseFilter){
           System.out.println(qe);
           count++;
        }
        System.out.println("Found "+ count + " quakes that match " + phrase + " at " + where);
    }
    //End of assignment 3, by Abraham Ferrero
    
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }
    
}
