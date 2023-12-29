package com.unit_converter.Service;

import com.unit_converter.Controller.DTO.ConversionInput;
import com.unit_converter.Controller.DTO.ConversionOutput;
import com.unit_converter.Controller.DTO.ConversionOutputWithoutId;
import com.unit_converter.Domain.*;
import com.unit_converter.Exception.IsEmptyException;
import com.unit_converter.Exception.NotExistsConversionException;
import com.unit_converter.Exception.NotValidException;
import com.unit_converter.Repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterService {

    @Autowired
    ConversionRepository conversionRepository;

    public ConversionOutputWithoutId conversion(ConversionInput conversionInput) throws NotValidException {
        if(conversionInput.getInputUnit().toLowerCase().equals("km") && conversionInput.getOutputUnit().toLowerCase().equals("mile")) {
            float outputValue = (float) (conversionInput.getInputValue() * 1.60934); //create output value with result
            return conversionOutput(conversionInput, outputValue);
        }
        if(conversionInput.getInputUnit().toLowerCase().equals("mile") && conversionInput.getOutputUnit().toLowerCase().equals("km")) {
            float outputValue = (float) (conversionInput.getInputValue() / 1.60934);
            return conversionOutput(conversionInput, outputValue);
        }
        if(conversionInput.getInputUnit().toLowerCase().equals("feet") && conversionInput.getOutputUnit().toLowerCase().equals("meter")) {
            float outputValue = (float) (conversionInput.getInputValue() * 0.3048);
            return conversionOutput(conversionInput, outputValue);
        }
        if(conversionInput.getInputUnit().toLowerCase().equals("meter") && conversionInput.getOutputUnit().toLowerCase().equals("feet")) {
            float outputValue = (float) (conversionInput.getInputValue() / 0.3048);
            return conversionOutput(conversionInput, outputValue);
        }
        if(conversionInput.getInputUnit().toLowerCase().equals("cm") && conversionInput.getOutputUnit().toLowerCase().equals("inch")) {
            float outputValue = (float) (conversionInput.getInputValue() / 2.54);
            return conversionOutput(conversionInput, outputValue);
        }
        if(conversionInput.getInputUnit().toLowerCase().equals("inch") && conversionInput.getOutputUnit().toLowerCase().equals("cm")) {
            float outputValue = (float) (conversionInput.getInputValue() * 2.54);
            return conversionOutput(conversionInput, outputValue);
        }
        throw new NotValidException("Unit input and unit output must be: km, mile, feet, meter," +
                " cm or inch");
    }

    public ConversionOutputWithoutId conversionOutput(ConversionInput conversionInput, float outputValue) throws NotValidException {
        Conversion conversion = ConversionInput.getConversion(conversionInput); //Input to conversion
        conversion.setOutputValue(outputValue); //set result to output value of conversion
        conversionRepository.save(conversion); // add conversion to repository
        ConversionOutputWithoutId conversionOutputWithoutId = Conversion.getConversionOutput(conversion); //create conversion output for final Json
        return conversionOutputWithoutId;
    }

    public List<ConversionOutput> getAllConversions() throws IsEmptyException, NotValidException {
        List<Conversion> conversions = conversionRepository.findAll();
        List<ConversionOutput> conversionsOutput = new ArrayList<>();
        if (conversions.isEmpty()) throw new IsEmptyException("List of conversions is empty");

        for (Conversion conversion : conversions) {
            conversionsOutput.add(ConversionOutput.getConversion(conversion));
        }
        return conversionsOutput;
    }

    public void deleteConversion(String conversionId) throws NotExistsConversionException {
        if(!conversionRepository.existsById(conversionId)) throw new NotExistsConversionException("Conversion " +
                "doesn´t exist");
        conversionRepository.deleteById(conversionId);
    }

    public float kmToMile(ConversionInput conversionInput) throws NotValidException {
       return  (float) (conversionInput.getInputValue() * 1.60934);   //constant 1.60934 is considered Double
    }

    public float mileToKm (String input, String output, float miles) throws NotValidException {
        if(input.toLowerCase().equals("mile") && output.toLowerCase().equals("km")) {
            return (float) (miles / 1.60934);
        }
        throw new NotValidException("Unit input and unit output must be: km, mile, feet, meter," +
                " cm or inch");
    }

    public float feetToMeter(float feet) throws NotValidException {
        return  (float) (feet * 0.3048);
    }

    public float meterToFeet(float meters) throws NotValidException {
        return (float) (meters / 0.3048);
    }

    public float cmToInches(float cm) throws NotValidException {
        return (float) (cm / 2.54);
    }

    public float inchesToCm(float inches) throws NotValidException {
        return (float) (inches * 2.54);
    }

    /*public Mile kmToMile(Kilometer km) throws NotValidException {
        float result = (float) (km.getKm() * 1.60934); //constant 1.60934 is considered Double
        Mile mile = new Mile(result);
        return mile;
    }

    public Kilometer mileToKm(Mile miles) throws NotValidException {
        float result = (float) (miles.getMiles() / 1.60934);
        Kilometer kilometer = new Kilometer(result);
        return kilometer;
    }

    public Meter feetToMeter(Feet feet) throws NotValidException {
        float result = (float) (feet.getFeet() * 0.3048);
        Meter meter = new Meter(result);
        return meter;
    }

    public Feet meterToFeet(Meter meters) throws NotValidException {
        float result = (float) (meters.getM() / 0.3048);
        Feet feet = new Feet(result);
        return feet;
    }

    public Inch cmToInches(Centimeter cm) throws NotValidException {
        float result = (float) (cm.getCm() / 2.54);
        Inch inch = new Inch(result);
        return inch;
    }

    public Centimeter inchesToCm(Inch inches) throws NotValidException {
        float result = (float) (inches.getInches() * 2.54);
        Centimeter cm = new Centimeter(result);
        return cm;
    }*/
}
