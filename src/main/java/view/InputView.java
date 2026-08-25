package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readInputBuyCharge(){
        return Integer.parseInt(Console.readLine());
    }

    public String readCorrectNumber(){
        return Console.readLine();
    }

    public int readBonusNumber(){
        return Integer.parseInt(Console.readLine());
    }
}
