package com.rest.RESTAPI.ExternalAPIS;

import com.rest.RESTAPI.models.CEPModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;

public class CEP {

    public static void main(String[] args) {
         consult(56903060);
    }

    public static CEPModel consult(int cep){
        try {
            URL url = new URL("http://viacep.com.br/ws/"+cep+"/json");
            URLConnection conection = url.openConnection();
            InputStream is= conection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Gson gson = new Gson();
            return gson.fromJson(br,CEPModel.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
