package com.unit_converter.Controller.DTO;

import com.unit_converter.Domain.Conversion;
import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ConversionOutput{

    private String conversionId;
    private String inputUnit;
    private String outputUnit;

    private float inputValue;

    private float outputValue;

    public ConversionOutput(String conversionId, String inputUnit, String outputUnit, float inputValue, float outputValue) throws NotValidException {
        this.conversionId = conversionId;
        this.inputUnit = inputUnit;
        this.outputUnit = outputUnit;
        if(inputValue < 0) throw new NotValidException("Input value must be positive");
        this.inputValue = inputValue;
        if(outputValue < 0) throw new NotValidException("Output value must be positive");
        this.outputValue = outputValue;
    }

    public static ConversionOutput getConversion(Conversion conversion) throws NotValidException {
        return new ConversionOutput(conversion.getConversionId(), conversion.getInputUnit(), conversion.getOutputUnit(),
                conversion.getInputValue(), conversion.getOutputValue());
    }
}
