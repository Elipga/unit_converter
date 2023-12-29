package com.unit_converter.Repository;

import com.unit_converter.Domain.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository <Conversion,String> {
    Boolean existsByInputUnitAndOutputUnitAndInputValue (String inputUnit, String outputUnit,
                                                                       float inputValue);
}
