import java.time.DayOfWeek;

public class PaymentSchedule {
    private Integer monthDay;

    private DayOfWeek weekDay;

    private String schedule;

    public PaymentSchedule(Integer monthDay, DayOfWeek weekDay, String schedule) {
        this.monthDay = monthDay;
        this.weekDay = weekDay;
        this.schedule = schedule;
    }

    public Integer getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
    }

    public DayOfWeek getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayOfWeek weekDay) {
        this.weekDay = weekDay;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Payment Schedule{" +
                    "Day of the month: " + getMonthDay() +
                    "Day of the week: " + getWeekDay() +
                    "Schedule Type: " + getSchedule() + '\'' +
                "}";
    }
}
