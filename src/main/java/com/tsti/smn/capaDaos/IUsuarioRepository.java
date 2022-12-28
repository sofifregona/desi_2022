package com.tsti.smn.capaDaos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.tsti.smn.pojos.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

	UserDetails findByUsername(String username);

}
