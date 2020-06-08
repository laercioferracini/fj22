package br.com.ferracini.argentum.aceitacao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author lferracini
 * @project = fj22
 * @since <pre>07/06/2020</pre>
 */
public class GeraGraficoTest {

    private static final String URL = "http://localhost:8080/fj22-argentum-web-1.0-SNAPSHOT/index.xhtml";

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void setDriver() {

        String os = System.getProperty("os.name").toUpperCase();
        String fileSeparator = System.getProperty("file.separator");
        String driverFile = "driver".concat(fileSeparator);

        if (os.contains("LINUX")) {
            driverFile = driverFile.concat("geckodriver");
        } else if (os.contains("WINDOWS")) {
            driverFile = driverFile.concat("geckodriver.exe");
        }

        System.setProperty("webdriver.gecko.driver", driverFile);
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    @DisabledIfSystemProperty(named = "aceitacao", matches = "false")
    void testeAoGerarGraficoSemTituloUmaMensagemEhApresentada() throws InterruptedException {

        driver.navigate().to(URL);
        WebElement webElement = driver.findElement(By.id("dadosGrafico:titulo"));

        webElement.sendKeys("");
        webElement.submit();
        Thread.sleep(2000);
        boolean existeMensagem = driver.getPageSource().contains("Erro de validação");
        assertTrue(existeMensagem);
    }

    @Test
    @DisabledIfSystemProperty(named = "aceitacao", matches = "false")
    void testeGerarGraficoComTitulo() throws InterruptedException {
        driver.navigate().to(URL);
        WebElement webElement = driver.findElement(By.id("dadosGrafico:titulo"));
        webElement.sendKeys("GraficoMediaSimples");
        driver.findElement(By.id("dadosGrafico:gerarGrafico")).click();
        webElement.submit();
        assertTrue(driver.getPageSource().contains("Gráficos"));
    }

}
