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
            List<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                numbers.add(generate.generateRandomNumber());
            }
            Lotto lotto = new Lotto(numbers);
            lottos.add(lotto.getLotto());
        }

        outputView.printCorrectInputNumber();
        String correctNumber = inputView.readCorrectNumber();
        String[] correctNumberArr = parseNumber.parse(correctNumber);

        int[] numbers = new int[6];
        for (int i = 0; i < correctNumberArr.length; i++) {
            numbers[i] = Integer.parseInt(correctNumberArr[i]);
        }


        outputView.printBonusNumber();

        inputView.readBonusNumber();

        int[] correctCountArr = new int[LottoCount];
        int maxCorrectCount = 0;
        int index = 0;
        for (List<Integer> lotto : lottos) {
            int correctCount = 0;
            correctCountArr[index] = checkLotto(lotto,numbers,correctCount);
            maxCorrectCount = Math.max(maxCorrectCount,correctCountArr[index]);
        }

        int correctResult = findCorrectResult(maxCorrectCount);

        String result = calculateResult(correctResult,buyCharge);

        outputView.printResult(result);
    }

    private String calculateResult(int correctResult, int buyCharge) {
        double result = (double) correctResult / buyCharge * 100;

        DecimalFormat df = new DecimalFormat("0.##");

        return df.format(result);
    }

    private int findCorrectResult(int maxCorrectCount) {
        if(maxCorrectCount == 6){
            return 2000000000;
        } else if(maxCorrectCount == 5){
            return 1500000;
        } else if(maxCorrectCount == 4){
            return 50000;
        }
        return 5000;
    }

    private int checkLotto(List<Integer> lotto, int[] numbers, int correctCount) {
        for (int i = 0; i < lotto.size(); i++) {
            if(lotto.get(i) == numbers[i]){
                correctCount++;
            }
        }
        return correctCount;
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
