package generate;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class Generate {

    public List<Integer> LottoNumberGenerator(){
        return Randoms.pickUniqueNumbersInRange(1,45,6);
    }
}
