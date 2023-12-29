package com.unit_converter.Service;

import com.unit_converter.Controller.DTO.ConversionInput;
import com.unit_converter.Controller.DTO.ConversionOutput;
import com.unit_converter.Controller.DTO.ConversionOutputWithoutId;
import com.unit_converter.Domain.*;
import com.unit_converter.Exception.AlreadyExistConversion;
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

    public ConversionOutputWithoutId addConversion(ConversionInput conversionInput) throws NotValidException, AlreadyExistConversion {
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

    public ConversionOutputWithoutId conversionOutput(ConversionInput conversionInput, float outputValue) throws NotValidException, AlreadyExistConversion {
        if(conversionRepository.existsByInputUnitAndOutputUnitAndInputValue(conversionInput.getInputUnit(),
                conversionInput.getOutputUnit(), conversionInput.getInputValue())) throw new
                AlreadyExistConversion("Conversion already exist");
        Conversion conversion = ConversionInput.getConversion(conversionInput); //Input to conversion
        conversion.setOutputValue(outputValue); //set result to output value of conversion
        conversionRepository.save(conversion); // add conversion to repository
        return Conversion.getConversionOutput(conversion);
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
                "does not exist");
        conversionRepository.deleteById(conversionId);
    }
}
