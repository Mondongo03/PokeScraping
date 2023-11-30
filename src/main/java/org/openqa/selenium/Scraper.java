package org.openqa.selenium;

import com.opencsv.*;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Thread.sleep;

public class Scraper {

    private Pokedex pokedex;

    private Bolsa bolsa;

    private Movedex movedex;

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
                        try {
                            arrayHabilidades[contador] = elementoA.getText();
                            contador++;
                        }catch (Exception e){}
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
        try {
            pokedex.pokedex.add(i, pokemon);
        }catch (Exception e){}
        }

    }

    public void hurtarObjetos(){
        bolsa = new Bolsa();
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.pokencyclopedia.info/es/index.php?id=itemdex");

        WebElement divConLosObjetos = driver.findElement(By.id("incl_div"));
        
        List<WebElement> objetos = divConLosObjetos.findElements(By.tagName("a"));

        ArrayList<String> nombres = new ArrayList<String>();

        String generacion="";
        String precioCompra = "";
        String precioVenta = "";
        String tipo2 = "";

        for (int i = 0; i < objetos.size(); i++) {

            //if (!objetos.get(i).getText().equals("Frag. Meteorito")) {
                nombres.add(objetos.get(i).getText());

        }

        for (int i = 0; i < nombres.size(); i++) {

            try {
                driver.get("https://pokemon.fandom.com/es/wiki/"+nombres.get(i));
                sleep(1000);
            WebElement divElemento = driver.findElement(By.className("NN0_TB_DIsNmMHgJWgT7U"));

            divElemento.click();
                }catch (Exception e){}
            try {
                sleep(2000);
                WebElement bloqueInfo = driver.findElement(By.tagName("aside"));

                List<WebElement> div = bloqueInfo.findElements(By.className("pi-data-value"));
                WebElement tipoO = bloqueInfo.findElement(By.className("enlacesblancos"));
                WebElement tipo1 = tipoO.findElement(By.tagName("a"));
                tipo2= tipo1.getAttribute("title");



                WebElement divv = div.get(2);

                    WebElement compra = div.get(3);
                    precioCompra = compra.getText();
                    precioVenta = div.get(4).getText();

                    WebElement gen = divv.findElement(By.tagName("span"));

                    generacion = gen.getText();


            }catch (Exception e){}

            Objeto objeto = new Objeto(nombres.get(i), generacion, precioCompra, precioVenta, tipo2);
            bolsa.bolsa.add(i, objeto);
        }


    }

    public  void hurtarMoves(){
        movedex = new Movedex();
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.pokexperto.net/index2.php?seccion=nds/movimientos_pokemon");

        List<WebElement> filas = driver.findElements(By.className("check3"));
        String nombre;
        String tipo;
        String categoria;
        String poder;
        String pp;
        String precision;
        String descripcion;
        Scanner scanner;
        for (int i = 0; i < filas.size(); i++) {
          List <WebElement> td = filas.get(i).findElements(By.tagName("td"));
           nombre = td.get(0).findElement(By.tagName("a")).getText();
           tipo = td.get(1).getAttribute("sorttable_customkey");
           categoria = td.get(2).getAttribute("sorttable_customkey");
           poder = String.valueOf(td.get(3).getText());
           scanner = new Scanner(poder);
            String poder2 = "";
           try {
               poder2 = String.valueOf(scanner.nextInt());
           }catch (Exception e){}
           pp = td.get(4).getText();
           precision = td.get(5).getText();
           descripcion = td.get(6).getText();
           Movimiento movimiento = new Movimiento(nombre, tipo, categoria, poder2, pp, precision, descripcion);
           movedex.movedex.add(i, movimiento);
        }
    }

    public void generarXmlPokemon() throws TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try {

            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {

            throw new RuntimeException(e);
        }

        Document document = dBuilder.newDocument();

        Node rootNode = document.createElement("Pokemons");
        document.appendChild(rootNode);

        for (Pokemon pokemon : pokedex.pokedex) {

            Node pokes = document.createElement("Pokemon");
            rootNode.appendChild(pokes);

            Node nombrePoke = document.createElement("Nombre");
            nombrePoke.appendChild(document.createTextNode(pokemon.getNombre()));
            pokes.appendChild(nombrePoke);

            Node numPkdex = document.createElement("Número");
            numPkdex.appendChild(document.createTextNode(pokemon.getNumPokedex()));
            pokes.appendChild(numPkdex);

            Node tipo1 = document.createElement("Tipo1");
            tipo1.appendChild(document.createTextNode(pokemon.getTypePr()));
            pokes.appendChild(tipo1);

            Node tipo2 = document.createElement("Tipo2");
            tipo2.appendChild(document.createTextNode(pokemon.getTypePr()));
            pokes.appendChild(tipo2);

            Node habilidad = document.createElement("Habilidad");
            habilidad.appendChild(document.createTextNode(pokemon.getHabilidad()));
            pokes.appendChild(habilidad);

            Node habilidadHidden = document.createElement("Habilidad_Oculta");
            habilidadHidden.appendChild(document.createTextNode(pokemon.getHabilidadHidden()));
            pokes.appendChild(habilidadHidden);

            Node hp = document.createElement("Hp");
            hp.appendChild(document.createTextNode(pokemon.getHp()));
            pokes.appendChild(hp);

            Node atq = document.createElement("Ataque");
            atq.appendChild(document.createTextNode(pokemon.getAtq()));
            pokes.appendChild(atq);

            Node def = document.createElement("Defensa");
            def.appendChild(document.createTextNode(pokemon.getDef()));
            pokes.appendChild(def);

            Node sAtq = document.createElement("Ataque_Especial");
            sAtq.appendChild(document.createTextNode(pokemon.getsAtq()));
            pokes.appendChild(sAtq);

            Node sDef = document.createElement("Defensa_Especial");
            sDef.appendChild(document.createTextNode(pokemon.getsDef()));
            pokes.appendChild(sDef);

            Node vel = document.createElement("Velocidad");
            vel.appendChild(document.createTextNode(pokemon.getVel()));
            pokes.appendChild(vel);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("./Pokemon.xml"));
            transformer.transform(source, result);
        }
    }

    public void generarXmlObjetos() throws TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = dBuilder.newDocument();

        Node rootNode = document.createElement("Objetos");
        document.appendChild(rootNode);

        for (Objeto objeto : bolsa.bolsa) {
            Node pokes = document.createElement("Objeto");
            rootNode.appendChild(pokes);

            Node nombreObjeto = document.createElement("Nombre");
            nombreObjeto.appendChild(document.createTextNode(objeto.getNombre()));
            pokes.appendChild(nombreObjeto);

            Node descObjeto = document.createElement("Generación");
            descObjeto.appendChild(document.createTextNode(objeto.getGeneracion()));
            pokes.appendChild(descObjeto);

            Node precioCompra = document.createElement("Precio_Compra");
            precioCompra.appendChild(document.createTextNode(objeto.getPrecio_compra()));
            pokes.appendChild(precioCompra);

            Node precioVenta = document.createElement("Precio_Venta");
            precioVenta.appendChild(document.createTextNode(objeto.getPrecio_venta()));
            pokes.appendChild(precioVenta);

            Node habilidad = document.createElement("Tipo");
            habilidad.appendChild(document.createTextNode(objeto.getTipo()));
            pokes.appendChild(habilidad);



            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("./Objeto.xml"));
            transformer.transform(source, result);
        }
    }

    public void generarXmlMovimientos() throws TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document document = dBuilder.newDocument();

        Node rootNode = document.createElement("Movimientos");
        document.appendChild(rootNode);

        for (Movimiento movimiento : movedex.movedex) {
            Node moves = document.createElement("Movimiento");
            rootNode.appendChild(moves);

            Node nombreMove = document.createElement("Nombre");
            nombreMove.appendChild(document.createTextNode(movimiento.getNombre()));
            moves.appendChild(nombreMove);

            Node typeMove = document.createElement("Tipo");
            typeMove.appendChild(document.createTextNode(movimiento.getTipo()));
            moves.appendChild(typeMove);

            Node catMove = document.createElement("Categoria");
            catMove.appendChild(document.createTextNode(movimiento.getCategoria()));
            moves.appendChild(catMove);

            Node poderMove = document.createElement("Poder");
            poderMove.appendChild(document.createTextNode(movimiento.getPoder()));
            moves.appendChild(poderMove);

            Node ppMove = document.createElement("PP");
            ppMove.appendChild(document.createTextNode(movimiento.getPp()));
            moves.appendChild(ppMove);

            Node precisionMove = document.createElement("Precisión");
            precisionMove.appendChild(document.createTextNode(movimiento.getPrecision()));
            moves.appendChild(precisionMove);

            Node descMove = document.createElement("Descripción");
            descMove.appendChild(document.createTextNode(movimiento.getDescripcion()));
            moves.appendChild(descMove);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("./Movimiento.xml"));
            transformer.transform(source, result);
        }
    }

    public void generarCsvPokemon() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("./Pokemon.csv"));
        List<String[]> rows = new LinkedList<String[]>();
        for (Pokemon pokemon : pokedex.pokedex) {
                rows.add(new String[]{pokemon.getNombre(), pokemon.getNumPokedex(), pokemon.getTypePr(),pokemon.getTypeSc(),pokemon.getHabilidad(), pokemon.getHabilidadHidden(), pokemon.getHp(), pokemon.getAtq(), pokemon.getDef(),pokemon.getsAtq(),pokemon.getsDef(),pokemon.getVel()});
            }
        writer.writeAll(rows);
        writer.close();
        }

    public void generarCsvObjeto() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("./Objeto.csv"));
        List<String[]> rows = new LinkedList<String[]>();
        for (Objeto objeto : bolsa.bolsa) {
            rows.add(new String[]{objeto.getNombre(), objeto.getGeneracion(), objeto.getPrecio_compra(), objeto.getPrecio_venta(), objeto.getTipo()});
        }
        writer.writeAll(rows);
        writer.close();
    }

    public void generarCsvMovimiento() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("./Movimiento.csv"));
        List<String[]> rows = new LinkedList<String[]>();
        for (Movimiento movimiento : movedex.movedex) {
            rows.add(new String[]{movimiento.getNombre(), movimiento.getTipo(), movimiento.getCategoria(), movimiento.getPoder(), movimiento.getPp(), movimiento.getPrecision(), movimiento.getDescripcion()});
        }
        writer.writeAll(rows);
        writer.close();
    }
    public Pokedex getPokedex() {
        return this.pokedex;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }
}
