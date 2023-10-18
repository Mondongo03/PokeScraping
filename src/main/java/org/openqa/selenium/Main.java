package org.openqa.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Main {

  public static void main(String[] args) {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    FirefoxOptions options = new FirefoxOptions();
  
    WebDriver driver = new FirefoxDriver(options);
    driver.get("https://dbz-dokkanbattle.fandom.com/wiki/Active_Skills#Ultimate_Damage");


  }
}
