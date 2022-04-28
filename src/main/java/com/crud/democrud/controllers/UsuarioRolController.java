package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioRolModel;
import com.crud.democrud.services.UsuarioRolService;
import com.crud.democrud.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;


@CrossOrigin
@RestController
@RequestMapping("/usuario/rol")
public class UsuarioRolController {

    @Autowired
    UsuarioRolService usuarioRolService;

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;


    @GetMapping()
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            response.data = this.usuarioRolService.obtenerUsuarioRol();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> obtenerUsuarioPorId(@PathVariable(value = "id") Long id) {
        response.restart();
        try {
            response.data = this.usuarioRolService.obtenerUsuarioRolPorId(id);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    @PostMapping()
    public ResponseEntity<Response> guardarUsuarioRol(@RequestBody UsuarioRolModel usuarioRolModel) {
        response.restart();
        try {
            response.data = this.usuarioRolService.guardarUsuarioRol(usuarioRolModel);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response> eliminarUsuarioRolPorId(@PathVariable("id") Long id) {
        response.restart();
        try {
            response.data = this.usuarioRolService.eliminarUsuarioRol(id);
            if (response.data == null) {
                response.message = "El usuario rol no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El usuario rol fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Response> actualizarUsuarioRol(
            @PathVariable(value = "id") Long id,
            @RequestBody UsuarioRolModel usuarioRolModel
    ) {
        response.restart();
        try {
            response.data = this.usuarioRolService.actualizarUsuarioRol(id, usuarioRolModel);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if (exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}