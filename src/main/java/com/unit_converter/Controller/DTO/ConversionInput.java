package com.unit_converter.Controller.DTO;

import com.unit_converter.Domain.Conversion;
import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class ConversionInput {

    private String inputUnit;
    private String outputUnit;

    private float inputValue;

    public ConversionInput(String inputUnit, String outputUnit, float inputValue) throws NotValidException {
        this.inputUnit = inputUnit;
        this.outputUnit = outputUnit;
        if(inputValue < 0) throw new NotValidException("Input value must be positive");
        this.inputValue = inputValue;
    }


    public static Conversion getConversion(ConversionInput conversionInput) throws NotValidException {
    return new Conversion(conversionInput.generateId(), conversionInput.getInputUnit(), conversionInput.getOutputUnit(),
                conversionInput.getInputValue(), 0) ;
    }

    public String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
