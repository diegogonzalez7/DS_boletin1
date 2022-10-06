package e1;

public class DateUtilities {
    public static boolean isLeap(int year) {
        if ((year%4)==0) {
            return (year % 100) != 0 || (year % 400) == 0;
        }
        else return false;
    }
    public static int numberOfDays(int month, int year) {
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> {
                return 31;
            }
            case 2 -> {
                if (!isLeap(year)) return 28;
                else return 29;
            }
            case 4, 6, 9, 11 -> {
                return 30;
            }
            default ->
                    throw new IllegalArgumentException("Invalid month number");
        }

    }
    public static String convertToISODate(String dateText){
        String [] parts = dateText.split("[ ,]+");
        String month = parts[0];
        String day = parts[1];
        String year = parts[2];

        switch (month) {
            case "January" -> month = "01";
            case "February" -> month = "02";
            case "March" -> month = "03";
            case "April" -> month = "04";
            case "May" -> month = "05";
            case "June" -> month = "06";
            case "July" -> month = "07";
            case "August" -> month = "08";
            case "September" -> month = "09";
            case "October" -> month = "10";
            case "November" -> month = "11";
            case "December" -> month = "12";
        }

        return year+"-"+month+"-"+day;
    }
    public static boolean checkISODate(String ISODate) {
        String [] parts = ISODate.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);


        if (month < 1 || month > 12)
            return false;

        else if (day>numberOfDays(month,year) || day < 1)
            return false;

        else return year >= 0;

    }
}
