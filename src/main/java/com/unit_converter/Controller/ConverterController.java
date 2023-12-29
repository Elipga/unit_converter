package com.unit_converter.Controller;

import com.unit_converter.Controller.DTO.ConversionInput;
import com.unit_converter.Controller.DTO.ConversionOutput;
import com.unit_converter.Controller.DTO.ConversionOutputWithoutId;
import com.unit_converter.Exception.IsEmptyException;
import com.unit_converter.Exception.NotExistsConversionException;
import com.unit_converter.Exception.NotValidException;
import com.unit_converter.Service.ConverterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConverterController {
    @Autowired
    ConverterService converterService;

    @Operation(summary = "To do a conversion and save it")
    @PostMapping("/conversion")
    public ResponseEntity<ConversionOutputWithoutId> convertUnit(@RequestBody ConversionInput conversionInput) throws NotValidException {
        ConversionOutputWithoutId conversionOutputWithoutId = converterService.conversion(conversionInput);
        return ResponseEntity.ok(conversionOutputWithoutId);
    }

    @Operation(summary = "Get all conversions")
    @GetMapping("/conversions")
    public ResponseEntity<List<ConversionOutput>> getAllConversions() throws NotValidException, IsEmptyException {
        List<ConversionOutput> conversions = converterService.getAllConversions();
        return ResponseEntity.ok(conversions);
    }

    @Operation(summary = "Delete conversion by id")
    @DeleteMapping("/conversion/{conversionId}")
    public ResponseEntity<String> deleteConversion(@PathVariable String conversionId) throws NotExistsConversionException {
        converterService.deleteConversion(conversionId);
        return ResponseEntity.ok().build();
    }

    /*@PostMapping("/kmToMile")
    public ResponseEntity<Mile> kmToMile(@RequestBody Kilometer km) throws NotValidException {
        Mile mile = converterService.kmToMile(km);
        return ResponseEntity.ok(mile);
    }

    @PostMapping("/mileToKm")
    public ResponseEntity<Kilometer> mileToKm(@RequestBody Mile miles) throws NotValidException {
        Kilometer km = converterService.mileToKm(miles);
        return ResponseEntity.ok(km);
    }

    @PostMapping("/feetToMeter")
    public ResponseEntity<Meter> feetToMeter(@RequestBody Feet feet) throws NotValidException {
        Meter m = converterService.feetToMeter(feet);
        return ResponseEntity.ok(m);
    }

    @PostMapping("/meterToFeet")
    public ResponseEntity<Feet> meterToFeet(@RequestBody Meter meters) throws NotValidException {
        Feet feet = converterService.meterToFeet(meters);
        return ResponseEntity.ok(feet);
    }

    @PostMapping("/cmToInches")
    public ResponseEntity<Inch> cmToInches(@RequestBody Centimeter cm) throws NotValidException {
        Inch inches = converterService.cmToInches(cm);
        return ResponseEntity.ok(inches);
    }

    @PostMapping("/inchesToCm")
    public ResponseEntity<Centimeter> inchesToCm(@RequestBody Inch inches) throws NotValidException {
        Centimeter cm = converterService.inchesToCm(inches);
        return ResponseEntity.ok(cm);
    }*/
}
