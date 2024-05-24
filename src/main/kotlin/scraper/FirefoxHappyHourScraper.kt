package scraper

import driver.FirefoxScraperDriver

class FirefoxHappyHourScraper(
    driverExePath: String,
    firefoxExePath: String,
): HappyHourScraper(FirefoxScraperDriver(driverExePath, firefoxExePath))