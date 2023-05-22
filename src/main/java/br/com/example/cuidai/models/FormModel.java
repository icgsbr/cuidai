package br.com.example.cuidai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FORM")
@Entity
public class FormModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "_userId", referencedColumnName = "_id"),
            @JoinColumn(name = "_userEmail", referencedColumnName = "_email")
    })
    private UserModel _user;

    private Double _achievement;

    private Double _supportingOthers;

    private Double _flow;

    private Double _coreCircle;

    private Double _placesVisited;

    private Double _todoCompleted;

    private Double _timeForPassion;

    private Double _fruitsAndVeggies;

    private Double _personalAwards;

    private LocalDateTime _dateOfCreation;
}
