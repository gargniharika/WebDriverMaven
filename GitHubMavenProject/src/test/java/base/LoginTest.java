package base;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	public static void main(String[] args) throws ParseException {
		//Test CI changes after configuring Jenkins-GIT
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.facebook.com/login/identify?ctx=recover"); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("a[title=\"Go to Facebook home\"]")).click();
		if(driver.getTitle().equals("Facebook – log in or sign up"))
			System.out.println("We are on facebook homepage");
		else
			System.out.println("We are not yet on facebook homepage");
		driver.navigate().back();
//		driver.quit();
		
		driver.get("https://money.rediff.com/gainers/bsc/daily/groupa");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		List <WebElement> col=driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
		System.out.println("Number of cols=:" + col.size());
		
		List <WebElement> rows=driver.findElements(By.xpath("//*[@id='leftcontainer']/table/tbody/tr/td[1]"));
		System.out.println("Number of rows=:" + rows.size());
		
		
		WebElement baseTable = driver.findElement(By.tagName("table"));
		  
		 //To find third row of table
		WebElement tableRow = baseTable.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]"));
        String rowtext = tableRow.getText();
		System.out.println("Third row of table : "+rowtext);
		    
		    //to get 3rd row's 2nd column data
		WebElement cellIneed = tableRow.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]/td[2]"));
		String valueIneed = cellIneed.getText();
		System.out.println("Cell value is : " + valueIneed); 
		
		List <WebElement> currPriceList = baseTable.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr[3]"));
		String max;
		double m=0, r=0;
		for (int i =1;i<rows.size();i++)
        {    
            max= driver.findElement(By.xpath("html/body/div[1]/div[5]/table/tbody/tr[" + (i+1)+ "]/td[4]")).getText();
            NumberFormat f =NumberFormat.getNumberInstance(); 
            Number num = f.parse(max);
            max = num.toString();
            m = Double.parseDouble(max);
            if(m>r)
             {    
                r=m;
             }
        }
        System.out.println("Maximum current price is : "+ r);
		driver.quit();
	}

}


