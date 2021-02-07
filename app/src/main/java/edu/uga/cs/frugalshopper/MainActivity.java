/**
 * Programming Project 1: Frugal Shopper
 * by Emily King    811397438   eak42100@uga.edu
 */

package edu.uga.cs.frugalshopper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the only class for this app. It contains the variables and event necessary to help a frugal shopper.
 */
public class MainActivity extends AppCompatActivity {

    protected double getValue(EditText e) {
        String value = e.getText().toString();
        if(value.equals("")){
            value = "0";
        }
        return Double.parseDouble(value);
    }

    /**
     * This is the overridden method onCreate
     *
     * @param savedInstanceState - a bundle to help restoration of previous state as needed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//  TODO get toast to work

//        final TextView toast = findViewById(R.id.textView2);

        final EditText priceAtext = (EditText) findViewById(R.id.priceA);
        final EditText poundsAtext = (EditText) findViewById(R.id.poundsA);
        final EditText ouncesAtext = (EditText) findViewById(R.id.ouncesA);

        final EditText priceBtext = (EditText) findViewById(R.id.priceB);
        final EditText poundsBtext = (EditText) findViewById(R.id.poundsB);
        final EditText ouncesBtext = (EditText) findViewById(R.id.ouncesB);

        final EditText priceCtext = (EditText) findViewById(R.id.priceC);
        final EditText poundsCtext = (EditText) findViewById(R.id.poundsC);
        final EditText ouncesCtext = (EditText) findViewById(R.id.ouncesC);

        final TextView bestBuy = findViewById(R.id.bestBuy);
        final Button compare = findViewById(R.id.compare);

//  todo for debugging
        Toast.makeText(this, "testing this toast message", Toast.LENGTH_LONG).show();
//        Toast.makeText(MainActivity.this, "testing this toast message", Toast.LENGTH_LONG).show();

//        event handler for compare button
        compare.setOnClickListener(event -> {
            bestBuy.setText("Best Buy: "); // resets the bestBuy TextView so that old values do not carry over somehow

//  todo    for debugging

//            toast.setText("");
//            Toast.makeText(getApplicationContext(), "testing this second toast message", Toast.LENGTH_LONG).show();
//            Toast.makeText(MainActivity.this, "testing this seconf toast message", Toast.LENGTH_LONG).show();

//  TODO remove default text of "0" so that this works while displaying the hints

//            double priceA = Double.parseDouble(priceAtext.getText().toString());
//            double poundsA = Double.parseDouble(poundsAtext.getText().toString());
//            double ouncesA = Double.parseDouble(ouncesAtext.getText().toString());
//
//            double priceB = Double.parseDouble(priceBtext.getText().toString());
//            double poundsB = Double.parseDouble(poundsBtext.getText().toString());
//            double ouncesB = Double.parseDouble(ouncesBtext.getText().toString());
//
//            double priceC = Double.parseDouble(priceCtext.getText().toString());
//            double poundsC = Double.parseDouble(poundsCtext.getText().toString());
//            double ouncesC = Double.parseDouble(ouncesCtext.getText().toString());

            double priceA = getValue(priceAtext);
            double priceB = getValue(priceBtext);
            double priceC = getValue(priceCtext);

            double poundsA = getValue(priceAtext);
            double poundsB = getValue(priceBtext);
            double poundsC = getValue(priceCtext);

            double ouncesA = getValue(priceAtext);
            double ouncesB = getValue(priceBtext);
            double ouncesC = getValue(priceCtext);

            double unitPriceA;
            double unitPriceB;
            double unitPriceC;

            if (priceA <= 0 && priceB <= 0 && priceC <= 0) {    // if no item is entered with a price
//                toast.setText("You must input at least one product with a price and weight.");
                Toast.makeText(this, "You must input at least one product with a price and weight.", Toast.LENGTH_SHORT).show();
            } else {
                if ((priceA > 0 && (poundsA > 0 || ouncesA > 0)) || (priceA == 0 && poundsA == 0 && ouncesA == 0)) { // if a price and at least one weight is entered or nothing is entered
                    unitPriceA = (poundsA * 16 + ouncesA) / priceA;
                } else {
                    unitPriceA = -1;
                }
                if ((priceB > 0 && (poundsB > 0 || ouncesB > 0)) || (priceB == 0 && poundsB == 0 && ouncesB == 0)) {
                    unitPriceB = (poundsB * 16 + ouncesB) / priceB;
                } else {
                    unitPriceB = -1;
                }
                if ((priceC > 0 && (poundsC > 0 || ouncesC > 0)) || (priceC == 0 && poundsC == 0 && ouncesC == 0)) {
                    unitPriceC = (poundsC * 16 + ouncesC) / priceC;
                } else {
                    unitPriceC = -1;
                }

                // round values to two decimal places before comparing
                unitPriceA = Double.valueOf(String.format("%.2f", unitPriceA));
                unitPriceB = Double.valueOf(String.format("%.2f", unitPriceB));
                unitPriceC = Double.valueOf(String.format("%.2f", unitPriceC));

                double bestPrice = unitPriceA; // A is set as the default bestPrice
                char bestProduct = 'A';
                if (unitPriceB < bestPrice && unitPriceB > 0) { // if B is cheaper, it replaces A
                    bestPrice = unitPriceB;
                    bestProduct = 'B';
                }
                if (unitPriceC < bestPrice && unitPriceB > 0) { // if C is cheaper, it replaces the previous bestPrice
                    bestPrice = unitPriceC;
                    bestProduct = 'C';
                }
                if (unitPriceA < 0 || unitPriceB < 0 || unitPriceC < 0) { // an error occured because a weight was not entered for one or more of the prices entered
//                    toast.setText("You must enter a weight for each product that you enter.");
                    Toast.makeText(this, "You must enter a weight for each product that you enter.", Toast.LENGTH_SHORT).show();
                } else {
                    bestBuy.setText("Best Buy:  " + bestProduct + " at $" + bestPrice);
//  todo for debugging
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//                    toast.setText("");
                }
            }
        });
    }
}