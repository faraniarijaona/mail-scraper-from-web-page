package main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "/opt/phantomjs/bin/phantomjs"
        );
        WebDriver driver = new PhantomJSDriver(caps);
        driver.get(args[0]);
       String text = driver.findElement(By.tagName("html")).getAttribute("innerHTML");

        Pattern p = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+[a-z]{2,4}", Pattern.CASE_INSENSITIVE);

        String[] ExtensionToBeIgnored = new String[]{"jpg", "png","gif", "", "txt", "html","scss", "css","js", "php", "jsp"};
        List<String> stringList = new ArrayList<>();
        Matcher matcher = p.matcher(text);
        while (matcher.find()){
            String found = matcher.group();
            String ext = found.split("\\.")[found.split("\\.").length-1].toLowerCase();
            if(!Arrays.asList(ExtensionToBeIgnored).contains(ext)){
                System.out.println(found);
                stringList.add(found);
            }
        }
    }
}
