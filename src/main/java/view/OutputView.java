package view;

import lotto.Lotto;

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
}
