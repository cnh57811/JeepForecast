package com.cgavlabs.jeepforecast.searchresults;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cgavlabs.jeepforecast.main.MainActivity;
import com.cgavlabs.jeepforecast.models.domain.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class SearchResultsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Double latitude = ((Location) getListAdapter().getItem(position)).getLatitude();
        Double longitude = ((Location) getListAdapter().getItem(position)).getLongitude();
        Timber.d("City, State:%s, Lat:%s, Long:%s", ((TextView) v).getText().toString(), latitude,
                longitude);
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("latitude", latitude);
        i.putExtra("longitude", longitude);
        startActivity(i);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Timber.d(query);

            Geocoder gc = new Geocoder(this);
            try {
                List<Address> addressList = gc.getFromLocationName(query, 5);
                List<Location> locations = new ArrayList<>();
                for (Address a : addressList) {
                    locations.add(new Location(a.getAddressLine(0), a.getLatitude(), a.getLongitude()));
                }
                ListAdapter adapter =
                        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, locations);
                setListAdapter(adapter);
            } catch (IOException e) {
                Timber.e(e, "Geocoder:  Something bad happened when finding a location");
            }
        }
    }
}
