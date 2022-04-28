package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.repositories.IUsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioRolService {
    @Autowired
    IUsuarioRolRepository iUsuarioRolRepository;

    @Transactional(readOnly = true)
    public ArrayList<UsuarioRolModel> obtenerUsuarioRol(){
        return (ArrayList<UsuarioRolModel>) iUsuarioRolRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<UsuarioRolModel> obtenerUsuarioRolPorId(Long id){
        return iUsuarioRolRepository.findById(id);
    }

    @Transactional
    public UsuarioRolModel guardarUsuarioRol(UsuarioRolModel usuarioRolModel){
        return iUsuarioRolRepository.save(usuarioRolModel);
    }
    @Transactional
    public boolean eliminarUsuarioRol(Long id){
        try{
            iUsuarioRolRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    @Transactional
    public UsuarioRolModel actualizarUsuarioRol(Long id, UsuarioRolModel usuarioRolModel){
        Optional<UsuarioRolModel> usuario = iUsuarioRolRepository.findById(id);
        usuario.get().setRol(usuarioRolModel.getRol());
        return iUsuarioRolRepository.save(usuario.get());
    }




}
