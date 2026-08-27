package controller;

import generate.LottoNumberGenerator;
import lotto.Lotto;
import lotto.Rank;
import parse.ParseNumber;
import view.InputView;
import view.OutputView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ParseNumber parseNumber = new ParseNumber();
    private final LottoNumberGenerator generate = new LottoNumberGenerator();

    public void start(){
        outputView.printBuyMessage();
        int buyCharge = inputView.readInputBuyCharge();
        validateBuyCharge(buyCharge);
        int LottoCount = buy(buyCharge);

        outputView.printLotto(LottoCount);

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < LottoCount; i++) {
            lottos.add(new Lotto(generate.generate()));
        }

        for (Lotto lotto : lottos) {
            outputView.printCurLottoNumbers(lotto);
        }

        outputView.printCorrectInputNumber();
        String correctNumber = inputView.readCorrectNumber();
        String[] correctNumberArr = parseNumber.parse(correctNumber);

        outputView.printBonusNumber();
        int bonusNumber = inputView.readBonusNumber();

        int[] winningNumbers = new int[6];
        for (int i = 0; i < correctNumberArr.length; i++) {
            winningNumbers[i] = Integer.parseInt(correctNumberArr[i]);
        }

        Map<Rank,Integer> rankMap = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            rankMap.put(rank,0);
        }

        for (int i = 0; i < lottos.size(); i++) {

            Lotto lotto = lottos.get(i);
            int curMatch = lotto.countMatches(winningNumbers);
            boolean isBonus = lotto.checkBonus(bonusNumber);

            Rank rank = Rank.find(curMatch, isBonus);

            int curCount = rankMap.get(rank);
            rankMap.put(rank,curCount + 1);
        }
        outputView.printLottoResult(rankMap);

        long sumPrice = calculateTotalPrice(rankMap);
        String result = calculateResult(sumPrice,buyCharge);
        outputView.printResult(result);
    }

    private long calculateTotalPrice(Map<Rank, Integer> rankMap) {
        long totalPrice = 0;

        for (Rank rank : rankMap.keySet()) {
            int winCount = rankMap.get(rank);
            totalPrice += (long) rank.getPrice() * winCount;
        }
        return totalPrice;

    }

    private String calculateResult(long sumPrice, int buyCharge) {
        double result = (double) sumPrice / buyCharge * 100;

        DecimalFormat df = new DecimalFormat("#,##0.0");

        return df.format(result);
    }

    private int buy(int buyCharge) {
        return buyCharge / 1000;
    }

    private void validateBuyCharge(int buyCharge) {
        if(buyCharge <= 0 || buyCharge % 1000 != 0){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위 입니다.");
        }
    }
}
