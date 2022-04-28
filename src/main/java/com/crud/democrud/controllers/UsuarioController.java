package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.services.UsuarioService;
import com.crud.democrud.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;


@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    private Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping()
    public ResponseEntity<Response> obtenerUsuarios() {
        response.restart();
        try {
            response.data = this.usuarioService.obtenerUsuarios();
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
            response.data = this.usuarioService.obtenerPorId(id);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    @PostMapping()
    public ResponseEntity<Response> guardarUsuario(@RequestBody UsuarioModel usuario) {
        response.restart();
        try {
            response.data = this.usuarioService.guardarUsuario(usuario);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad) {
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response> eliminarPorId(@PathVariable("id") Long id) {
        response.restart();
        try {
            response.data = this.usuarioService.eliminarUsuario(id);
            if (response.data == null) {
                response.message = "El usuario no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "El usuario fue removido exitosamente";
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
    public ResponseEntity<Response> actualizarUsuario(
            @PathVariable(value = "id") Long id,
            @RequestBody UsuarioModel usuarioModel
    ) {
        response.restart();
        try {
            response.data = this.usuarioService.actualizarUsuario(id, usuarioModel);
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