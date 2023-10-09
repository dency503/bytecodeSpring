package com.bytecode.bytecodeecommerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Table(name = "Usuarios", schema = "Empleados")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioID")
    private Long usuarioId;

   /* @ManyToOne
    @JoinColumn(name = "EmpleadoID")
    private Empleado empleado;*/



    @NotEmpty(message = "El nombre de usuario no puede estar vacío")
    @Column(name = "Username")
    private String username;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Column(name = "email")
    private String email;
    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Column(name = "Password")
    private String password;
    /*
     @Column(name = "FechaCreacion")
       private Date fechaCreacion = new Date();

      @Column(name = "UltimoLogin")
       private LocalDateTime ultimoLogin;*/
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name())); // No contiene el prefijo "ROLE_"
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
