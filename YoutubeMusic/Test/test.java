package YoutubeMusic.Test;

import Shared.Base.BaseDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners(Shared.Utility.Listener.class)
public class test extends BaseDriver {

    // TODO: Need to work on assertion
//    @Test
//    public void userLoginSuccessfully() throws InterruptedException {
//        driver.get("https://music.youtube.com");
//        info("User navigate to https://music.youtube.com");
//
//        homePage.clickLoginButton();
//        loginPage.typeId("dummy559502932@gmail.com");
//        loginPage.clickNextButton();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        loginPage.typePassword("miegoreng123");
//        loginPage.clickNextButton2();
//        driver.navigate().to("https://music.youtube.com");
//        // assert user avatar icon is displayed
//        Assert.assertEquals(homePage.userAvatar.getAttribute("aria-label"),"Buka menu avatar");
//    }

    @Test
    public void userPlayFirstSongOnMusicCarousel() throws InterruptedException {
        driver.get("https://music.youtube.com");
        info("User navigate to https://music.youtube.com");
        homePage.clickPlayFirstSongOnMusicCarousel();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String URL = driver.getCurrentUrl();
        // assert page URL is song page
        Assert.assertTrue(URL.contains("https://music.youtube.com/watch?" ));
        Assert.assertEquals(homePage.playPauseButton.getAttribute("title"), "Pause");
    }

    @Test
    public void userPauseSong() throws InterruptedException {
        driver.get("https://music.youtube.com");
        info("User navigate to https://music.youtube.com");
        homePage.clickPlayFirstSongOnMusicCarousel();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        homePage.clickPlayPauseButton();
        // assert button title is updaated to "Play" after user clicks on pause
        Assert.assertEquals(homePage.playPauseButton.getAttribute("title"), "Play");
    }

    @Test
    public void userViewsSongArtist() throws InterruptedException {
        driver.get("https://music.youtube.com");
        info("User navigate to https://music.youtube.com");
        homePage.clickPlayFirstSongArtistOnMusicCarousel();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://music.youtube.com/channel/"));
    }

}