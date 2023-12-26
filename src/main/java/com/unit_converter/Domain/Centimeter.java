package com.unit_converter.Domain;

import javax.persistence.*;

import com.unit_converter.Exception.NotValidException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Entity
@NoArgsConstructor
@Getter
public class Centimeter {
    @Id
    private float cm;

    public Centimeter(float cm) throws NotValidException {
        if(cm < 0) throw new NotValidException("Cm must be positive");
        this.cm = cm;
    }
}
