package com.jglive.www.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {

	public static void main(String[] args) {
		
		WebDriver driver = null;
		WebDriver driver2 = null;

		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");// Web Driver 압축 해제 경로 입력
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless", "--disable-gpu","-no-sandbox");
		driver = new ChromeDriver(option);// WebDriver 객체 생성
//		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);// 로드 웹페이지에서 특정 요소를 찾을 때까지 기다리는 시간 설정
//		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);// 페이지로드가 완료 될 때까지 기다리는 시간 설정
//		driver.manage().window().maximize();//브라우저 창 최대화
		driver.get("https://web.joongna.com/search?keyword=%EB%85%B8%ED%8A%B8%2020&minPrice=300000&maxPrice=700000");// 웹
		
//		WebElement div = driver.findElement(By.xpath("/html/body/div[@id='__next]/div[@class='css-dvv6ar]'"));
		List<WebElement> posts = driver.findElements(By.className("e312bqk0"));
		
		int count =0;
		for (WebElement webElement : posts) {
			String price = webElement.findElement(By.className("priceTxt")).getText();
			String title = webElement.findElement(By.className("titleTxt")).getText();
			String url = webElement.findElement(By.className("css-8rmnao")).getAttribute("href");
			System.out.println("제목 : " + title);
			System.out.println("가격 : " + price);
			System.out.println("링크 : " + url);
			count ++;
		}
		driver.quit();
		System.out.println("검색결과 : " + count);
		
		
	}
}
