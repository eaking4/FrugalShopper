// Programming Project 1: Frugal Shopper
// by Emily King    811397438   eak42100@uga.edu

package edu.uga.cs.frugalshopper;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the only class for this app. It contains the variables and event necessary to help a frugal shopper.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Gets the value from an EditText
     *
     * @param e - EditText
     * @return 0 if the EditText contains no value
     */
    protected double getValue(EditText e) {
        String value = e.getText().toString();
        if (value.equals("")) {
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

        final EditText priceAtext = findViewById(R.id.priceA);
        final EditText poundsAtext = findViewById(R.id.poundsA);
        final EditText ouncesAtext = findViewById(R.id.ouncesA);

        final EditText priceBtext = findViewById(R.id.priceB);
        final EditText poundsBtext = findViewById(R.id.poundsB);
        final EditText ouncesBtext = findViewById(R.id.ouncesB);

        final EditText priceCtext = findViewById(R.id.priceC);
        final EditText poundsCtext = findViewById(R.id.poundsC);
        final EditText ouncesCtext = findViewById(R.id.ouncesC);

        final TextView bestBuy = findViewById(R.id.bestBuy);
        final Button compare = findViewById(R.id.compare);

//        event handler for compare button
        compare.setOnClickListener(event -> {
            bestBuy.setText("Best Buy: "); // resets the bestBuy TextView so that old values do not carry over somehow

            double priceA = getValue(priceAtext);
            double priceB = getValue(priceBtext);
            double priceC = getValue(priceCtext);

            double poundsA = getValue(poundsAtext);
            double poundsB = getValue(poundsBtext);
            double poundsC = getValue(poundsCtext);

            double ouncesA = getValue(ouncesAtext);
            double ouncesB = getValue(ouncesBtext);
            double ouncesC = getValue(ouncesCtext);

            double unitPriceA;
            double unitPriceB;
            double unitPriceC;

            if (priceA <= 0 && priceB <= 0 && priceC <= 0) {    // if no item is entered with a price
                Toast.makeText(MainActivity.this, "You must input at least one product with a price and weight.", Toast.LENGTH_SHORT).show();
            } else {
                if ((priceA > 0 && (poundsA > 0 || ouncesA > 0)) || (priceA == 0 && poundsA == 0 && ouncesA == 0)) { // if a price and at least one weight is entered or nothing is entered
                    unitPriceA = priceA / (poundsA * 16 + ouncesA);
                } else {
                    unitPriceA = -1;
                }
                if ((priceB > 0 && (poundsB > 0 || ouncesB > 0)) || (priceB == 0 && poundsB == 0 && ouncesB == 0)) {
                    unitPriceB = priceB / (poundsB * 16 + ouncesB);
                } else {
                    unitPriceB = -1;
                }
                if ((priceC > 0 && (poundsC > 0 || ouncesC > 0)) || (priceC == 0 && poundsC == 0 && ouncesC == 0)) {
                    unitPriceC = priceC / (poundsC * 16 + ouncesC);
                } else {
                    unitPriceC = -1;
                }

                // round values to two decimal places before comparing
                unitPriceA = Double.parseDouble(String.format("%.2f", unitPriceA));
                unitPriceB = Double.parseDouble(String.format("%.2f", unitPriceB));
                unitPriceC = Double.parseDouble(String.format("%.2f", unitPriceC));

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
                if (unitPriceA < 0 || unitPriceB < 0 || unitPriceC < 0) { // an error occurred because a weight was not entered for one or more of the prices entered
                    Toast.makeText(MainActivity.this, "You must enter a price and weight for each product that you enter.", Toast.LENGTH_SHORT).show();
                } else {
                    bestBuy.setText("Best Buy:  " + bestProduct + " at $" + bestPrice);
                }
            }
        });
    }
}