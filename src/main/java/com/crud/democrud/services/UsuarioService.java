package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    @Transactional
    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioModel> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }


    @Transactional(readOnly = true)
    public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad) {
        return usuarioRepository.findByPrioridad(prioridad);
    }

    @Transactional
    public boolean eliminarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    @Transactional
    public UsuarioModel actualizarUsuario(Long id, UsuarioModel usuarioModel) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        usuario.get().setNombre(usuarioModel.getNombre());
        usuario.get().setEmail(usuarioModel.getEmail());
        usuario.get().setPrioridad(usuarioModel.getPrioridad());
        return usuarioRepository.save(usuario.get());
    }

}