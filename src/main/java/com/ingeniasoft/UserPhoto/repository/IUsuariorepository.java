package com.ingeniasoft.UserPhoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingeniasoft.UserPhoto.entity.Usuario;

public interface IUsuariorepository extends JpaRepository<Usuario, Long> {
}
