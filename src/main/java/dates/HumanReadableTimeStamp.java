package dates;

import java.time.LocalDateTime;

public interface HumanReadableTimeStamp {
    String getTimeStamp(LocalDateTime eventTimeStamp);
}
