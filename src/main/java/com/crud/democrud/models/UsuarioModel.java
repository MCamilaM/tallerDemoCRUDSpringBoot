package com.crud.democrud.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.List;

/**
 * Clase para la definicion del modelo Usuario.
 * @author Camila Morales
 */
@Entity
@Table(name = "usuario")
public class UsuarioModel {

    /**
     * Propiedades
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nombre;
    private String email;
    private Integer prioridad;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = UsuarioRolModel.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "usuarioModel"
    )
    @JsonManagedReference
    private List<UsuarioRolModel> usuarioRoles;

    /**
     *Getters y Setters
     */
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Constructor
     * @param nombre
     * @param email
     * @param prioridad
     */
    public UsuarioModel(String nombre, String email, Integer prioridad) {
        this.nombre = nombre;
        this.email = email;
        this.prioridad = prioridad;
    }

    /**
     * Constructor vacio
     */
    public UsuarioModel() {

    }



}