package dates.strategy;

import java.time.Duration;

public class HumanReadableMinutes implements HumanReadableUnit{
    @Override
    public String parse(Duration d) {

        var time = d.toMinutes();
        var timeAsString = Long.toString(time);

        if(time == 0)
            return "только что";

        if(time > 4 && time < 21)
            return time + " минут назад";

        if(timeAsString.endsWith("1"))
            return time + " минуту назад";

        if(timeAsString.endsWith("2")
                || timeAsString.endsWith("3")
                || timeAsString.endsWith("4"))
            return time + " минуты назад";

        return null;
    }
}
