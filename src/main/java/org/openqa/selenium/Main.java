package org.openqa.selenium;

import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TransformerException, IOException {
        Scraper scraper = new Scraper();
        scraper.hurtarObjetos();
        Bolsa bolsa = scraper.getBolsa();
        scraper.hurtarMoves();
        scraper.hurtarPoke();
        Pokedex pokedex = scraper.getPokedex();
        scraper.generarXmlPokemon();
        //scraper.generarXmlObjetos();
        //scraper.generarXmlMovimientos();
        scraper.generarCsvPokemon();
        //scraper.generarCsvObjeto();
        //scraper.generarCsvMovimiento();
            }
        }