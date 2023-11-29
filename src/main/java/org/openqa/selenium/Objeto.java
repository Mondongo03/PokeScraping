package org.openqa.selenium;

public class Objeto {
        private String nombre;
        private String precio_compra;
        private String precio_venta;
        private String generacion;

        private String tipo;

    public Objeto(String nombre, String generacion, String precio_compra, String precio_venta, String tipo) {
        this.nombre = nombre;
        this.generacion = generacion;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.tipo = tipo;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(String precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(String precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getGeneracion() {
        return generacion;
    }

    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }
}
