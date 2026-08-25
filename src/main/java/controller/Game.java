package controller;

import generate.Generate;
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
    private final Generate generate = new Generate();

    public void start(){
        outputView.printBuyMessage();
        int buyCharge = inputView.readInputBuyCharge();
        validateBuyCharge(buyCharge);
        int LottoCount = buy(buyCharge);

        outputView.printLotto(LottoCount);
        List<List<Integer>> lottos = new ArrayList<>();

        for (int i = 0; i < LottoCount; i++) {
            Lotto lotto = new Lotto(generate.LottoNumberGenerator());
            lottos.add(lotto.getLotto());
        }

        for (int i = 0; i < lottos.size(); i++) {
            System.out.println(lottos.get(i));
        }

        outputView.printCorrectInputNumber();
        String correctNumber = inputView.readCorrectNumber();
        String[] correctNumberArr = parseNumber.parse(correctNumber);

        int[] winngNumbers = new int[6];
        for (int i = 0; i < correctNumberArr.length; i++) {
            winngNumbers[i] = Integer.parseInt(correctNumberArr[i]);
        }

        int[] matchArr = new int[LottoCount];
        int correctResult = 0;

        for (int i = 0; i < lottos.size(); i++) {
            Lotto lotto = new Lotto(lottos.get(i));
            matchArr[i] = lotto.countMatches(winngNumbers);
            correctResult += findCorrectResult(matchArr[i]);
        }
        String result = calculateResult(correctResult,buyCharge);
        outputView.printResult(result);
    }

    private String calculateResult(int correctResult, int buyCharge) {
        double result = (double) correctResult / buyCharge * 100;

        DecimalFormat df = new DecimalFormat("0.##");

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
