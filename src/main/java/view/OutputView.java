package view;

import lotto.Lotto;
import lotto.Rank;

import java.util.Map;

public class OutputView {

    public void printBuyMessage(){
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLotto(int num){
        System.out.println(num + "개를 구매했습니다.");
    }

    public void printCorrectInputNumber(){
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public void printBonusNumber(){
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public void printResult(String result){
        System.out.println("총 수익률은 " + result + "%입니다.");
    }

    public void printCurLottoNumbers(Lotto lotto) {
        System.out.println(lotto.getLotto());
    }

    public void printLottoResult(Map<Rank, Integer> rankMap) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5000원) - "
                + rankMap.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - "
                + rankMap.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - "
                + rankMap.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "
                + rankMap.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - "
                + rankMap.get(Rank.FIRST) + "개");
    }

    public void printError(String message) {
        System.out.println("[ERROR] 로또 금액 입력은 숫자여야합니다.");
    }
}
