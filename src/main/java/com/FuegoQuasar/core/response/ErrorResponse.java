package com.FuegoQuasar.core.response;

public class ErrorResponse {
    private Integer codigoError;
    private String descripcionError;

    public Integer getCodigoError() {
	return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
	this.codigoError = codigoError;
    }

    public String getDescripcionError() {
	return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
	this.descripcionError = descripcionError;
    }

}
