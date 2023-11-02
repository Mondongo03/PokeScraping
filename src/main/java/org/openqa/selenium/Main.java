package org.openqa.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scraper scraper = new Scraper();
        scraper.hurtarPoke();
        Pokedex pokedex = scraper.getPokedex();

    }
}