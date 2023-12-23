package dates;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {


        HumanReadableTimeStamp service = new HumanReadableTimeStampImpl();


        for(int i = 1; i < 24; i++)
        {
            System.out.println(service.getTimeStamp(LocalDateTime.now().minusHours(i)));
        }

    }
}
