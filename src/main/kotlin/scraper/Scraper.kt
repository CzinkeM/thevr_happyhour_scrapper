package scraper

interface Scraper<T> {

    /**
     * Process a webpage with the provided logic.
     * This process takes a lot of time(5-10 min) make sure it does not block the UI thread.
     *
     * @return Returns a [Long] number represents the processing time in ms.
     */
    suspend fun processWebpage(): List<T>

    /**
     * Release the resources related to scraper.
     */
    fun quit()
}