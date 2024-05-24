package driver

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class ChromiumScraperDriver(
    driverExePath: String,
    chromeExePath: String,
    chromeOptions: ChromeOptions = ChromeOptions()
): ScraperDriver {
    private var driver: ChromeDriver

    init {
        chromeOptions.setHeadless(true)
        chromeOptions.setBinary(chromeExePath)
        System.setProperty("webdriver.chrome.driver",driverExePath)
        driver = ChromeDriver(
            chromeOptions
        )
    }

    override fun loadPage() {
        driver.get(Constants.happyHourPageUri)
    }

    override fun getExecutor(): JavascriptExecutor {
        return driver
    }

    override fun getRoot(): WebElement {
        return driver.findElement(By.id("hhVideos"))
    }

    override fun quitDriver() {
        driver.quit()
    }
}