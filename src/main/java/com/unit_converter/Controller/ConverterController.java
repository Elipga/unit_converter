package com.unit_converter.Controller;

import com.unit_converter.Domain.Kilometer;
import com.unit_converter.Domain.Mile;
import com.unit_converter.Exception.NotValidException;
import com.unit_converter.Service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConverterController {
    @Autowired
    ConverterService converterService;

    @PostMapping("/kmToMile")
    public ResponseEntity <Mile> kmToMile(@RequestBody Kilometer km) {
        Mile mile = null;
        try {
            mile = converterService.kmToMile(km);
        } catch (NotValidException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
        return ResponseEntity.ok(mile);
    }

    @PostMapping("/mileToKm")
    public ResponseEntity <Kilometer> mileToKm(@RequestBody Mile miles){
        Kilometer km = null;
        try {
            km = converterService.mileToKm(miles);
        } catch (NotValidException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
        return ResponseEntity.ok(km);
    }

    @PostMapping("/feetToMeter")
    public ResponseEntity <Float> feetToMeter(@RequestBody float feet){
        float m = 0;
        try {
            m = converterService.feetToMeter(feet);
        } catch (NotValidException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
        return ResponseEntity.ok(m);
    }

    @PostMapping("/meterToFeet")
    public ResponseEntity <Float> meterToFeet(@RequestBody float meters){
        float feet = 0;
        try {
            feet = converterService.meterToFeet(meters);
        } catch (NotValidException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
        return ResponseEntity.ok(feet);
    }

    @PostMapping("/cmToInches")
    public ResponseEntity <Float> cmToInches(@RequestBody float cm){
        float inches = 0;
        try {
            inches = converterService.cmToInches(cm);
        } catch (NotValidException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
        return ResponseEntity.ok(inches);
    }

    @PostMapping("/inchesToCm")
    public ResponseEntity <Float> inchesToCm(@RequestBody float inches){
        float cm = 0;
        try {
            cm = converterService.inchesToCm(inches);
        } catch (NotValidException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
        return ResponseEntity.ok(cm);
    }
}
