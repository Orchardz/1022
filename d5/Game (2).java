//Noah Chiang
// ID: 216612285
// Youtube link :https://youtu.be/X7C5Nf2FDm4
package com.example.caps;

import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import ca.roumani.i2c.Country;
import ca.roumani.i2c.CountryDB;

public class Game {

    private CountryDB db;
    public Game(){
        this.db=new CountryDB();
    }

    public String qa(){

        List<String > capitals = this.db.getCapitals();
        int n = capitals.size();

        int index = (int)(n*Math.random());

        String c = capitals.get(index);
        Map<String, Country> countryData = this.db.getData();
        Country ref = countryData.get(c);


        if(Math.random()<0.5){
            return  ref.getCapital()+" is the capital of ?\n"+ref.getName();
        }else {
            return  "what is the capital of " + ref.getName() + "?\n" + ref.getCapital();
        }


    }



}
