package scraper

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

internal fun WebElement.mapToHappyHour(): HappyHourDto {
    val info = this.findElement(By.className("hhPartInfo"))
    val url = info.findElement(By.xpath(".//a")).getAttribute("href")
    val date = info.findElement(By.className("hhPartInfoPubDateCenter")).text.substring(2)
    val serialNumber = info.findElement(By.className("hhPartInfoPartCenter")).text.substring(2)
    val title = info.findElement(By.xpath(".//h5/a")).text

    val body = this.findElement(By.className("card-body"))
    val chapters = body.findElements(By.className("timeStampLink")).map {
        it.mapToHappyHourChapter()
    }
    return HappyHourDto(
        title = title,
        url = url,
        date = date,
        serialNumber = serialNumber,
        chapters = chapters,
    )
}

fun WebElement.mapToHappyHourChapter(): HappyHourChapterDto {
    return HappyHourChapterDto(
        url = this.getAttribute("href"),
        title = this.text
    )
}
