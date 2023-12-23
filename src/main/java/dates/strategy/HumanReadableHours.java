package dates.strategy;

import java.time.Duration;

public class HumanReadableHours implements HumanReadableUnit{
    @Override
    public String parse(Duration d) {

        var time = d.toHours();
        var timeAsString = Long.toString(time);

        if(time >= 5 && time <= 20)
            return time + " часов назад";

        if(timeAsString.endsWith("1"))
            return time + " час назад";

        return time + " часа назад";
    }
}
