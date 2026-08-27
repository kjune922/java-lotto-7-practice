package calculator;

import lotto.Rank;

import java.text.DecimalFormat;
import java.util.Map;

public class Calculator {

    public long calculateTotalPrice(Map<Rank, Integer> rankMap) {
        long totalPrice = 0;

        for (Rank rank : Rank.values()) {
            int winCount = rankMap.get(rank);
            totalPrice += (long) rank.getPrice() * winCount;
        }
        return totalPrice;

    }

    public String calculateResult(long sumPrice, int buyCharge) {
        double result = (double) sumPrice / buyCharge * 100;

        DecimalFormat df = new DecimalFormat("#,##0.0");

        return df.format(result);
    }

    public int buy(int buyCharge) {
        return buyCharge / 1000;
    }
}
