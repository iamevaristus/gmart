package g.store.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {
    public static String getLocalCurrencyFromDouble(double amount) {
        Locale userLocale = Locale.of("en", "NG");

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(userLocale);

        /// Convert salary from int to string of currency
        return numberFormat.format(amount);
    }

//    public static double convertStringToDouble(String amount) {
//        String value;
//
//        /// Check if there is , in amount and fix that
//        if(amount.contains(",")) {
//            value = amount.replace(",", "");
//        } else {
//            value = amount;
//        }
//
//        /// Convert to double and return
//        return Double.parseDouble(value);
//    }
}
