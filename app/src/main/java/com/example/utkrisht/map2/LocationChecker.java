package com.example.utkrisht.map2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Criteria;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xml.sax.helpers.LocatorImpl;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by allenF on 5/6/16.
 */
public class LocationChecker {
    private static HashMap myFavLocs;
    public static float check = (float) 160.934;
    private GoogleMap mMap;

    public LocationChecker(GoogleMap mMap) {
        this.mMap = mMap;
        myFavLocs = new HashMap();
    }

    public void addLocation(String name, Location currLocation) {

        if(name.equals("") || name.equals(null) || myFavLocs.containsKey(name)) {
            Log.w("error", "No names specified");
            return;
        }
        if(currLocation == null) {
            Log.w("Location is Null", "Null Location");
            return;

        }
        myFavLocs.put(name, currLocation);
        if (mMap!=null) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(currLocation.getLatitude(),
                    currLocation.getLongitude())).title(name));
        }
        Log.v("Success", "Successfully added Location");
    }

    public static boolean checkForVisit(Location location){
        Iterator it = myFavLocs.entrySet().iterator();
        if(myFavLocs.isEmpty()) {
            return false;
        }
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            Location toComp = (Location) pair.getValue();
            float dist = location.distanceTo(toComp);
            if(dist <= check){
                Log.v("Visit", "A Visit has occurred!!");
                return true;
            }
        }
        return false;
    }

    public HashMap getMap(){
        return myFavLocs;
    }

}
