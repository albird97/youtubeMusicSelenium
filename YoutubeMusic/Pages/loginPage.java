package YoutubeMusic.Pages;

import Shared.Base.BaseDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class loginPage extends BaseDriver {

    public loginPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * IDENTIFIER
     **/

    @FindBy(id = "identifierId")
    private WebElement idInput;

    @FindBy(xpath = "//*[@id=\"identifierNext\"]/div/button/span")
    private WebElement nextButton;

    @FindBy(xpath = " //*[@id=\"passwordNext\"]/div/button/span")
    private WebElement nextButton2;

    @FindBy(name = "Passwd")
    private WebElement passwordInput;


    @FindBy(id = "input_phone_number")
    private WebElement tfPhoneNumber;

    @FindBy(id = "button_masuk")
    private WebElement btnLogin;

    @FindBy(id = "button_phone_not_active")
    private WebElement btnPhoneNumberNotActive;

    @FindBy(id = "button_register")
    private WebElement btnSignUp;

    @FindBy(id = "text_welcome")
    private WebElement txtWelcome;

    /**
     * FUNCTION
     **/

    public void typeId(String input) {
        idInput.clear();
        idInput.sendKeys(input);
        info("Type " + input + " into id input");
    }

    public void clickNextButton() {
        nextButton.click();
        info("Click next button");
    }

    public void typePassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        info("Type " + password + " into password input");
    }

    public void clickNextButton2() {
        nextButton2.click();
        info("Click next button 2");
    }



}
