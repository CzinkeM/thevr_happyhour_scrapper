package driver

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement

interface ScraperDriver {

    fun loadPage()

    fun getExecutor(): JavascriptExecutor

    fun getRoot(): WebElement

    fun quitDriver()
}