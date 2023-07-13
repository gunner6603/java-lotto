package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoResultTest {

    private List<Rank> target;

    @BeforeEach
    void setUp() {
        target = List.of(
                Rank.FIFTH, Rank.FIFTH,
                Rank.NONE, Rank.NONE, Rank.NONE, Rank.NONE, Rank.NONE,
                Rank.NONE, Rank.NONE, Rank.NONE, Rank.NONE, Rank.NONE
        );
    }

    @Test
    @DisplayName("LottoResult를 정상적으로 생성한다.")
    void createSuccess() {
        /* given */

        /* when & then */
        assertDoesNotThrow(() -> new LottoResult(target));
    }

    @Test
    @DisplayName("로또 결과로 특정 등수가 나온 횟수를 반환한다.")
    void countRank() {
        /* given */
        final LottoResult lottoResult = new LottoResult(target);

        /* when */
        final Long firstRankCount = lottoResult.countRank(Rank.FIRST);
        final Long fifthRankCount = lottoResult.countRank(Rank.FIFTH);
        final Long noneRankCount = lottoResult.countRank(Rank.NONE);

        /* then */
        assertThat(firstRankCount).isEqualTo(0);
        assertThat(fifthRankCount).isEqualTo(2);
        assertThat(noneRankCount).isEqualTo(10);
    }

    @Test
    @DisplayName("수익률을 구한다.")
    void calculateTotalPrize() {
        /* given */
        LottoResult lottoResult = new LottoResult(target);
        int money = target.size() * 1_000;

        /* when */
        double rateOfReturn = lottoResult.calculateRateOfReturn(money);

        /* then */
        double expectedRateOfReturn = Rank.FIFTH.getPrize() * 2L / (double) money;
        assertThat(rateOfReturn).isEqualTo(expectedRateOfReturn);
    }
}
