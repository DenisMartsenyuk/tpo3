import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class FunctionsTests {

    private static WebDriver webDriver;
    private static WebDriverWait webDriverWait;

    private final static String URL = "https://stealthex.io";


    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        webDriver = new ChromeDriver();

//        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
//        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    private void logout() {
        webDriver.get("https://partners.stealthex.io/pp/account");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//button[@class='sc-ywFzA iNJWCA']"))).click();
    }

    private void login() {
        logout();
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/header/div/div/nav/ul/li[7]/a"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div/div/div/div/a[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//input[@placeholder='Email']"))).sendKeys("denmarc@bk.ru");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//input[@placeholder='Password']"))).sendKeys("Tpotopsubject2022!");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div/div/div/section/div/div[2]/div/button"))).click();
    }

    @Test
    public void testLogin() {
        login();
        String result = webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div[1]/div/h2"))).getText();
        logout();
        assertEquals("Partner profile", result);
    }

    @RepeatedTest(3)
    public void testLogout() {
        login();
        logout();
        assertEquals("Affiliate Program", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/div/div/header/h1"))).getText());
    }

    @Test
    public void testChatOpen() {
        webDriver.get(URL + "/faq");
        WebElement frameButton = webDriverWait.until(visibilityOfElementLocated(By.xpath("//iframe[@id='launcher']")));
        webDriver.switchTo().frame(frameButton);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("//button[@aria-label='Поддержка']"))).click();
        webDriver.switchTo().defaultContent();

        WebElement frameChat = webDriverWait.until(visibilityOfElementLocated(By.xpath("//iframe[@id='webWidget']")));
        webDriver.switchTo().frame(frameChat);
        assertNotNull(webDriverWait.until(visibilityOfElementLocated(By.xpath("//div[@data-garden-container-id='focusjail']"))));
        webDriver.switchTo().defaultContent();
    }

    @Test
    public void testBlogSearch() {
        webDriver.get(URL + "/blog");
        String title = "How to Convert Bitcoin to Monero Coin? BTC to XMR Exchange";
        WebElement input = webDriverWait.until(visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        input.sendKeys(title);
        input.submit();
        assertEquals(title, webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section/main/div[2]/div/div/h2/a"))).getText());
    }

    @Test
    public void testBlogOpenPost() {
        webDriver.get(URL + "/blog");
        String title = webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[2]/div/div/div[1]/div/div/h2/a"))).getText();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[2]/div/div/div[1]"))).click();
        assertEquals(title, webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div/div[1]/div/h1"))).getText());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    public void testBlogPagination(int page) {
        webDriver.get(URL + "/blog");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[2]/div/div/nav/div/div/ul/li[" + page +"]/a"))).click();
        assertEquals("https://stealthex.io/blog/page/" + page + "/", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testMinimumAmountError() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/input[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/input[1]"))).sendKeys(Keys.ARROW_LEFT);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/input[1]"))).sendKeys("00000");
        assertEquals("The minimum amount", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[2]"))).getText().split(":")[0]);
    }

    @Test
    public void testChangeCurrencyToEth() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/span[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"))).sendKeys("eth");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/ul[1]/li[1]"))).click();
        assertEquals("Send Ethereum", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/label[1]"))).getText());
    }

    @RepeatedTest(5)
    public void testExchangeUserFlowConfirmationAmount() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/input[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/input[1]"))).sendKeys("234");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[3]/div[1]/a[1]/span[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/input[1]"))).sendKeys("0xAAf64Ecc4FcC0995aaE1A4EFBA889d585f69E7EC");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[2]/div[1]/div[4]/div[2]/div[2]/div[1]/button[1]"))).click();
        assertEquals("0.1234", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/p[1]/span[1]"))).getText());
    }

    private void exchangeUserFlow() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/input[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[2]/div[1]/div[1]/div[1]/input[1]"))).sendKeys("234");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/section[1]/div[1]/div[1]/section[1]/div[3]/div[1]/a[1]/span[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[2]/div[1]/div[4]/div[2]/div[1]/div[1]/input[1]"))).sendKeys("0xAAf64Ecc4FcC0995aaE1A4EFBA889d585f69E7EC");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[2]/div[1]/div[4]/div[2]/div[2]/div[1]/button[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[4]/div[2]/div[2]/div[2]/button[1]"))).click();
    }

    @RepeatedTest(5)
    public void testExchangeUserFlow() {
        exchangeUserFlow();
        assertEquals("0.1234", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[1]/div[1]/div[3]/div[2]/section[1]/div[2]/div[1]/div[1]/div[1]/div[1]/p[1]/span[1]/span[1]"))).getText());
    }

    @RepeatedTest(5)
    public void testMyExchangesSearch() {
        exchangeUserFlow();
        String exchangeId = webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[2]/div[1]/p[1]/span[1]"))).getText();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/nav[1]/ul[1]/li[1]/div[1]/button[1]/span[1]"))).click();
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/nav[1]/ul[1]/li[1]/div[1]/div[2]/div[1]/input[1]"))).sendKeys(exchangeId);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[1]/nav[1]/ul[1]/li[1]/div[1]/div[2]/div[2]/ul[1]/li[1]/a[1]/div[2]/div[1]/span[1]"))).click();
        assertEquals(exchangeId, webDriverWait.until(visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/section[1]/div[1]/div[1]/div[2]/div[2]/div[1]/p[1]/span[1]"))).getText());
    }

    @AfterAll
    static void destroy() {
        webDriver.quit();
    }
}
