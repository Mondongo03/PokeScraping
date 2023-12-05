package org.openqa.selenium;

import java.util.ArrayList;
/**
 * Esta clase es para crear objetos de tipo pokemon.
 */
public class Pokemon {
    private String numPokedex;
    private String nombre;
    private String typePr;
    private String typeSc;
    private String habilidad;
    private String habilidadHidden;
    private String hp;
    private String atq;
    private String def;
    private String sAtq;
    private String sDef;
    private String vel;


    public String getNumPokedex() {
        return numPokedex;
    }

    public void setNumPokedex(String numPokedex) {
        this.numPokedex = numPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTypePr() {
        return typePr;
    }

    public void setTypePr(String typePr) {
        this.typePr = typePr;
    }

    public String getTypeSc() {
        return typeSc;
    }

    public void setTypeSc(String typeSc) {
        this.typeSc = typeSc;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getHabilidadHidden() {
        return habilidadHidden;
    }

    public void setHabilidadHidden(String habilidadHidden) {
        this.habilidadHidden = habilidadHidden;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAtq() {
        return atq;
    }

    public void setAtq(String atq) {
        this.atq = atq;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getsAtq() {
        return sAtq;
    }

    public void setsAtq(String sAtq) {
        this.sAtq = sAtq;
    }

    public String getsDef() {
        return sDef;
    }

    public void setsDef(String sDef) {
        this.sDef = sDef;
    }

    public String getVel() {
        return vel;
    }

    public void setVel(String vel) {
        this.vel = vel;
    }

    /**
     * Es el constructor de la clase
     * @param numPokedex Número de la pokedex del pokemon
     * @param nombre Pombre del pokemon
     * @param typePr El tipo primario
     * @param typeSc El tipo secundario
     * @param habilidad La habilidad
     * @param habilidadHidden La habilidad oculta
     * @param hp La vida base
     * @param atq El ataque base
     * @param def La defensa base
     * @param sAtq El ataque especial base
     * @param sDef La defensa especial base
     * @param vel La velocidad base
     */
    public Pokemon(String numPokedex, String nombre, String typePr, String typeSc, String habilidad, String habilidadHidden, String hp, String atq, String def, String sAtq, String sDef, String vel) {
        this.numPokedex = numPokedex;
        this.nombre = nombre;
        this.typePr = typePr;
        this.typeSc = typeSc;
        this.habilidad = habilidad;
        this.habilidadHidden = habilidadHidden;
        this.hp = hp;
        this.atq = atq;
        this.def = def;
        this.sAtq = sAtq;
        this.sDef = sDef;
        this.vel = vel;
    }
}
