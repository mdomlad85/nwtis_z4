/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mdomladov.web.podaci;

import java.util.Date;

/**
 *
 * @author dkermek
 */
public class MeteoPodaci {

    private Date sunRise;
    private Date sunSet;

    private Float temperatureValue;
    private Float temperatureMin;
    private Float temperatureMax;
    private String temperatureUnit;

    private Float humidityValue;
    private String humidityUnit;

    private Float seaLevelPressureValue;
    private Float groundLevelPressureValue;
    private Float pressureValue;
    private String pressureUnit;

    private Float windSpeedValue;
    private String windSpeedName;
    private Float windDirectionValue;
    private String windDirectionCode;
    private String windDirectionName;

    private int cloudsValue;
    private String cloudsName;

    private String visibility;

    private Float precipitationValue;
    private String precipitationMode;
    private String precipitationUnit;

    private int weatherNumber;
    private String weatherValue;
    private String weatherIcon;
    private Date lastUpdate;
    
    private String naziv;
    private Lokacija lokacija;

    /**
     *
     */
    public MeteoPodaci() {
    }

    /**
     *
     * @param sunRise
     * @param sunSet
     * @param temperatureValue
     * @param temperatureMin
     * @param temperatureMax
     * @param temperatureUnit
     * @param humidityValue
     * @param humidityUnit
     * @param seaLevelPressureValue
     * @param groundLevelPressureValue
     * @param pressureValue
     * @param pressureUnit
     * @param windSpeedValue
     * @param windSpeedName
     * @param windDirectionValue
     * @param windDirectionCode
     * @param windDirectionName
     * @param cloudsValue
     * @param cloudsName
     * @param visibility
     * @param precipitationValue
     * @param precipitationMode
     * @param precipitationUnit
     * @param weatherNumber
     * @param weatherValue
     * @param weatherIcon
     * @param lastUpdate
     * @param naziv
     * @param lokacija
     */
    public MeteoPodaci(Date sunRise, Date sunSet, Float temperatureValue, Float temperatureMin, Float temperatureMax, String temperatureUnit, Float humidityValue, String humidityUnit, Float seaLevelPressureValue, Float groundLevelPressureValue, Float pressureValue, String pressureUnit, Float windSpeedValue, String windSpeedName, Float windDirectionValue, String windDirectionCode, String windDirectionName, int cloudsValue, String cloudsName, String visibility, Float precipitationValue, String precipitationMode, String precipitationUnit, int weatherNumber, String weatherValue, String weatherIcon, Date lastUpdate, String naziv, Lokacija lokacija) {
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.temperatureValue = temperatureValue;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.temperatureUnit = temperatureUnit;
        this.humidityValue = humidityValue;
        this.humidityUnit = humidityUnit;
        this.seaLevelPressureValue = seaLevelPressureValue;
        this.groundLevelPressureValue = groundLevelPressureValue;
        this.pressureValue = pressureValue;
        this.pressureUnit = pressureUnit;
        this.windSpeedValue = windSpeedValue;
        this.windSpeedName = windSpeedName;
        this.windDirectionValue = windDirectionValue;
        this.windDirectionCode = windDirectionCode;
        this.windDirectionName = windDirectionName;
        this.cloudsValue = cloudsValue;
        this.cloudsName = cloudsName;
        this.visibility = visibility;
        this.precipitationValue = precipitationValue;
        this.precipitationMode = precipitationMode;
        this.precipitationUnit = precipitationUnit;
        this.weatherNumber = weatherNumber;
        this.weatherValue = weatherValue;
        this.weatherIcon = weatherIcon;
        this.lastUpdate = lastUpdate;
        this.naziv = naziv;
        this.lokacija = lokacija;
    }
        
    /**
     *
     * @return
     */
    public Date getSunRise() {
        return sunRise;
    }

    /**
     *
     * @param sunRise
     */
    public void setSunRise(Date sunRise) {
        this.sunRise = sunRise;
    }

    /**
     *
     * @return
     */
    public Date getSunSet() {
        return sunSet;
    }

    /**
     *
     * @param sunSet
     */
    public void setSunSet(Date sunSet) {
        this.sunSet = sunSet;
    }

    /**
     *
     * @return
     */
    public Float getTemperatureValue() {
        return temperatureValue;
    }

