import kotlinx.coroutines.runBlocking
import scraper.FirefoxHappyHourScraper
import java.util.concurrent.TimeUnit

fun Example() {
    val scraper = FirefoxHappyHourScraper(
        driverExePath = "Path to geckodriver.exe",
        firefoxExePath = "Path to firefox.exe",
    )

    runBlocking {
        val hhList = scraper.processWebpage()
        println("${hhList.size} HappyHour obtained in ${TimeUnit.MILLISECONDS.toSeconds(scraper.lastProcessingTime)}")
        scraper.quit()
    }
}

