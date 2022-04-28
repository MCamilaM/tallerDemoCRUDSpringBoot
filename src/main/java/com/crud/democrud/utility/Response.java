package com.crud.democrud.utility;

/**
 * Clase para el manejo de las respuestas de las API
 *
 * @author Julian Lasso <julian.lasso@sofka.com.co>
 * @version 1.0.0 2022-03-20
 * @since 1.0.0
 */
public class Response {

    public Boolean error;
    public String message;
    public Object data;


    /**
     * Constructor de la clase
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    public Response() {
        error = false;
        message = "";
        data = null;
    }

    /**
     * Restaura a ceros la respuesta del API
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    public void restart() {
        error = false;
        message = "";
        data = null;
    }
}

