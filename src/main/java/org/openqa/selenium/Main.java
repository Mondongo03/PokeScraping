package org.openqa.selenium;

import javax.xml.transform.TransformerException;

public class Main {
    public static void main(String[] args) throws TransformerException {
        Scraper scraper = new Scraper();
        //scraper.hurtarObjetos();
        //Bolsa bolsa = scraper.getBolsa();
        scraper.hurtarPoke();
        Pokedex pokedex = scraper.getPokedex();
        scraper.generarXmlPokemon();
        //scraper.generarXmlObjetos();

            }
        }