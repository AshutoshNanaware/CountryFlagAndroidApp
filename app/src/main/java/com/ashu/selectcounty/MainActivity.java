package com.ashu.selectcounty;



//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.neovisionaries.i18n.CountryCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    HashMap<String,String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCountryCodeCounterName();
        initList();

        Spinner spinnerCountries = findViewById(R.id.spinner_countries);

        mAdapter = new CountryAdapter(this, mCountryList);
        spinnerCountries.setAdapter(mAdapter);

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

    private void initList() {
        String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};

        mCountryList = new ArrayList<>();
     //   mCountryList.add(new CountryItem("India", "https://flagcdn.com/w160/gb.png"));
      for(int i=0;i<countries.length;i++){
          if(map.containsKey(countries[i])){

              mCountryList.add(new CountryItem(countries[i],"https://flagcdn.com/w160/"+(map.get(countries[i])).toLowerCase()+".png"));
          }

      }
//        mCountryList.add(new CountryItem("China","https://flagcdn.com/w160/gb.png"));
//        mCountryList.add(new CountryItem("USA", "https://flagcdn.com/w160/gb.png"));
//        mCountryList.add(new CountryItem("Germany", "https://flagcdn.com/w160/gb.png"));
    }

     void getCountryCodeCounterName(){
        map.put("Andorra, Principality Of", "AD");
        map.put("United Arab Emirates", "AE");
        map.put("Afghanistan, Islamic State Of", "AF");
        map.put("Antigua And Barbuda", "AG");
        map.put("Anguilla", "AI");
        map.put("Albania", "AL");
        map.put("Armenia", "AM");
        map.put("Netherlands Antilles", "AN");
        map.put("Angola", "AO");
        map.put("Antarctica", "AQ");
        map.put("Argentina", "AR");
        map.put("American Samoa", "AS");
        map.put("Austria", "AT");
        map.put("Australia", "AU");
        map.put("Aruba", "AW");
        map.put("Azerbaidjan", "AZ");
        map.put("Bosnia-Herzegovina", "BA");
        map.put("Barbados", "BB");
        map.put("Bangladesh", "BD");
        map.put("Belgium", "BE");
        map.put("Burkina Faso", "BF");
        map.put("Bulgaria", "BG");
        map.put("Bahrain", "BH");
        map.put("Burundi", "BI");
        map.put("Benin", "BJ");
        map.put("Bermuda", "BM");       map.put("Brazil", "BR");
        map.put("Bahamas", "BS");
        map.put("Bhutan", "BT");
        map.put("Bouvet Island", "BV");
        map.put("Botswana", "BW");
        map.put("Belarus", "BY");
        map.put("Belize", "BZ");
        map.put("Brunei Darussalam", "BN");
        map.put("Bolivia", "BO");

        map.put("Canada", "CA");
        map.put("Cocos (Keeling) Islands", "CC");
        map.put("Central African Republic", "CF");
        map.put("Congo, The Democratic Republic Of The", "CD");
        map.put("Congo", "CG");
        map.put("Switzerland", "CH");
        map.put("Ivory Coast (Cote D'Ivoire)", "CI");
        map.put("Cook Islands", "CK");
        map.put("Chile", "CL");
        map.put("Cameroon", "CM");
        map.put("China", "CN");
        map.put("Colombia", "CO");
        map.put("Costa Rica", "CR");
        map.put("Former Czechoslovakia", "CS");
        map.put("Cuba", "CU");
        map.put("Cape Verde", "CV");
        map.put("Christmas Island", "CX");
        map.put("Cyprus", "CY");
        map.put("Czech Republic", "CZ");
        map.put("Germany", "DE");
        map.put("Djibouti", "DJ");
        map.put("Denmark", "DK");
        map.put("Dominica", "DM");
        map.put("Dominican Republic", "DO");
        map.put("Algeria", "DZ");
        map.put("Ecuador", "EC");
        map.put("Estonia", "EE");
        map.put("Egypt", "EG");
        map.put("Western Sahara", "EH");
        map.put("Eritrea", "ER");
        map.put("Spain", "ES");
        map.put("Ethiopia", "ET");
        map.put("Finland", "FI");
        map.put("Fiji", "FJ");
        map.put("Falkland Islands", "FK");
        map.put("Micronesia", "FM");
        map.put("Faroe Islands", "FO");
        map.put("France", "FR");
        map.put("France (European Territory)", "FX");
        map.put("Gabon", "GA");
        map.put("Great Britain", "UK");
        map.put("Grenada", "GD");
        map.put("Georgia", "GE");
        map.put("French Guyana", "GF");
        map.put("Ghana", "GH");
        map.put("Gibraltar", "GI");
        map.put("Greenland", "GL");
        map.put("Gambia", "GM");
        map.put("Guinea", "GN");
        map.put("Guadeloupe (French)", "GP");
        map.put("Equatorial Guinea", "GQ");
        map.put("Greece", "GR");
        map.put("S. Georgia & S. Sandwich Isls.", "GS");
        map.put("Guatemala", "GT");
        map.put("Guam (USA)", "GU");
        map.put("Guinea Bissau", "GW");
        map.put("Guyana", "GY");
        map.put("Hong Kong", "HK");
        map.put("Heard And McDonald Islands", "HM");
        map.put("Honduras", "HN");
        map.put("Croatia", "HR");
        map.put("Haiti", "HT");
        map.put("Hungary", "HU");
        map.put("Indonesia", "ID");
        map.put("Ireland", "IE");
        map.put("Israel", "IL");
        map.put("India", "IN");
        map.put("British Indian Ocean Territory", "IO");
        map.put("Iraq", "IQ");
        map.put("Iran", "IR");
        map.put("Iceland", "IS");
        map.put("Italy", "IT");
        map.put("Jamaica", "JM");
        map.put("Jordan", "JO");
        map.put("Japan", "JP");
        map.put("Kenya", "KE");
        map.put("Kyrgyz Republic (Kyrgyzstan)", "KG");
        map.put("Cambodia, Kingdom Of", "KH");
        map.put("Kiribati", "KI");
        map.put("Comoros", "KM");
        map.put("Saint Kitts & Nevis Anguilla", "KN");
        map.put("North Korea", "KP");
        map.put("South Korea", "KR");
        map.put("Kuwait", "KW");
        map.put("Cayman Islands", "KY");
        map.put("Kazakhstan", "KZ");
        map.put("Laos", "LA");
        map.put("Lebanon", "LB");
        map.put("Saint Lucia", "LC");
        map.put("Liechtenstein", "LI");
        map.put("Sri Lanka", "LK");
        map.put("Liberia", "LR");
        map.put("Lesotho", "LS");
        map.put("Lithuania", "LT");
        map.put("Luxembourg", "LU");
        map.put("Latvia", "LV");
        map.put("Libya", "LY");
        map.put("Morocco", "MA");
        map.put("Monaco", "MC");
        map.put("Moldavia", "MD");
        map.put("Madagascar", "MG");
        map.put("Marshall Islands", "MH");
        map.put("Macedonia", "MK");
        map.put("Mali", "ML");
        map.put("Myanmar", "MM");
        map.put("Mongolia", "MN");
        map.put("Macau", "MO");
        map.put("Northern Mariana Islands", "MP");
        map.put("Martinique (French)", "MQ");
        map.put("Mauritania", "MR");
        map.put("Montserrat", "MS");
        map.put("Malta", "MT");
        map.put("Mauritius", "MU");
        map.put("Maldives", "MV");
        map.put("Malawi", "MW");
        map.put("Mexico", "MX");
        map.put("Malaysia", "MY");
        map.put("Mozambique", "MZ");
        map.put("Namibia", "NA");
        map.put("New Caledonia (French)", "NC");
        map.put("Niger", "NE");
        map.put("Norfolk Island", "NF");
        map.put("Nigeria", "NG");
        map.put("Nicaragua", "NI");
        map.put("Netherlands", "NL");
        map.put("Norway", "NO");
        map.put("Nepal", "NP");
        map.put("Nauru", "NR");
        map.put("Neutral Zone", "NT");
        map.put("Niue", "NU");
        map.put("New Zealand", "NZ");
        map.put("Oman", "OM");
        map.put("Panama", "PA");
        map.put("Peru", "PE");
        map.put("Polynesia (French)", "PF");
        map.put("Papua New Guinea", "PG");
        map.put("Philippines", "PH");
        map.put("Pakistan", "PK");
        map.put("Poland", "PL");
        map.put("Saint Pierre And Miquelon", "PM");
        map.put("Pitcairn Island", "PN");
        map.put("Puerto Rico", "PR");
        map.put("Portugal", "PT");
        map.put("Palau", "PW");
        map.put("Paraguay", "PY");
        map.put("Qatar", "QA");
        map.put("Reunion (French)", "RE");
        map.put("Romania", "RO");
        map.put("Russian Federation", "RU");
        map.put("Rwanda", "RW");
        map.put("Saudi Arabia", "SA");
        map.put("Solomon Islands", "SB");
        map.put("Seychelles", "SC");
        map.put("Sudan", "SD");
        map.put("Sweden", "SE");
        map.put("Singapore", "SG");
        map.put("Saint Helena", "SH");
        map.put("Slovenia", "SI");
        map.put("Svalbard And Jan Mayen Islands", "SJ");
        map.put("Slovak Republic", "SK");
        map.put("Sierra Leone", "SL");
        map.put("San Marino", "SM");
        map.put("Senegal", "SN");
        map.put("Somalia", "SO");
        map.put("Suriname", "SR");
        map.put("Saint Tome (Sao Tome) And Principe", "ST");
        map.put("Former USSR", "SU");
        map.put("El Salvador", "SV");
        map.put("Syria", "SY");
        map.put("Swaziland", "SZ");
        map.put("Turks And Caicos Islands", "TC");
        map.put("Chad", "TD");
        map.put("French Southern Territories", "TF");
        map.put("Togo", "TG");
        map.put("Thailand", "TH");
        map.put("Tadjikistan", "TJ");
        map.put("Tokelau", "TK");
        map.put("Turkmenistan", "TM");
        map.put("Tunisia", "TN");
        map.put("Tonga", "TO");
        map.put("East Timor", "TP");
        map.put("Turkey", "TR");
        map.put("Trinidad And Tobago", "TT");
        map.put("Tuvalu", "TV");
        map.put("Taiwan", "TW");
        map.put("Tanzania", "TZ");
        map.put("Ukraine", "UA");
        map.put("Uganda", "UG");
        map.put("United Kingdom", "UK");
        map.put("USA Minor Outlying Islands", "UM");
        map.put("United States", "US");
        map.put("Uruguay", "UY");
        map.put("Uzbekistan", "UZ");
        map.put("Holy See (Vatican City State)", "VA");
        map.put("Saint Vincent & Grenadines", "VC");
        map.put("Venezuela", "VE");
        map.put("Virgin Islands (British)", "VG");
        map.put("Virgin Islands (USA)", "VI");
        map.put("Vietnam", "VN");
        map.put("Vanuatu", "VU");
        map.put("Wallis And Futuna Islands", "WF");
        map.put("Samoa", "WS");
        map.put("Yemen", "YE");
        map.put("Mayotte", "YT");
        map.put("Yugoslavia", "YU");
        map.put("South Africa", "ZA");
        map.put("Zambia", "ZM");
        map.put("Zaire", "ZR");
        map.put("Zimbabwe", "ZW");

    }

}



