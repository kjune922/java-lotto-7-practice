package controller;

import generate.lottoNumberGenerator;
import lotto.Lotto;
import parse.ParseNumber;
import view.InputView;
import view.OutputView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final ParseNumber parseNumber = new ParseNumber();
    private final lottoNumberGenerator generate = new lottoNumberGenerator();

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

        int[] winningNumbers = new int[6];
        for (int i = 0; i < correctNumberArr.length; i++) {
            winningNumbers[i] = Integer.parseInt(correctNumberArr[i]);
        }

        long correctResult = 0;

        for (int i = 0; i < lottos.size(); i++) {
            Lotto lotto = lottos.get(i);
            correctResult += findCorrectResult(lotto.countMatches(winningNumbers));
        }
        String result = calculateResult(correctResult,buyCharge);
        outputView.printResult(result);
    }

    private String calculateResult(long correctResult, int buyCharge) {
        double result = (double) correctResult / buyCharge * 100;

        DecimalFormat df = new DecimalFormat("#,##0.0");

        return df.format(result);
    }

    private int findCorrectResult(int maxCorrectCount) { // 이 부분은 ENUM으로 한번바꿔보자
        if(maxCorrectCount == 6){
            return 2000000000;
        } else if(maxCorrectCount == 5){
            return 1500000;
        } else if(maxCorrectCount == 4){
            return 50000;
        } else if(maxCorrectCount == 3){
            return 5000;
        }
        return 0;
    }

    private int buy(int buyCharge) {
        return buyCharge / 1000;
    }

    private void validateBuyCharge(int buyCharge) {
        if(buyCharge % 1000 != 0){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위 입니다.");
        }
    }
}
