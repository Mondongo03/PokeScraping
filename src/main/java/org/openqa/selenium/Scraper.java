package org.openqa.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.Scanner;

public class Scraper {

    private Pokedex pokedex;

    public void hurtarPoke(){
        pokedex = new Pokedex();
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.pokexperto.net/index2.php?seccion=nds/nationaldex/buscar");


        WebElement tabla = driver.findElement(By.id("listapokemon"));
        List<WebElement> pokemons = tabla.findElements(By.className("nav5c"));


        int contador = 0;
        int contadorStat = 1;
        String arrayHabilidades[] = new String[3];

        String num, nombre, tipo1, tipo2, habilidad , hp, atq, def, vel, sAtq, sDef;
        String habilidadHidden = "";
        habilidad = "";
        for (int i = 0; i < pokemons.size(); i++) {
            driver.get("https://www.pokexperto.net/index2.php?seccion=nds/nationaldex/pkmn&pk=" + (i + 1));
            WebElement nums = driver.findElement(By.className("amarillo"));
            num = nums.getText();
            WebElement nombres = driver.findElement(By.className("mini"));
            nombre = nombres.getText();
            WebElement tipo = driver.findElement(By.className("bordeambos"));
            List<WebElement> tipos = tipo.findElements(By.tagName("img"));

            if (tipos.size() == 2) {
                tipo1 = tipos.get(0).getAttribute("alt");
                tipo2 = tipos.get(1).getAttribute("alt");
            } else {
                tipo1 = tipos.get(0).getAttribute("alt");
                tipo2 = "";
            }
            WebElement tablaHabilidades = driver.findElement(By.className("pkmain"));
            List<WebElement> filas = tablaHabilidades.findElements(By.tagName("td"));
            contador = 0;
            for (WebElement fila : filas) {
                List<WebElement> strongs = fila.findElements(By.tagName("strong"));

                for (WebElement strong : strongs) {
                    List<WebElement> aInStrong = strong.findElements(By.tagName("a"));
                    for (WebElement elementoA : aInStrong) {
                        arrayHabilidades[contador] = elementoA.getText();
                        contador++;
                    }

                    if (contador == 3) {
                        habilidad = arrayHabilidades[0] + "/" + arrayHabilidades[1];
                        habilidadHidden = arrayHabilidades[2];

                    } else {
                        habilidad = arrayHabilidades[0];
                        habilidadHidden = arrayHabilidades[1];

                    }
                    //contador = 0;
                }
            }
            if(num.equals("094")) {
                habilidad= habilidadHidden;
                habilidadHidden = "";
            }
            if(num.equals("145")) {
                habilidad = "Presión";
            }
            if(num.equals("243") || num.equals("244")||num.equals("245")){
                habilidad = "Presión";
                habilidadHidden = "Foco Interno";
            }

            driver.get("https://www.pokexperto.net/index2.php?seccion=nds/nationaldex/stats&pk="+(i+1));

            List<WebElement> tablas = driver.findElements(By.tagName("table"));
            WebElement content = tablas.get(11);
            Scanner input = new Scanner(content.getText());
            input.nextLine();
            input.next();
            hp = input.next();
            input.nextLine();
            input.next();
            atq = input.next();
            input.nextLine();
            input.next();
            def = input.next();
            input.nextLine();
            input.next();
            vel = input.next();
            input.nextLine();
            input.next();
            sAtq = input.next();
            input.nextLine();
            input.next();
            sDef = input.next();

            Pokemon pokemon = new Pokemon(num, nombre, tipo1, tipo2, habilidad, habilidadHidden , hp, atq, def, vel, sAtq, sDef);
            pokedex.pokedex.add(i, pokemon);
        }

    }

    public Pokedex getPokedex() {
        return this.pokedex;
    }
}
