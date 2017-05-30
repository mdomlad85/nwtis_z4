/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.rest.klijenti;

import java.io.StringReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.foi.nwtis.mdomladov.web.podaci.MeteoPodaci;
import org.foi.nwtis.mdomladov.web.podaci.MeteoPrognoza;

/**
 * Klasa koja služi za komunikaciju sa
 * http://openweathermap.org/ servisom
 * 
 * @author Marko Domladovac
 */
public class OWMKlijent {

    String apiKey;
    OWMRESTHelper helper;
    Client client;

    /**
     *
     * @param apiKey
     */
    public OWMKlijent(String apiKey) {
        this.apiKey = apiKey;
        helper = new OWMRESTHelper(apiKey);
        client = ClientBuilder.newClient();
    }

    /**
     * Dohvaćanje prognoze za geolokaciju
     * 
     * @param id
     * @param latitude
     * @param longitude
     * @return
     */
    public MeteoPrognoza[] getWeatherForecast(int id, String latitude, String longitude) {
        MeteoPrognoza[] prognoza = null;

        WebTarget webResource = client.target(OWMRESTHelper.getOWM_BASE_URI())
                .path(OWMRESTHelper.getOWM_Forecast_Path());
        webResource = webResource.queryParam("lat", latitude);
        webResource = webResource.queryParam("lon", longitude);
        webResource = webResource.queryParam("lang", "hr");
        webResource = webResource.queryParam("units", "metric");
        webResource = webResource.queryParam("APIKEY", apiKey);

        String odgovor = webResource.request(MediaType.APPLICATION_JSON).get(String.class);
        try {
            JsonReader reader = Json.createReader(new StringReader(odgovor));

            JsonObject jo = reader.readObject();
            JsonObject cityJsonObject = jo.getJsonObject("city");
            JsonArray weatherJsonArray = jo.getJsonArray("list");
            int weatherCount = weatherJsonArray.size();

            if (weatherCount > 0) {
                prognoza = new MeteoPrognoza[weatherCount];
            }

            for (int i = 0; i < weatherCount; i++) {
                MeteoPodaci meteoPodaci = createMeteoPrognoza(
                           weatherJsonArray.getJsonObject(i), 
                           cityJsonObject);
                
                   prognoza[i] = new MeteoPrognoza(id, i, meteoPodaci);
            }

        } catch (Exception ex) {
            Logger.getLogger(OWMKlijent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prognoza;
    }

    /**
     * Pomoćna metoda za kreiranje objekta
     * 
     * @param weatherJson
     * @param cityJson
     * @return 
     */
    private MeteoPodaci createMeteoPrognoza(JsonObject weatherJson, JsonObject cityJson) {
        

            MeteoPodaci mp = new MeteoPodaci();

            JsonObject coord = cityJson.getJsonObject("coord");
            mp.setLokacija(coord.getJsonNumber("lat").bigDecimalValue().toPlainString(),
                    coord.getJsonNumber("lon").bigDecimalValue().toPlainString());
            mp.setNaziv(cityJson.getJsonString("name").getString());

            mp.setTemperatureValue(new Double(weatherJson.getJsonObject("main").getJsonNumber("temp").doubleValue()).floatValue());
            mp.setTemperatureMin(new Double(weatherJson.getJsonObject("main").getJsonNumber("temp_min").doubleValue()).floatValue());
            mp.setTemperatureMax(new Double(weatherJson.getJsonObject("main").getJsonNumber("temp_max").doubleValue()).floatValue());
            mp.setTemperatureUnit("celsius");

            mp.setHumidityValue(new Double(weatherJson.getJsonObject("main").getJsonNumber("humidity").doubleValue()).floatValue());
            mp.setHumidityUnit("%");

            mp.setSeaLevelPressureValue(new Double(weatherJson.getJsonObject("main").getJsonNumber("sea_level").doubleValue()).floatValue());
            mp.setGroundLevelPressureValue(new Double(weatherJson.getJsonObject("main").getJsonNumber("grnd_level").doubleValue()).floatValue());
            mp.setPressureValue(new Double(weatherJson.getJsonObject("main").getJsonNumber("pressure").doubleValue()).floatValue());
            mp.setPressureUnit("hPa");

            mp.setWindSpeedValue(new Double(weatherJson.getJsonObject("wind").getJsonNumber("speed").doubleValue()).floatValue());
            mp.setWindSpeedName("");

            mp.setWindDirectionValue(new Double(weatherJson.getJsonObject("wind").getJsonNumber("deg").doubleValue()).floatValue());
            mp.setWindDirectionCode("");
            mp.setWindDirectionName("");

            mp.setCloudsValue(weatherJson.getJsonObject("clouds").getInt("all"));
            mp.setCloudsName(weatherJson.getJsonArray("weather").getJsonObject(0).getString("description"));
            mp.setPrecipitationMode("");

            mp.setWeatherNumber(weatherJson.getJsonArray("weather").getJsonObject(0).getInt("id"));
            mp.setWeatherValue(weatherJson.getJsonArray("weather").getJsonObject(0).getString("description"));
            mp.setWeatherIcon(weatherJson.getJsonArray("weather").getJsonObject(0).getString("icon"));

            mp.setLastUpdate(new Date(weatherJson.getJsonNumber("dt").bigDecimalValue().longValue() * 1000));
            
            return mp;
    }
}
