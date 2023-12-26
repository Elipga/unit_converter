package com.unit_converter.Domain;

import javax.persistence.*;

import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class Kilometer {
    @Id
    private float km;

    public Kilometer(float km) throws NotValidException {
        if(km < 0) throw new NotValidException("Km must be positive");
        this.km = km;
    }
}
