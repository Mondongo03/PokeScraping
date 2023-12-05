package org.openqa.selenium;
/**
 * Esta clase es para crear objetos de tipo movimiento.
 */
public class Movimiento {

    public String nombre;
    public String tipo;
    public String categoria;
    public String poder;
    public String pp;
    public String precision;
    public String descripcion;

    /**
     * Es el constructor de la clase
     * @param nombre Nombre del movimiento
     * @param tipo Tipo
     * @param categoria Si el movimiento es de estado, físico o especial
     * @param poder La potencia del movimiento, si es de tipo estado sera nula
     * @param pp La cantidad de usos máximos del movimiento
     * @param precision El porcentaje de acierto del movimiento
     * @param descripcion Descripción de que hace
     */
    public Movimiento(String nombre, String tipo, String categoria, String poder, String pp, String precision, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.categoria = categoria;
        this.poder = poder;
        this.pp = pp;
        this.precision = precision;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
