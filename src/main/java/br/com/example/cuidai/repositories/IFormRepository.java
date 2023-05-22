package br.com.example.cuidai.repositories;

import br.com.example.cuidai.models.FormModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IFormRepository extends JpaRepository<FormModel, UUID> {
    @Query(value = "select form from FormModel form where form._user._email = :userEmail")
    Optional<FormModel> findAllByUserEmail(@Param(value = "userEmail") String userEmail);

    @Modifying
    @Query(value = "delete from FormModel form where form._user._email = :userEmail")
    void deleteAllByUserEmail(String userEmail);
}