    /**
     *
     * @param temperatureValue
     */
    public void setTemperatureValue(Float temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    /**
     *
     * @return
     */
    public Float getTemperatureMin() {
        return temperatureMin;
    }

    /**
     *
     * @param temperatureMin
     */
    public void setTemperatureMin(Float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    /**
     *
     * @return
     */
    public Float getTemperatureMax() {
        return temperatureMax;
    }

    /**
     *
     * @param temperatureMax
     */
    public void setTemperatureMax(Float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    /**
     *
     * @return
     */
    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    /**
     *
     * @param temperatureUnit
     */
    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    /**
     *
     * @return
     */
    public Float getHumidityValue() {
        return humidityValue;
    }

    /**
     *
     * @param humidityValue
     */
    public void setHumidityValue(Float humidityValue) {
        this.humidityValue = humidityValue;
    }

    /**
     *
     * @return
     */
    public String getHumidityUnit() {
        return humidityUnit;
    }

    /**
     *
     * @param humidityUnit
     */
    public void setHumidityUnit(String humidityUnit) {
        this.humidityUnit = humidityUnit;
    }

    /**
     *
     * @return
     */
    public Float getPressureValue() {
        return pressureValue;
    }

    /**
     *
     * @param pressureValue
     */
    public void setPressureValue(Float pressureValue) {
        this.pressureValue = pressureValue;
    }

    /**
     *
     * @return
     */
    public String getPressureUnit() {
        return pressureUnit;
    }

    /**
     *
     * @param pressureUnit
     */
    public void setPressureUnit(String pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    /**
     *
     * @return
     */
    public Float getWindSpeedValue() {
        return windSpeedValue;
    }

    /**
     *
     * @param windSpeedValue
     */
    public void setWindSpeedValue(Float windSpeedValue) {
        this.windSpeedValue = windSpeedValue;
    }

    /**
     *
     * @return
     */
    public String getWindSpeedName() {
        return windSpeedName;
    }

    /**
     *
     * @param windSpeedName
     */
    public void setWindSpeedName(String windSpeedName) {
        this.windSpeedName = windSpeedName;
    }

    /**
     *
     * @return
     */
    public Float getWindDirectionValue() {
        return windDirectionValue;
    }

    /**
     *
     * @param windDirectionValue
     */
    public void setWindDirectionValue(Float windDirectionValue) {
        this.windDirectionValue = windDirectionValue;
    }

    /**
     *
     * @return
     */
    public String getWindDirectionCode() {
        return windDirectionCode;
    }

    /**
     *
     * @param windDirectionCode
     */
    public void setWindDirectionCode(String windDirectionCode) {
        this.windDirectionCode = windDirectionCode;
    }

    /**
     *
     * @return
     */
    public String getWindDirectionName() {
        return windDirectionName;
    }

    /**
     *
     * @param windDirectionName
     */
    public void setWindDirectionName(String windDirectionName) {
        this.windDirectionName = windDirectionName;
    }

    /**
     *
     * @return
     */
    public int getCloudsValue() {
        return cloudsValue;
    }

    /**
     *
     * @param cloudsValue
     */
    public void setCloudsValue(int cloudsValue) {
        this.cloudsValue = cloudsValue;
    }

    /**
     *
     * @return
     */
    public String getCloudsName() {
        return cloudsName;
    }

    /**
     *
     * @param cloudsName
     */
    public void setCloudsName(String cloudsName) {
        this.cloudsName = cloudsName;
    }

    /**
     *
     * @return
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     *
     * @param visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     *
     * @return
     */
    public Float getPrecipitationValue() {
        return precipitationValue;
    }

    /**
     *
     * @param precipitationValue
     */
    public void setPrecipitationValue(Float precipitationValue) {
        this.precipitationValue = precipitationValue;
    }

    /**
     *
     * @return
     */
    public String getPrecipitationMode() {
        return precipitationMode;
    }

    /**
     *
     * @param precipitationMode
     */
    public void setPrecipitationMode(String precipitationMode) {
        this.precipitationMode = precipitationMode;
    }

    /**
     *
     * @return
     */
    public String getPrecipitationUnit() {
        return precipitationUnit;
    }

    /**
     *
     * @param precipitationUnit
     */
    public void setPrecipitationUnit(String precipitationUnit) {
        this.precipitationUnit = precipitationUnit;
    }

    /**
     *
     * @return
     */
    public int getWeatherNumber() {
        return weatherNumber;
    }

    /**
     *
     * @param weatherNumber
     */
    public void setWeatherNumber(int weatherNumber) {
        this.weatherNumber = weatherNumber;
    }

    /**
     *
     * @return
     */
    public String getWeatherValue() {
        return weatherValue;
    }

    /**
     *
     * @param weatherValue
     */
    public void setWeatherValue(String weatherValue) {
        this.weatherValue = weatherValue;
    }

    /**
     *
     * @return
     */
    public String getWeatherIcon() {
        return weatherIcon;
    }

    /**
     *
     * @param weatherIcon
     */
    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    /**
     *
     * @return
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     *
     * @param lastUpdate
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        StringBuilder sb = getRow(" Temp: ", temperatureValue, temperatureUnit);
        sb.append(getRow("Vlaga: ", humidityValue, humidityUnit));
        sb.append(getRow(" Tlak: ", pressureValue, pressureUnit));

        return sb.toString();
    }

    private StringBuilder getRow(String label, Object value, String unit) {
        StringBuilder sb = new StringBuilder(label);
        sb.append(value);
        sb.append(" ");
        sb.append(unit);
        sb.append("<br/>");

        return sb;
    }

    /**
     *
     * @return
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     *
     * @param naziv
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    /**
     *
     * @return
     */
    public Lokacija getLokacija() {
        return lokacija;
    }

    /**
     *
     * @param latitude
     * @param longitude
     */
    public void setLokacija(String latitude, String longitude) {
        this.lokacija = new Lokacija(latitude, longitude);
    }

    /**
     *
     * @return
     */
    public Float getSeaLevelPressureValue() {
        return seaLevelPressureValue;
    }

    /**
     *
     * @param seaLevelPressureValue
     */
    public void setSeaLevelPressureValue(Float seaLevelPressureValue) {
        this.seaLevelPressureValue = seaLevelPressureValue;
    }

    /**
     *
     * @return
     */
    public Float getGroundLevelPressureValue() {
        return groundLevelPressureValue;
    }

    /**
     *
     * @param groundLevelPressureValue
     */
    public void setGroundLevelPressureValue(Float groundLevelPressureValue) {
        this.groundLevelPressureValue = groundLevelPressureValue;
    }
    
    
}
