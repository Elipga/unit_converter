package com.unit_converter.Domain;

import javax.persistence.*;

import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Inch {
    @Id
    private float inches;

    public Inch(float inches) throws NotValidException {
        if(inches < 0) throw new NotValidException("Inches must be positive");
        this.inches = inches;
    }
}
