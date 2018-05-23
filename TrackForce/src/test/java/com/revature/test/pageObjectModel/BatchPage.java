package com.revature.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.tester.MethodUtil;

public class BatchPage {
	static WebElement we;
	
	public static WebElement getSelectMenuOption(WebDriver wd,int selectMenuNum,int optionNum) {
		return MethodUtil.waitForLoad(wd,"(//md-select-menu)["+selectMenuNum+"]/md-option["+optionNum+"]");
	}
	
	public static WebElement getOptionByText(WebDriver wd,int menuIndex, String text) {
		List<WebElement> list = wd.findElements(By.xpath("//md-option/div[1]"));
		for (int i=0; i<list.size();i++) {
			if (list.get(i).getText().contains(text))
				return list.get(i);
		}
		try {
			throw new NoSuchElementException("Option with text " + text +" was not found");
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static WebElement getSkillsOptsByText(WebDriver wd,int menuIndex, String text) {
		List<WebElement> list = wd.findElements(By.xpath("//md-option/div[2]"));
		for (int i=0; i<list.size();i++) {
			if (list.get(i).getText().contains(text))
				return list.get(i);
		}
		try {
			throw new NoSuchElementException("Option with text " + text +" was not found");
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static WebElement getBatchCurriculumSelect(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//md-select)[1]");		
	}
	
		public static WebElement getBatchCurriculumOption(WebDriver wd, String text) {
			return getOptionByText(wd, 12, text);
		}
	
	public static WebElement getBatchFocusSelect(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//md-select)[2]");
	}
	
		public static WebElement getBatchFocusOption(WebDriver wd, String text) {
			return getOptionByText(wd, 11, text);
		}
	
	public static WebElement getBatchSkillsSelect(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//md-select)[3]");
	}
	
		public static WebElement getBatchSkillsOption(WebDriver wd, String text) {
			return getSkillsOptsByText(wd, 12, text);
		}
	
	public static WebElement getBatchStartDateInput(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//input)[1]");
	}
	
	public static WebElement getBatchEndDateInput(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//input)[2]");
	}
	
	public static WebElement getBatchWeekSpanInput(WebDriver wd) {
		// NOTE: this input is read-only
		return MethodUtil.waitForLoad(wd,"(//input)[3]");
	}
	
	public static WebElement getBatchNameInput(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//input)[4]");
	}
	
	public static WebElement getBatchTrainerSelect(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//md-select)[4]");
	}
	
		public static WebElement getBatchTrainerOption(WebDriver wd, String text) {
			return getOptionByText(wd, 1, text);
		}
	
	public static WebElement getBatchCoTrainerSelect(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//md-select)[5]");
	}
		public static WebElement getBatchCoTrainerOption(WebDriver wd, String text) {
			return getOptionByText(wd, 2, text);
		}
	
	public static WebElement getBatchLocationSelect(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//md-select)[6]");
	}
		public static WebElement getBatchLocationOption(WebDriver wd, String text) {
			return getOptionByText(wd, 3, text);
		}
	
	public static WebElement getBatchBuildingSelect(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//md-select)[7]");
	}
		public static WebElement getBatchBuildingOption(WebDriver wd, String text) {
			return getOptionByText(wd, 4, text);
		}
	
	public static WebElement getBatchRoomSelect(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//md-select)[8]");
	}
		public static WebElement getBatchRoomOption(WebDriver wd, String text) {
			return getOptionByText(wd, 5, text);
		}
	
	public static WebElement getBatchSubmitBtn(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//button)[2]");
	}
	
	public static WebElement getBatchCancelBtn(WebDriver wd) {
		return MethodUtil.waitForLoad(wd,"(//button)[3]");
	}
	
	public static WebElement getColumnSortBtn(WebDriver wd,int index) {
		return MethodUtil.waitForLoad(wd,"//*[@id=\"view\"]/md-card/md-content[1]/div/md-table-container/table/thead/tr/th["+index+"]/md-icon");
	}
										  //*[@id="view"]/md-card/md-content[1]/div/md-table-container/table/thead/tr/th[2]/md-icon
										  //*[@id="view"]/md-card/md-content[1]/div/md-table-container/table/thead/tr/th[10]/md-icon
}
