package com.bytecode.bytecodeecommerce.Repository;



import java.util.Optional;

import com.bytecode.bytecodeecommerce.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
    // Since email is unique, we'll find users by email
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);
}