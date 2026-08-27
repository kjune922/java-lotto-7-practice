package controller;

import calculator.Calculator;
import generate.LottoNumberGenerator;
import lotto.Lotto;
import lotto.Rank;
import parse.ParseNumber;
import validator.InputValidator;
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
    private final InputValidator inputValidator = new InputValidator();
    private final Calculator calculator = new Calculator();

    public void start(){
        outputView.printBuyMessage();
        int buyCharge = readPurchaseAmount();
        int LottoCount = calculator.buy(buyCharge);

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

        long sumPrice = calculator.calculateTotalPrice(rankMap);
        String result = calculator.calculateResult(sumPrice,buyCharge);
        outputView.printResult(result);
    }

    private int readPurchaseAmount() {
        while(true){
            try{
                int amount = inputView.readInputBuyCharge();
                inputValidator.validateBuyCharge(amount);
                return amount;
            } catch (NumberFormatException e){
                outputView.printError("[ERROR] 구입 금액은 숫자여야 합니다.");
            } catch (IllegalArgumentException e){
                outputView.printError(e.getMessage());
            }
        }
    }
}
