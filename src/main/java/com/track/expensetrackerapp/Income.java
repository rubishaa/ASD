package com.track.expensetrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("INCOME")
public class Income extends Transaction {

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
