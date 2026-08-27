package validator;

public class InputValidator {

    public void validateBuyCharge(int buyCharge) {
        if(buyCharge <= 0 || buyCharge % 1000 != 0){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위 입니다.");
        }
    }
}
