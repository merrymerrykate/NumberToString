import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberToString {



    private static final String[] UNITS = {
            "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять",
            "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };

    private static final String[] TENS = {
            "", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят",
            "восемьдесят", "девяносто"
    };

    private static final String[] HUNDREDS = {
            "", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот",
            "восемьсот", "девятьсот"
    };

    private static final String[] THOUSANDS = {
            "", "тысяча", "тысячи", "тысяч"
    };

    public static String convert(BigDecimal amount) {
        BigInteger intPart = amount.toBigInteger();

        if (intPart.compareTo(BigInteger.ZERO) == 0) {
            return "ноль рублей";
        }

        String intPartInWords = convertIntegerPart(intPart);

        return intPartInWords + " рублей";
    }

    private static String convertIntegerPart(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            return "";
        }

        StringBuilder result = new StringBuilder();



        int thousands = number.divide(BigInteger.valueOf(1000)).intValue();
        int hundreds = number.mod(BigInteger.valueOf(1000)).intValue();

        if (thousands > 0) {
            result.append(convertThousands(thousands)).append(" ");
        }

        if (hundreds > 0) {
            result.append(convertHundreds(hundreds));
        }

        return result.toString().trim();
    }

    private static String convertThousands(int number) {
        String result = "";

        if (number > 19) {
            result += TENS[number / 10] + " ";
            number %= 10;
        }

        if (number > 0) {
            result += (number == 1 ? "одна" : number == 2 ? "две" : UNITS[number]) + " ";
        }

        result += THOUSANDS[getThousandsIndex(number)];
        return result.trim();
    }

    private static int getThousandsIndex(int number) {
        if (number == 1) {
            return 1;
        } else if (number >= 2 && number <= 4) {
            return 2;
        } else {
            return 3;
        }
    }

    private static String convertHundreds(int number) {
        String result = "";

        if (number >= 100) {
            result += HUNDREDS[number / 100] + " ";
            number %= 100;
        }

        if (number > 19) {
            result += TENS[number / 10] + " ";
            number %= 10;
        }

        if (number > 0) {
            result += UNITS[number];
        }

        return result.trim();
    }

    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal(99999);
        System.out.println(convert(amount));
    }
}


