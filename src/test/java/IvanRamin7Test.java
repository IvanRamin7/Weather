import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IvanRamin7Test {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        //arrange
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        //act
        driver.get(url);
        Thread.sleep(5000);
        WebElement searchCity = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']")
        );

        searchCity.click();
        searchCity.sendKeys(cityName);
        WebElement searchButton = driver.findElement(
                By.xpath("//button[@type = 'submit']")
        );
        searchButton.click();
        Thread.sleep(3000);
        WebElement parisFRChoiceFromDropdownMenu = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//ul[@class = 'search-dropdown-menu']//span[text() = 'Paris, FR ']")
        );
        parisFRChoiceFromDropdownMenu.click();
        Thread.sleep(3000);
        WebElement h2CityNameHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2")
        );
        String actualResult = h2CityNameHeader.getText();

        //assert
        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }


    /**
     * TC_11_01
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
     * и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */

    @Test
    public void testConfirmGuidePage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String urlGuidePageExpectedResult = "https://openweathermap.org/guide";
        String titleGuideExpectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);
        WebElement guideDesktop = driver.findElement(
                By.xpath("//nav[@id = 'nav-website']//div[@id = 'desktop-menu']//a[@href = '/guide']")
        );
        guideDesktop.click();
        String urlGuidePageActualResult = driver.getCurrentUrl();
        String titleGuideActualResult = driver.getTitle();

        Assert.assertEquals(urlGuidePageActualResult, urlGuidePageExpectedResult);
        Assert.assertEquals(titleGuideActualResult, titleGuideExpectedResult);
        driver.quit();
    }

    /**
     * TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */

    @Test
    public void testConfirmFarenheits() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String fahrenheitExpectedResult = "°F";

        driver.get(url);
        Thread.sleep(5000);
        WebElement fahrenheitButton = driver.findElement(
                By.xpath("//div[@class='page-container']//div[text() = 'Imperial: °F, mph']")
        );
        fahrenheitButton.click();
        Thread.sleep(2000);
        WebElement celciusFahrenheits = driver.findElement(
                By.xpath("//div[@class='section-content']//span[@class = 'heading']")
        );
        boolean actualResult = celciusFahrenheits.getText().contains(fahrenheitExpectedResult);
        Assert.assertTrue(actualResult);
        driver.quit();
    }

    /**
     * TC_11_03
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
     * We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
     * You can allow all cookies or manage them individually.”
     * 3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */

    @Test
    public void testCookies() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String textExpectedResult = "We use cookies which are essential for the site to work." +
                " We also use non-essential cookies to help us improve our services. Any data collected is anonymised." +
                " You can allow all cookies or manage them individually.";
        String allowAllButtonExpectedResult = "Allow all";
        String manageCookiesButtonExpectedResult = "Manage cookies";

        driver.get(url);
        Thread.sleep(5000);
        WebElement cookieText = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel']//p[@class = 'stick-footer-panel__description']")
        );
        String textActualResult = cookieText.getText();
        WebElement allowAllButton = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel']//button")
        );
        String allowAllButtonActualResult = allowAllButton.getText();
        WebElement manageCookiesButton = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel']//a[@rel = 'noopener noreferrer']")
        );
        String manageAllActualResult = manageCookiesButton.getText();

        Assert.assertEquals(textActualResult, textExpectedResult);
        Assert.assertEquals(allowAllButtonActualResult, allowAllButtonExpectedResult);
        Assert.assertEquals(manageAllActualResult, manageCookiesButtonExpectedResult);
        driver.quit();
    }

    /**
     *TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”
     */
    @Test
    public void testSupportDropDown() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("FAQ", "How to start", "Ask a question"));

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement supportButton = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']")
        );
        supportButton.click();
        Thread.sleep(1000);
        WebElement faqFromDropdown = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']//a[@href='/faq']")
        );
        WebElement howToStartFromDropdown = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = '/appid']")
        );
        WebElement askQuestionFromDropdown = driver.findElement(
                By.xpath("//ul[@class = 'dropdown-menu dropdown-visible']/li/a[@href = 'https://home.openweathermap.org/questions']")

        );
        ArrayList<String> actualResult = new ArrayList<>(Arrays.asList(faqFromDropdown.getText(),
                howToStartFromDropdown.getText(), askQuestionFromDropdown.getText()));

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }



    /**
     * TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */
    //poka ne sdelal
    @Test
    public void testErrorHandlingCAPTCHA() {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
    }



}





