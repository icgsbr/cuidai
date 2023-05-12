package br.com.example.cuidai.models;

import br.com.example.cuidai.services.utils.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID _id;

    private String _name;

    private String _lastName;

    @Column(unique = true)
    private String _email;

    private String _password;

    private String _ageRange;

    private String _gender = "Outros";

    private Double _height;

    private Double _weight;

    private Double _imc;

    private PasswordUtil.PasswordStrength _passwordStrength;
}
