package classes;

public class Card {
    String number;
    String pinCode;
    public Card(String number, String pinCode)
    {
        this.number = number;
        this.pinCode = pinCode;
    }

    public String getNumber()
    {
        number = number.replace(" ", "");
        var lastDigits = number.substring(number.length() - 4);
        return "**** ".repeat(3).concat(lastDigits);
    }


    public String getNumber(String pinCode)
    {
        if(pinCode.equals(this.pinCode))
        {
            return this.number;
        }
        else
            return "401 Unauthorized";
    }
}
