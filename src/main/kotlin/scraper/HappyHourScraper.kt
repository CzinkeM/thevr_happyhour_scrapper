package scraper

import driver.ScraperDriver
import kotlinx.coroutines.delay
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import kotlin.system.measureTimeMillis

abstract class HappyHourScraper(
    protected val scraperDriver: ScraperDriver
): IHappyHourScrapper {
    private lateinit var javascriptExecutor: JavascriptExecutor

    var lastProcessingTime = 0L

    override suspend fun processWebpage(): List<HappyHourDto> {
        scraperDriver.loadPage()
        javascriptExecutor = scraperDriver.getExecutor()
        processDynamicallyLoadingPage(javascriptExecutor)
        val root = scraperDriver.getRoot()
        return root.findElements(By.cssSelector(".card"))
            .map { webElement ->
                webElement.mapToHappyHour()
            }
    }

    private suspend fun processDynamicallyLoadingPage(javascriptExecutor: JavascriptExecutor) {
        lastProcessingTime = measureTimeMillis {
            try {
                var lastHeight = javascriptExecutor.executeScript("return document.body.scrollHeight") as Long
                while (true) {
                    javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);")
                    delay(2000)
                    val newHeight = javascriptExecutor.executeScript("return document.body.scrollHeight") as Long
                    if (newHeight == lastHeight) {
                        break
                    }
                    lastHeight = newHeight
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun quit() {
        scraperDriver.quitDriver()
    }
}