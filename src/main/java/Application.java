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
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://avito.ru");
//        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 2.
        Select categories = new Select(driver.findElement(By.id("category")));
        categories.selectByVisibleText("Оргтехника и расходники");

        // 3.
        WebElement search = driver.findElement(By.xpath("//input[@data-marker=\"search-form/suggest\"]"));
        search.sendKeys("Принтер");

        // 4.
        WebElement sity = driver.findElement(By.xpath("//div[@data-marker=\"search-form/region\"]"));
        sity.click();

        // 5.
        WebElement sityFind = driver.findElement(By.xpath("//input[@placeholder=\"Город, регион или Россия\"]"));
        sityFind.sendKeys("Владивосток");

        List<WebElement> sitySelect = driver.findElements(By.xpath("//strong"));
        sitySelect.get(0).click();

        WebElement btnFind = driver.findElement(By.xpath("//button[@data-marker=\"popup-location/save-button\"]"));
        btnFind.click();

        // 6.
        WebElement chkBox = driver.findElement(By.xpath("//label[@data-marker=\"delivery-filter\"]" +
                "/input[@type=\"checkbox\"]"));
        if (!chkBox.isSelected() && chkBox.isEnabled()) {
            chkBox.sendKeys(Keys.SPACE);
            WebElement btnFind2 = driver.findElement(By.xpath("//button[@data-marker=\"search-filters/submit-button\"]"));
            btnFind2.click();
        }

        // 7.
        Select price = new Select(driver.findElement(By.cssSelector("[class^=\"sort-select\"]>[class^=\"select-select\"]")));
        price.selectByIndex(2);

        // 8.
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@data-marker=\"item\"]"));
        for (int i = 0; i < 3; ++i) {
            WebElement printerName = searchResults.get(i).findElement(By.cssSelector("[data-marker^=\"item-title\"]"));
            System.out.println("Printer: " + printerName.getText());
            WebElement printerPrice = searchResults.get(i).findElement(By.cssSelector("[class^=\"price-text-\"]"));
            System.out.println("Price: " + printerPrice.getText());
        }

        driver.quit();
    }
}
