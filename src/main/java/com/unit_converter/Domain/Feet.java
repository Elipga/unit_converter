package com.unit_converter.Domain;
import javax.persistence.*;

import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Feet {
    @Id
    private float feet;

    public Feet(float feet) throws NotValidException {
        if(feet < 0) throw new NotValidException("Feet must be positive");
        this.feet = feet;
    }
}

