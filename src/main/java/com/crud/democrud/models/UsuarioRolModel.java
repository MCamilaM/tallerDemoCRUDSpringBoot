package com.crud.democrud.models;

import javax.persistence.*;

@Entity
@Table(name = "usuarioRol")
public class UsuarioRolModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idRol;

    private String rol;

    @ManyToOne
    @JoinColumn(name = "usuario_model")
    private UsuarioModel usuarioModel;

    public UsuarioRolModel(String rol, UsuarioModel usuarioModel) {
        this.rol = rol;
        this.usuarioModel = usuarioModel;
    }

    public UsuarioRolModel() {
    }

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
