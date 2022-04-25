import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class OpenPagesTests {
    private static WebDriver webDriver;
    private static WebDriverWait webDriverWait;

    private final static String URL = "https://stealthex.io";


    @BeforeAll
    static void setup() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); //chrome
        webDriver = new ChromeDriver();

//        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
//        webDriver = new FirefoxDriver();
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    @RepeatedTest(5)
    public void testFaqOpen() {
        webDriver.get(URL + "/faq");
        assertEquals("FAQ", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/section/div[1]/div/h1"))).getText());
    }

    @RepeatedTest(5)
    public void testHowItWorksOpen() {
        webDriver.get(URL + "/how-it-works");
        assertEquals("How it works", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div/h1"))).getText());
    }

    @RepeatedTest(5)
    public void testAboutOpen() {
        webDriver.get(URL + "/about");
        assertEquals("About", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/section/div[1]/h1"))).getText());
    }

    @RepeatedTest(5)
    public void testBlogOpen() {
        webDriver.get(URL + "/blog");
        assertEquals("Learn about crypto with StealthEX", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/main/section[1]/div/div/div[1]/h1"))).getText());
    }

    @RepeatedTest(5)
    public void testBlogOpenAll() {
        webDriver.get(URL + "/blog/?cat=crypto-blockchain");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[1]/div/div/div[2]/a[1]"))).click();
        assertEquals("https://stealthex.io/blog/?cat=all", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testBlogOpenCrypto() {
        webDriver.get(URL + "/blog");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[1]/div/div/div[2]/a[2]"))).click();
        assertEquals("https://stealthex.io/blog/?cat=crypto-blockchain", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testBlogOpenHow() {
        webDriver.get(URL + "/blog");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[1]/div/div/div[2]/a[3]"))).click();
        assertEquals("https://stealthex.io/blog/?cat=how-to-buy", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testBlogOpenInvest() {
        webDriver.get(URL + "/blog");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[1]/div/div/div[2]/a[4]"))).click();
        assertEquals("https://stealthex.io/blog/?cat=investment", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testBlogOpenMining() {
        webDriver.get(URL + "/blog");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[1]/div/div/div[2]/a[5]"))).click();
        assertEquals("https://stealthex.io/blog/?cat=mining", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testBlogOpenNews() {
        webDriver.get(URL + "/blog");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[1]/div/div/div[2]/a[6]"))).click();
        assertEquals("https://stealthex.io/blog/?cat=our-news", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testBlogOpenPrice() {
        webDriver.get(URL + "/blog");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[1]/div/div/div[2]/a[7]"))).click();
        assertEquals("https://stealthex.io/blog/?cat=price-prediction", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testBlogOpenWallets() {
        webDriver.get(URL + "/blog");
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/main/section[1]/div/div/div[2]/a[8]"))).click();
        assertEquals("https://stealthex.io/blog/?cat=wallets", webDriver.getCurrentUrl());
    }

    @Test
    public void testFooterOpenChannel() {
        webDriver.get(URL);
        assertEquals("https://t.me/StealthEX", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/div/div/div[1]/a[1]"))).getAttribute("href"));
    }

    @Test
    public void testFooterOpenChat() {
        webDriver.get(URL);
        assertEquals("https://t.me/StealthEX_io", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/div/div/div[1]/a[2]"))).getAttribute("href"));
    }

    @Test
    public void testFooterOpenTwitter() {
        webDriver.get(URL);
        assertEquals("https://twitter.com/StealthEX_io", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/div/div/div[2]/a[1]"))).getAttribute("href"));
    }

    @Test
    public void testFooterOpenReddit() {
        webDriver.get(URL);
        assertEquals("https://www.reddit.com/user/Stealthex_io", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/div/div/div[2]/a[2]"))).getAttribute("href"));
    }

    @Test
    public void testFooterOpenYouTube() {
        webDriver.get(URL);
        assertEquals("https://www.youtube.com/channel/UCeES_XBesX76ge7xf1meuSw", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/div/div/div[2]/a[3]"))).getAttribute("href"));
    }

    @Test
    public void testFooterOpenMedium() {
        webDriver.get(URL);
        assertEquals("https://stealthex-io.medium.com/", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/div/div/div[2]/a[4]"))).getAttribute("href"));
    }

    @RepeatedTest(5)
    public void testFooterOpenHow() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[1]/div[2]/ul/li[1]/a"))).click();
        assertEquals("https://stealthex.io/how-it-works", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenAbout() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[1]/div[2]/ul/li[2]/a"))).click();
        assertEquals("https://stealthex.io/about", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenContacts() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[1]/div[2]/ul/li[3]/a"))).click();
        assertEquals("https://stealthex.io/contacts", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenFAQ() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[1]/div[2]/ul/li[4]/a"))).click();
        assertEquals("https://stealthex.io/faq", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenBlog() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[1]/div[2]/ul/li[5]/a"))).click();
        assertEquals("https://stealthex.io/blog/", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenAPI() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div/footer/div/div[1]/nav/section[2]/div[2]/ul/li[1]/a"))).click();
        assertEquals("https://documenter.getpostman.com/view/11320959/T17J8mzw?version=latest", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenAffiliate() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[2]/div[2]/ul/li[2]/a"))).click();
        assertEquals("https://stealthex.io/affiliate-program", webDriver.getCurrentUrl());
    }

    @Test
    public void testFooterDownloadBrand() {
        webDriver.get(URL);
        assertEquals("https://stealthex.io/StealthEX_Brand_Assets.zip", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[2]/div[2]/ul/li[3]/a"))).getAttribute("href"));
    }

    @Test
    public void testFooterDownloadWhitepaper() {
        webDriver.get(URL);
        assertEquals("https://stealthex.io/StealthEX_Bitcoin_Whitepaper.pdf", webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[2]/div[2]/ul/li[4]/a"))).getAttribute("href"));
    }

    @RepeatedTest(5)
    public void testFooterOpenBTC() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[3]/div[2]/ul/li[1]/ul[1]/li/a"))).click();
        assertEquals("https://stealthex.io/coin/bitcoin", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenETH() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[3]/div[2]/ul/li[1]/ul[2]/li/a"))).click();
        assertEquals("https://stealthex.io/coin/ethereum", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenXMR() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[3]/div[2]/ul/li[1]/ul[3]/li/a"))).click();
        assertEquals("https://stealthex.io/coin/monero", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenZEC() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[3]/div[2]/ul/li[1]/ul[4]/li/a"))).click();
        assertEquals("https://stealthex.io/coin/zcash", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenBSV() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[3]/div[2]/ul/li[2]/ul[1]/li/a"))).click();
        assertEquals("https://stealthex.io/coin/bitcoin%20sv", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenZEN() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[3]/div[2]/ul/li[2]/ul[2]/li/a"))).click();
        assertEquals("https://stealthex.io/coin/horizen", webDriver.getCurrentUrl());
    }

    @RepeatedTest(5)
    public void testFooterOpenCoins() {
        webDriver.get(URL);
        webDriverWait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/footer/div/div[1]/nav/section[3]/div[2]/ul/li[2]/a"))).click();
        assertEquals("https://stealthex.io/coin", webDriver.getCurrentUrl());
    }

    @AfterAll
    static void destroy() {
        webDriver.quit();
    }
}
