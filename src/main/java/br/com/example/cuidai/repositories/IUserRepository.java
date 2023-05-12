package br.com.example.cuidai.repositories;

import br.com.example.cuidai.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    @Query(value = "select user._password from UserModel user where user._email = :email")
    String findPasswordByEmail(@Param(value = "email") String email);

    @Query(value = "select user from UserModel user where user._email = :email")
    Optional<UserModel> findByEmail(@Param(value = "email") String email);

    Double countAllBy_genderEqualsIgnoreCase(String gender);

    @Modifying
    void deleteBy_email(String email);

    Boolean existsBy_email(String email);
}
