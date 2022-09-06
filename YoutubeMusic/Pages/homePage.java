package YoutubeMusic.Pages;

import Shared.Base.BaseDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class homePage extends BaseDriver {

    public homePage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * IDENTIFIER
     **/

    @FindBy(xpath = "//*[@id=\"right-content\"]/a")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"right-content\"]/ytmusic-settings-button/tp-yt-paper-icon-button")
    public WebElement userAvatar;

    @FindBy(xpath = "//*[@id=\"icon\"]")
    private WebElement searchIcon;

    @FindBy(xpath = "//*[@id=\"input\"]")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"img\"]")
    private WebElement firstSearchResult;

    @FindBy(xpath = "//*[@id=\"items\"]/ytmusic-responsive-list-item-renderer[1]/div[2]/div[1]/yt-formatted-string/a")
    private WebElement firstSongOnMusicCarousel;

    @FindBy(xpath = "//*[@id=\"items\"]/ytmusic-responsive-list-item-renderer[1]/div[2]/div[3]/yt-formatted-string[1]")
    private WebElement firstSongArtistOnMusicCarousel;

    @FindBy(xpath = "//*[@id=\"play-pause-button\"]")
    public WebElement playPauseButton;

    @FindBy(xpath = "//*[@id=\"layout\"]/ytmusic-player-bar/div[2]/div[2]/span/span[2]/yt-formatted-string/a[1]")
    public WebElement artistButton;

    /**
     * FUNCTION
     **/

    public void typeSearchInput(String input) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input\"]")));
        searchInput.sendKeys(input);
        searchInput.sendKeys(Keys.RETURN);
        info("Type " + input + " into search input");
    }

    public void clickPlayFirstSearchResult() {
        firstSearchResult.click();
        info("Click play on first search result");
    }

    public void clickPlayFirstSongOnMusicCarousel() {
        firstSongOnMusicCarousel.click();
        info("Click play on first song on music carousel");
    }

    public void clickPlayFirstSongArtistOnMusicCarousel() {
        firstSongArtistOnMusicCarousel.click();
        info("Click play on first song artist on music carousel");
    }

    public void clickPlayPauseButton() {
        playPauseButton.click();
        info("Click play/pause button");
    }

}
