package com.unit_converter.Domain;

import javax.persistence.*;

import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Meter {
    @Id
    private float m;

    public Meter(float m) throws NotValidException {
        if(m < 0) throw new NotValidException("Meters must be positive");
        this.m = m;
    }
}

