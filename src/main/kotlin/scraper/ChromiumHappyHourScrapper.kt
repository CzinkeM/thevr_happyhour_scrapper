package scraper

import driver.ChromiumScraperDriver

class ChromiumHappyHourScrapper(
    driverExePath: String,
    chromeExePath: String,
): HappyHourScraper(ChromiumScraperDriver(driverExePath, chromeExePath))