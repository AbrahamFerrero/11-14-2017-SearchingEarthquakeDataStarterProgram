import java.util.*;
/**This whole class is part o Assignment 5
 * @author (Abraham Ferrero) 
 * @version (15/NOV/2017)
 */
public class LargestQuakes {
    /*I don't see the point of this method. Although, it was an assignment 5 requirement,
     * it won't be used during the rest of the assignment and it was already developed in
     * another class.
     */
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        ArrayList<QuakeEntry> newQuakeData = new ArrayList<QuakeEntry>(quakeData);
        int count = 0;
        System.out.println("Total quakes checked = " + newQuakeData.size());
        for (QuakeEntry qe: newQuakeData){
            System.out.println(qe);
            count++;
        }
        System.out.println("Total found = " + count);
    }
    /*This method iterates over our quake database, gets the magnitude and calculates the biggest one,
     * storing it as well as the index in the arraylist, and returns this index. It will be really usefull
     * in our next method. The printing methods are just for particular testing purposes.
     */
    public int indexOfLargest(ArrayList<QuakeEntry> data){
       double maxIndex = 0; 
       int index = 0;
       for(int i=0; i<data.size(); i++){
           QuakeEntry quake = data.get(i);
           double magnitude = quake.getMagnitude();
           //System.out.println(magnitude);
           if (magnitude > maxIndex){
               maxIndex = magnitude;
               index = i;
            }
        }
       //System.out.println("Index with biggest value is = " + index + " and max magnitude is: " +maxIndex);
       return index;
    }
    
    //This tester just executes the methods inserting the parameters as desired.
    public void findLargestQuakesTest(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        int largeQuake = indexOfLargest(quakeData);
        System.out.println("Index with biggest value is = " + largeQuake);
        ArrayList<QuakeEntry> answer = getLargest(quakeData,50);
        for (int i=0; i<answer.size(); i++){
            System.out.println(answer.get(i));
        }
    }
    
    /*This method creates a copy of our quake data, and a new Arraylist for storing data
     * as a result of our algorithm. We will calculate the largest index in our quakeData(the copy of it)
     * add it to our new Arraylist, and erase it from our copy of our quakeDatabase.
     * We have created the copy because we are manipulating(erasing) the database for
     * our purposes, so we create a backup to preserve the original database. 
     */
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (int i=0; i<howMany; i++){
            answer.add(copy.get(indexOfLargest(copy)));
            copy.remove(copy.get(indexOfLargest(copy)));
        }
        return answer;  
    }
}
