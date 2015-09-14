package com.peak.framentexample;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements CountryFragment.OnFragmentInteractionListener {

    CountryDetails fragCountryDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.container) != null)
        {
            // Se è presente il FrameLayout con id container,
            // vuol dire che siamo in SINGLE-PANE perciò
            // è necessario aggiungere il Fragment con la transazione.

            // Se savedInstanceState non è nullo, non siamo alla
            // prima visualizzazione perciò non serve aggiungere il Fragment.

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
            fragCountryDetails = new CountryDetails();
            Bundle b = new Bundle();
            b.putString("country", id);
            fragCountryDetails.setArguments(b);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.replace(R.id.container, CountryDetails.newInstance(id));
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onLongClick(String id) {
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
    }
}
