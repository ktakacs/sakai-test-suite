package com.anisakai.test.pageobjects

import org.openqa.selenium.By
import java.util.Calendar
import org.openqa.selenium.support.ui.Select
import scala.util.Random

/**
 * Created with IntelliJ IDEA.
 * User: gareth
 * Date: 9/6/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
object CalendarObj extends CalendarObj
class CalendarObj extends Page {

  def checkCalendar() : Boolean = {
    return webDriver.findElement(By.tagName("h3")).getText().contains("Calendar")
  }

  def addCalEvent() {
    click on xpath("//a[contains(@title,'Add')]")
  }

  def createRandomEvent() : String = {
    val eventTitle = faker.letterify("??????????")
    val cal = Calendar.getInstance
    cal.add(Calendar.DATE, 1);

    val day = cal.get(Calendar.DAY_OF_MONTH)
    val month = cal.get(Calendar.MONTH) + 1
    val year = cal.get(Calendar.YEAR)

    val rand = new Random();
    val hour = rand.nextInt(12) + 1;
    var am_pm = "AM"

    textField("activitytitle").value = eventTitle
    singleSel("month").value = month.toString()
    singleSel("day").value = day.toString()
    singleSel("yearSelect").value = year.toString()
    singleSel("startHour").value = hour.toString()
    singleSel("startMinute").value = "0"
    def ampm = new Select(webDriver.findElement(By.name("startAmpm")))
    ampm.selectByVisibleText(am_pm)
    singleSel("eventType").value = "Exam"
    textArea("location").value = "Online"
    Portal.richTextEditor()
    click on name("eventSubmit_doAdd")
    return eventTitle
  }

  def isAdded(eventTitle : String) : Boolean = {
    return linkText(eventTitle).element.isDisplayed
  }

}
