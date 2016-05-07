package com.example.utkrisht.map2.tests;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;

import com.example.utkrisht.map2.LocationChecker;
import com.example.utkrisht.map2.MapsActivity;
import com.google.android.gms.maps.GoogleMap;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by allenF on 5/6/16.
 */
public class Junit_test extends ActivityInstrumentationTestCase2<MapsActivity> {

    private GoogleMap mMap;

    LocationChecker locationChecker = new LocationChecker(mMap);


    public Junit_test(){
        super(MapsActivity.class);
    }


    //check if a location is successfully added in to the hashmap myFavLocs
    public void test_addLocation_success(){
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        locationChecker.addLocation("random",location);
        HashMap map = locationChecker.getMap();

        if (map.containsKey("random")==true && map.containsValue(location)==true){
            assertEquals(true,true);
        }



    }

    //null location should not be added
    public void test_addLocation_failure()
   {
       Location location = null;
       locationChecker.addLocation("null", location);
       HashMap map = locationChecker.getMap();

       assertEquals(false,map.containsKey("null"));


    }


    public void test_valid_visit(){
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        locationChecker.addLocation("random2",location);
        HashMap map = locationChecker.getMap();
        boolean test = locationChecker.checkForVisit(location);
        assertEquals(true, test);

    }

    public void test_invalid_visit(){
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);

        Location test_location = new Location("random2");
        test_location.setLatitude(120);
        test_location.setLongitude(145);
        locationChecker.addLocation("random3",location);
        HashMap map = locationChecker.getMap();
        boolean test = locationChecker.checkForVisit(test_location);
        assertEquals(false, test);
    }

}
