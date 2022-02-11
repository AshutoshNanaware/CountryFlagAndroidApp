package com.ashu.selectcounty;



//import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.neovisionaries.i18n.CountryCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
   String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};


   HashMap<String,String> map = new HashMap<>();
   HashMap<String,String> Countery_name_code_map=new HashMap<>();
   String url = "https://api.printful.com/countries";
   ProgressDialog dialog;
   ArrayList al=new ArrayList();
    Spinner spinnerCountries;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         dialog = new ProgressDialog(this);
         dialog.setMessage("Loading....");
         dialog.show();
       spinnerCountries = findViewById(R.id.spinner_countries);
         fetchJsonData();// get countrt name and code data form api

       // wait for  fetch api to get data
        Runnable a2=new Runnable() {
           @Override
           public void run() {
               initList(); // set data of country name and country flag endpoint to each item for spinner adapter
           }
       };
       Handler h1=new Handler();
       h1.postDelayed(a2,3000);

         spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getCountryName();
                Toast.makeText(MainActivity.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

   private void fetchJsonData() {
      StringRequest request = new StringRequest(url, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
            parseJsonData(response);

         }
      }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
         }
      });

      RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
      rQueue.add(request);
   }

   private void initList() {

        mCountryList = new ArrayList<>();
        /*
          THIS METHOD WILL GET RANDOM COUNTRY NAMES
       for (Map.Entry<String, String> entry : Countery_name_code_map.entrySet()) {

           mCountryList.add(new CountryItem(entry.getKey(),"https://flagcdn.com/w160/"+(entry.getValue()).toLowerCase()+".png"));
           Log.d("JSON Data" , " names --> "+ entry.getKey()+"  "+entry.getValue()  );
       }

       */
       // THIS METHO WILL GET ALPHABETICAL COUNTRY NAME AND THEIR RESP. CODE
      for(int i=0;i<countries.length;i++){


         if(Countery_name_code_map.containsKey(countries[i])){
            // set Countery according to our list of countries
             mCountryList.add(new CountryItem(countries[i],"https://flagcdn.com/w160/"+(Countery_name_code_map.get(countries[i])).toLowerCase()+".png"));

         }

      }
       mAdapter = new CountryAdapter(this, mCountryList);
       spinnerCountries.setAdapter(mAdapter);
    }

 // get string of country name and code from api string  responce
   void parseJsonData(String jsonString) {
      try {
         JSONObject object = new JSONObject(jsonString);
         JSONArray counteryArray = object.getJSONArray("result");

         for(int i = 0; i < counteryArray.length(); ++i) {
            al.add(counteryArray.getString(i));
            // map each country name and code
            Countery_name_code_map.put(
                    counteryArray.getJSONObject(i).getString("name"),
                    counteryArray.getJSONObject(i).getString("code")
            );

         }

      } catch (JSONException e) {
         e.printStackTrace();
      }
      dialog.dismiss();

      Log.d("JSON DATA", "parseJsonData: "+((Object)al.get(0))+ " data  "+ Countery_name_code_map.get( countries[0]))  ;
   }
}



