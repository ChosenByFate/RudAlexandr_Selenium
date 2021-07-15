import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) {
        // 1.
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://avito.ru");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 2.
        By by = By.id("category");
        Select categories = new Select(driver.findElement(by));
        categories.selectByVisibleText("Оргтехника и расходники");

        // 3.
        by = By.xpath("//input[@data-marker=\"search-form/suggest\"]");
        WebElement search = driver.findElement(by);
        search.sendKeys("Принтер");

        // 4.
        by = By.xpath("//div[@data-marker=\"search-form/region\"]");
        WebElement city = driver.findElement(by);
        city.click();

        // 5.
        by = By.xpath("//input[@placeholder=\"Город, регион или Россия\"]");
        WebElement cityFind = driver.findElement(by);
        cityFind.sendKeys("Владивосток");

        by = By.xpath("//strong");
        List<WebElement> citySelect = driver.findElements(by);
        citySelect.get(0).click();

        by = By.xpath("//button[@data-marker=\"popup-location/save-button\"]");
        WebElement btnFind = driver.findElement(by);
        btnFind.click();

        // 6.
        by = By.xpath("//label[@data-marker=\"delivery-filter\"]/input[@type=\"checkbox\"]");
        WebElement chkBox = driver.findElement(by);
        by = By.xpath("//button[@data-marker=\"search-filters/submit-button\"]");
        if (!chkBox.isSelected() && chkBox.isEnabled()) {
            chkBox.sendKeys(Keys.SPACE);
            WebElement btnFind2 = driver.findElement(by);
            btnFind2.click();
        }

        // 7.
        by = By.cssSelector("[class^=\"sort-select\"]>[class^=\"select-select\"]");
        Select price = new Select(driver.findElement(by));
        price.selectByIndex(2);

        // 8.
        by = By.xpath("//div[@data-marker=\"item\"]");
        List<WebElement> searchResults = driver.findElements(by);
        for (int i = 0; i < 3; ++i) {
            by = By.cssSelector("[data-marker^=\"item-title\"]");
            WebElement printerName = searchResults.get(i).findElement(by);
            System.out.println("Printer: " + printerName.getText());
            by = By.cssSelector("[class^=\"price-text-\"]");
            WebElement printerPrice = searchResults.get(i).findElement(by);
            System.out.println("Price: " + printerPrice.getText());
        }

        driver.quit();
    }
}
