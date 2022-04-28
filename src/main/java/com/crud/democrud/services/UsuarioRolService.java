package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.usuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioRolService {
    @Autowired
    usuarioRolRepository usuarioRolRepository;

    @Transactional(readOnly = true)
    public ArrayList<UsuarioRolModel> obtenerUsuarioRol() {
        return (ArrayList<UsuarioRolModel>) usuarioRolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioRolModel> obtenerUsuarioRolPorId(Long id) {
        return usuarioRolRepository.findById(id);
    }

    @Transactional
    public UsuarioRolModel guardarUsuarioRol(UsuarioRolModel usuarioRolModel) {
        return usuarioRolRepository.save(usuarioRolModel);
    }

    @Transactional
    public boolean eliminarUsuarioRol(Long id) {
        try {
            usuarioRolRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    @Transactional
    public UsuarioRolModel actualizarUsuarioRol(Long id, UsuarioRolModel usuarioRolModel) {
        Optional<UsuarioRolModel> usuario = usuarioRolRepository.findById(id);
        usuario.get().setRol(usuarioRolModel.getRol());
        return usuarioRolRepository.save(usuario.get());
    }


}
