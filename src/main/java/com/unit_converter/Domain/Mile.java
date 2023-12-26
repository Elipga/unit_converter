package com.unit_converter.Domain;

import javax.persistence.*;

import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Mile {
    @Id
    private float miles;

    public Mile(float miles) throws NotValidException {
        if(miles < 0) throw new NotValidException("Miles must be positive");
        this.miles = miles;
    }
}
