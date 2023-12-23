package dates.strategy;

import java.time.Duration;

public interface HumanReadableUnit {
    String parse(Duration d);
}
