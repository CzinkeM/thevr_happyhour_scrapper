package driver

import Constants
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxBinary
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import java.io.File

class FirefoxScraperDriver(
    driverExePath: String,
    firefoxExePath: String,
    firefoxOptions: FirefoxOptions = FirefoxOptions()
): ScraperDriver {
    private var driver: FirefoxDriver

    init {
        firefoxOptions.setHeadless(true)
        firefoxOptions.binary = FirefoxBinary(File(firefoxExePath))
        System.setProperty("webdriver.gecko.driver",driverExePath)
        driver = FirefoxDriver(
            firefoxOptions
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