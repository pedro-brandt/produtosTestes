package com.example.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class TestesDeSistemaWikipedia {

    private ChromeDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// config p rodar localmente
		driver = new ChromeDriver(options);
    }

    //2.1 Busca de Artigo com Termo Válido
    @Test
    public void testBuscaTermoValido() {
       
        driver.get("https://www.wikipedia.org/");

        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("Selenium (software)");

        searchBox.submit();

        WebElement articleHeader = driver.findElement(By.className("mw-page-title-main"));
        assertEquals("Selenium (software)", articleHeader.getText());
    }

    //2.2 Busca de Artigo com Termo Inexistente
    @Test
    public void testBuscaTermoInexistente() {
        driver.get("https://www.wikipedia.org/");

        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("DHASUDHASIDSUIAHDSADHISAHD");

        searchBox.submit();

        WebElement noResultMessage = driver.findElement(By.className("mw-search-nonefound"));
        assertNotNull(noResultMessage);
        assertTrue(noResultMessage.getText().contains("A pesquisa não produziu resultados."));
    }

    @AfterEach
    public void fechar() {
        if (driver != null) {
            driver.quit();
        }
    }
}


