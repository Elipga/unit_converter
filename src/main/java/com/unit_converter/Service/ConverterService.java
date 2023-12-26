package com.unit_converter.Service;

import com.unit_converter.Domain.Kilometer;
import com.unit_converter.Domain.Mile;
import com.unit_converter.Exception.NotValidException;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    public Mile kmToMile(Kilometer km) throws NotValidException {
        float result = (float) (km.getKm() * 1.60934); //constant 1.60934 is considered Double
        Mile mile = new Mile(result);
        return mile;
    }

    public Kilometer mileToKm(Mile miles) throws NotValidException {
        float result = (float) (miles.getMiles() * 0.621371);
        Kilometer kilometer = new Kilometer(result);
        return kilometer;
    }

    public float feetToMeter(float feet) throws NotValidException {
        if(feet < 0) throw new NotValidException("Feet must be positive");
        float result;
        result = (float) (feet * 0.3048);
        return result;
    }

    public float meterToFeet(float meters) throws NotValidException {
        if(meters < 0) throw new NotValidException("Meters must be positive");
        float result;
        result = (float) (meters * 3.28084);
        return result;
    }

    public float cmToInches(float inches) throws NotValidException {
        if(inches < 0) throw new NotValidException("Inches must be positive");
        float result;
        result = (float) (inches * 0.393701);
        return result;
    }

    public float inchesToCm(float cms) throws NotValidException {
        if(cms < 0) throw new NotValidException("Centimeters must be positive");
        float result;
        result = (float) (cms * 2.54);
        return result;
    }

}
