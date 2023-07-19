import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelExample {

    WebDriver driver;
        @Test(dataProvider="testdata")
        public void demoClass(String username, String password) throws InterruptedException {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println(driver.getTitle());
            driver.get("https://www.automationexercise.com/login");
            driver.findElement(By.name("email")).sendKeys(username);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/button")).click();
            Thread.sleep(5000);
            System.out.println(driver.getTitle());
            Assert.assertTrue(driver.getTitle().matches("Automation Exercise"), "Invalid credentials , Login failed!");
            System.out.println("Login successful");
        }
        @AfterMethod
        void ProgramTermination() {
            driver.quit();
        }
        @DataProvider(name="testdata")
        public Object[][] testDataExample(){
            ReadExcelFile configuration = new ReadExcelFile("C:\\Users\\poorvid\\IdeaProjects\\DataDrivenDevelopement-1\\doc\\Login_testdata.xlsx");
            int rows = configuration.getRowCount(0);
           Object[][]signin_credentials = new Object[rows][2];

            for(int i=0;i<rows;i++)
            {
                signin_credentials[i][0] = configuration.getData(0, i, 0);
               signin_credentials[i][1] = configuration.getData(0, i, 1);
            }
            return signin_credentials;
        }
  }

