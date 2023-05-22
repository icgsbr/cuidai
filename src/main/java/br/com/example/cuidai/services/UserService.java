package br.com.example.cuidai.services;

import br.com.example.cuidai.dtos.UserDTO;
import br.com.example.cuidai.models.UserModel;
import br.com.example.cuidai.repositories.IUserRepository;
import br.com.example.cuidai.services.utils.IMCCalculationUtil;
import br.com.example.cuidai.services.utils.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public UserModel save(UserDTO userDTO) {
        if (!existsByEmail(userDTO.get_email())) {
            UserModel userModel = new UserModel();

            BeanUtils.copyProperties(userDTO, userModel);

            userModel.set_password(PasswordUtil.createPassword(userModel.get_password()));

            userModel.set_imc(IMCCalculationUtil.calculate(userModel.get_weight(), userModel.get_height()));

            userModel.set_passwordStrength(PasswordUtil.verifyPasswordStrength(userModel.get_password()));

            return userRepository.save(userModel);
        }
        return null;
    }

    public List<UserModel> find() {
        if (!userRepository.findAll().isEmpty()) {
            return userRepository.findAll();
        }
        return null;
    }

    public UserModel find(UUID userId) {
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        }
        return null;
    }

    public UserModel find(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return userRepository.findByEmail(email).get();
        }
        return null;
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsBy_email(email);
    }

    /*public UserModel update(UserDTO userDTO, String currentPassword) {
        if (existsByEmail(userDTO.get_email())) {
            PasswordUtil.changePassword(
                    userRepository.findPasswordByEmail(userDTO.get_email()),
                    currentPassword,
                    userDTO.get_password()
            );
        }
    }*/

    @Transactional
    public Boolean delete() {
        userRepository.deleteAll();

        return find().isEmpty();
    }

    @Transactional
    public Boolean delete(UUID userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);

            return true;
        }
        return false;
    }

    @Transactional
    public Boolean delete(String email) {
        if (userRepository.existsBy_email(email)) {
            userRepository.deleteBy_email(email);

            return true;
        }
        return false;
    }

    public Boolean login(String email, String password) {
        return existsByEmail(email) &&
                PasswordUtil.authenticatePassword(password, userRepository.findPasswordByEmail(email));
    }

    public Double countByGender(String gender) {
        if (!gender.isEmpty()) {
            return userRepository.countAllBy_genderEqualsIgnoreCase(gender);
        }
        return null;
    }
}
