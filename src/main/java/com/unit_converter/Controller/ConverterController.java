package com.unit_converter.Controller;

import com.unit_converter.Controller.DTO.ConversionInput;
import com.unit_converter.Controller.DTO.ConversionOutput;
import com.unit_converter.Controller.DTO.ConversionOutputWithoutId;
import com.unit_converter.Exception.AlreadyExistConversion;
import com.unit_converter.Exception.IsEmptyException;
import com.unit_converter.Exception.NotExistsConversionException;
import com.unit_converter.Exception.NotValidException;
import com.unit_converter.Service.ConverterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;

@RestController
public class ConverterController {
    @Autowired
    ConverterService converterService;

    @Operation(summary = "To do a conversion and save it")
    @PostMapping("/conversions")
    public ResponseEntity<ConversionOutputWithoutId> addConversion(@RequestBody ConversionInput conversionInput) throws NotValidException, AlreadyExistConversion {
        ConversionOutputWithoutId conversionOutputWithoutId = converterService.addConversion(conversionInput);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all conversions")
    @GetMapping("/conversions")
    public ResponseEntity<List<ConversionOutput>> getAllConversions() throws NotValidException, IsEmptyException {
        List<ConversionOutput> conversions = converterService.getAllConversions();
        return ResponseEntity.ok(conversions);
    }

    @Operation(summary = "Get conversion by unit input, unit output and input value")
    @GetMapping("/conversions/{inputUnit}/{outputUnit}/{inputValue}")
    public ResponseEntity<ConversionOutputWithoutId> getConversionByUnitAndValue(@PathVariable String inputUnit,  @PathVariable String
            outputUnit, @PathVariable float inputValue) throws NotValidException, AlreadyExistConversion {
        ConversionOutputWithoutId conversionOutput = converterService.getConversionByUnitAndValue(inputUnit, outputUnit, inputValue);
        return ResponseEntity.ok(conversionOutput);
    }

    @Operation(summary = "Delete conversion by id")
    @DeleteMapping("/conversions/{conversionId}")
    public ResponseEntity<String> deleteConversion(@PathVariable String conversionId) throws NotExistsConversionException {
        converterService.deleteConversion(conversionId);
        return ResponseEntity.ok().build();
    }
}
