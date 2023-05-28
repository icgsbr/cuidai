package br.com.example.cuidai.services;

import br.com.example.cuidai.dtos.FormDTO;
import br.com.example.cuidai.models.FormModel;
import br.com.example.cuidai.models.UserModel;
import br.com.example.cuidai.repositories.IFormRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FormService {
    @Autowired
    UserService userService;

    @Autowired
    IFormRepository formRepository;

    @Transactional
    public FormModel save(FormDTO formDTO) {
        UserModel user = userService.find(formDTO.getUserEmail());

        FormModel formModel = new FormModel();

        if (userService.existsByEmail(formDTO.getUserEmail())) {
            BeanUtils.copyProperties(formDTO, formModel);

            formModel.set_dateOfCreation(LocalDateTime.now());

            formModel.set_user(user);

            return formRepository.save(formModel);
        }
        return null;
    }

    public List<FormModel> find() {
        if (!formRepository.findAll().isEmpty()) {
            return formRepository.findAll();
        }
        return null;
    }

    public FormModel find(String userEmail) {
        if (formRepository.findAllByUserEmail(userEmail).isPresent()) {
            return formRepository.findAllByUserEmail(userEmail).get();
        }
        return null;
    }

    public FormModel find(UUID id) {
        if (formRepository.findById(id).isPresent()) {
            return formRepository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public Boolean delete(UUID id) {
        if (formRepository.findById(id).isPresent()) {
            formRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean delete(String userEmail) {
        if (formRepository.findAllByUserEmail(userEmail).isPresent()) {
            formRepository.deleteAllByUserEmail(userEmail);
            return true;
        }
        return false;
    }

    @Transactional
    public Boolean delete() {
        formRepository.deleteAll();

        return formRepository.findAll().isEmpty();
    }
}
