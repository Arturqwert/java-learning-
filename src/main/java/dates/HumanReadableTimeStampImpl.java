package dates;

import dates.strategy.HumanReadableHours;
import dates.strategy.HumanReadableMinutes;
import dates.strategy.HumanReadableUnit;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HumanReadableTimeStampImpl implements HumanReadableTimeStamp{
    Map<String, HumanReadableUnit> handlers = new HashMap<>();

    public HumanReadableTimeStampImpl() {
        handlers.put("minutes", new HumanReadableMinutes());
        handlers.put("hours", new HumanReadableHours());
    }

    @Override
    public String getTimeStamp(LocalDateTime eventTimeStamp) {

        String key = null;

        var diff = Duration.between(eventTimeStamp, LocalDateTime.now());

        if(diff.toMinutes() < 60) {
            key = "minutes";
        }

        if(diff.toHours() > 0)
        {
            key = "hours";
        }

        return handlers.get(key).parse(diff);
    }
}
