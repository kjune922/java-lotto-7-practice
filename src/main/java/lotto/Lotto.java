package lotto;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public List<Integer> getLotto() {
        return numbers;
    }

    public int countMatches(int[] winngNumbers) {
        int matchCount = 0;
        for (int i = 0; i < winngNumbers.length; i++) {
            if(numbers.contains(winngNumbers[i])){
                matchCount++;
            }
        }
        return matchCount;
    }

    public boolean checkBonus(Lotto lotto, int bonusNumber) {
        for (Integer number : numbers) {
            if(number == bonusNumber){
                return true;
            }
        }
        return false;
    }
}
