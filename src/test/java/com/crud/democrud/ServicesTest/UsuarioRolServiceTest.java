package com.crud.democrud.ServicesTest;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.usuarioRolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRolServiceTest {

    @Autowired
    usuarioRolRepository usuarioRolRepository;

    @Test
    public void testGuardarUsuarioRol() {
        UsuarioModel usuarioModel = new UsuarioModel("Maria", "mariac@gmail.com", 85);
        UsuarioRolModel usuarioRolModel = new UsuarioRolModel("practicante", usuarioModel);
        UsuarioRolModel usuarioRolModelRegistrado = usuarioRolRepository.save(usuarioRolModel);
        assertNotNull(usuarioRolModelRegistrado);
    }

    @Test
    public void testBuscarUsuarioRolPorId() {
        Long idBuscado = 11L;
        Optional<UsuarioRolModel> usuarioRolModelBuscado = usuarioRolRepository.findById(idBuscado);
        assertThat(usuarioRolModelBuscado.get().getIdRol()).isEqualTo(idBuscado);
    }

    @Test
    public void testListarUsuariosRol() {
        List<UsuarioRolModel> usuarioRolModelList = (List<UsuarioRolModel>) usuarioRolRepository.findAll();
        assertThat(usuarioRolModelList).size().isGreaterThan(0);
    }
}

