package com.unit_converter.Domain;

import com.unit_converter.Controller.DTO.ConversionOutputWithoutId;
import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "conversions")
@Getter
@Setter
public class Conversion {

    @Id
    private String conversionId;
    private String inputUnit;
    private String outputUnit;
    private float inputValue;
    private float outputValue;

    public Conversion(String conversionId, String inputUnit, String outputUnit, float inputValue, float outputValue) throws NotValidException {
        this.conversionId = generateId();
        this.inputUnit = inputUnit;
        this.outputUnit = outputUnit;
        if(inputValue < 0) throw new NotValidException("Input value must be positive");
        this.inputValue = inputValue;
        if(inputValue < 0) throw new NotValidException("Input value must be positive");
        this.outputValue = outputValue;
    }

    public static ConversionOutputWithoutId getConversionOutput(Conversion conversion) throws NotValidException {
        return new ConversionOutputWithoutId(conversion.getInputUnit(), conversion.getOutputUnit(),
                conversion.getInputValue(), conversion.getOutputValue());
    }

    public String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
