package com.crud.democrud.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Clase para la definicion del modelo Usuario Rol.
 * @author Camila Morales
 */
@Entity
@Table(name = "usuario_rol")
public class UsuarioRolModel {

    /**
     * Propiedades
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idRol;

    private String rol;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UsuarioModel.class, optional = false)
    @JoinColumn(name = "id_usuario", nullable = true)
    @JsonBackReference
    private UsuarioModel usuarioModel;

    /**
     * Constructor
     * @param rol
     * @param usuarioModel
     */
    public UsuarioRolModel(String rol, UsuarioModel usuarioModel) {
        this.rol = rol;
        this.usuarioModel = usuarioModel;
    }

    /**
     * Constructor vacio
     */
    public UsuarioRolModel() {
    }

    /**
     * Getters y Setters
     */
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }
}
