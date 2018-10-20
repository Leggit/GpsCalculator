/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpscalculator;

/**
 * Class containing methods that can work out the distance and bearing between 2 sets of coordinates
 * @author Oliver
 */
public class HaversineFormula {
    static final double DEG_TO_RAD = Math.PI/180;//Trig functions need values in Radians
    static final double EARTH_RADIUS = 6371000.0;//in m, replace with whatever units you want to get out 
    
    /**
     * Method containing a formula that will work out the compass bearing of one set of coordinates to another
     * @param lat1 starting latitude (should usually be a bigger number compared to longitude) 
     * @param lon1 starting longitude 
     * @param lat2 destination latitude 
     * @param lon2 destination longitude
     * @return 
     * @returns the bearing from start to the destination
     */
    public static double bearing(double lat1, double lon1, double lat2, double lon2){
        double bearing;
        double y;
        double x;
        double b;
        
        //conversions
        lat1 *= DEG_TO_RAD;
        lon1 *= DEG_TO_RAD;
        lat2 *= DEG_TO_RAD;
        lon2 *= DEG_TO_RAD;
        
        y = Math.sin(lon2 -lon1) * Math.cos(lat2);
        x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1);
        b = Math.atan2(y, x);
        
        bearing = b/DEG_TO_RAD;//convert back to degrees
        
        //make sure ther are no negatives
        if(bearing < 0){
            bearing = 360 + bearing;
        }
        
        return bearing;
    }
    
    /**
     * Method containing a formula that will work out the distance from one set of coordinates to another
     * @param lat1 starting latitude (should usually be a bigger number compared to longitude) 
     * @param lon1 starting longitude 
     * @param lat2 destination latitude 
     * @param lon2 destination longitude
     * @return 
     * @returns the distance between the two points in meters
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2){
        double distance = 0;
        double toSquare;
        double h;
        
        //conversions
        lat1 *= DEG_TO_RAD;
        lon1 *= DEG_TO_RAD;
        lat2 *= DEG_TO_RAD;
        lon2 *= DEG_TO_RAD;
        
        toSquare = ((Math.sin((lat1 - lat2) / 2.0))) + (Math.cos(lat1) * Math.cos(lat2) * Math.pow((Math.sin((lon1 - lon2) / 2.0)), 2));
        h = Math.pow(toSquare,2);
        
        distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));
        
        return distance;
    }
}
