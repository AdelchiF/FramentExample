package com.peak.framentexample;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements CountryFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.container) != null)
        {
            // Se � presente il FrameLayout con id container,
            // vuol dire che siamo in SINGLE-PANE perci�
            // � necessario aggiungere il Fragment con la transazione.

            // Se savedInstanceState non � nullo, non siamo alla
            // prima visualizzazione perci� non serve aggiungere il Fragment.

            if (savedInstanceState != null)
                return;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, CountryFragment.newInstance("a", "b")).commit();
        }
    }

    @Override
    public void onFragmentInteraction(String id) {
        CountryDetails countryDetails = (CountryDetails)getSupportFragmentManager().findFragmentById(R.id.cityfrag);

        if(countryDetails != null && countryDetails.isInLayout()){
            countryDetails.onCountrySelected(id);
        }else {
            CountryDetails fragCountryDetails = new CountryDetails();
            Bundle b = new Bundle();
            b.putString("country", id);
            fragCountryDetails.setArguments(b);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, fragCountryDetails);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
