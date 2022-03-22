package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    //플21 | 딜 20(승), 플 20 | 딜 22(승)
    // 플21 | 딜 21(무)
    // 플22 | 딜 21(패), 플20 | 딜21(패), 플22 | 딜 23(패)

    @Test
    @DisplayName("플레이어의 점수와 딜러의 점수 모두 21보다 큰 경우")
    void findLoseResultGamerPointAndDealerPointOutBlackjack() {
        //given
        Dealer dealer = new Dealer();
        get_22_Point(dealer);

        Gamer gamer = settingGamer();
        get_23_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(finalResultBoard.get(gamer)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 작거나 같지만, 딜러의 점수보다 작은 경우")
    void findLoseResultGamerPointInBlackJackAndLowerDealerPoint() {
        //given
        Dealer dealer = new Dealer();
        get_21_Point(dealer);

        Gamer gamer = settingGamer();
        get_20_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(finalResultBoard.get(gamer)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 큰 경우")
    void findLoseResultGamerPointOutBlackJack() {
        //given
        Dealer dealer = new Dealer();
        get_21_Point(dealer);

        Gamer gamer = settingGamer();
        get_22_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(finalResultBoard.get(gamer)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 같거나 작고, 딜러의 점수가 21보다 큰 경우")
    void findDrawResult() {
        //given
        Dealer dealer = new Dealer();
        get_21_Point(dealer);

        Gamer gamer = settingGamer();
        get_21_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(finalResultBoard.get(gamer)).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 같거나 작고, 딜러의 점수가 21보다 큰 경우")
    void findWinningResultDealerPointOutBlackJack() {
        //given
        Dealer dealer = new Dealer();
        get_22_Point(dealer);

        Gamer gamer = settingGamer();
        get_20_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(finalResultBoard.get(gamer)).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어의 점수가 21보다 같거나 작고 딜러의 점수가 플레이어의 점수보다 작은 경우")
    void findWinningResultDealerPointInBlackJack() {
        //given
        Dealer dealer = new Dealer();
        get_19_Point(dealer);

        Gamer gamer = settingGamer();
        get_20_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(finalResultBoard.get(gamer)).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어의 점수가 21이고 딜러의 점수가 플레이어의 점수보다 작은 경우")
    void findBlackJackResultDealerPointInBlackJack() {
        //given
        Dealer dealer = new Dealer();
        get_20_Point(dealer);

        Gamer gamer = settingGamer();
        get_21_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(finalResultBoard.get(gamer)).isEqualTo(15000);
    }

    @Test
    @DisplayName("플레이어의 점수가 21이고 딜러의 점수가 21보다 큰 경우")
    void findBlackJackResultDealerPointOutBlackJack() {
        //given
        Dealer dealer = new Dealer();
        get_23_Point(dealer);

        Gamer gamer = settingGamer();
        get_21_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(finalResultBoard.get(gamer)).isEqualTo(15000);
    }

    @Test
    @DisplayName("딜러의 수익을 계산한다.")
    void calculateGamersProfit() {
        //given
        Dealer dealer = new Dealer();
        get_21_Point(dealer);

        Gamer gamer = settingGamer();
        get_20_Point(gamer);
        //when
        Map<Gamer, Long> finalResultBoard = GameResult
            .calculateGamersProfit(dealer, List.of(gamer));
        //then
        assertThat(GameResult.calculateDealerProfit(finalResultBoard)).isEqualTo(10000);
    }

    @Test
    @DisplayName("여러 플레이어들의 수익을 계산할 때, 입력 순서대로 정렬되어 있는지 확인한다.")
    void calculateGamersAndDealerProfit() {
        //given
        Dealer dealer = new Dealer();
        get_20_Point(dealer);

        Gamer gamer1 = settingGamer();
        get_19_Point(gamer1);
        Gamer gamer2 = settingGamer();
        get_20_Point(gamer2);
        Gamer gamer3 = settingGamer();
        get_21_Point(gamer3);
        //when
        Map<Gamer, Long> gamersProfit = GameResult
            .calculateGamersProfit(dealer, List.of(gamer1, gamer2, gamer3));
        //then
        assertThat(new ArrayList<>(gamersProfit.keySet()))
            .isEqualTo(List.of(gamer1, gamer2, gamer3));
    }

    private Gamer settingGamer() {
        return new Gamer("judy", 10000);
    }

    void get_19_Point(Player player) {
        player.receiveCard(new Card(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.NINE));
    }

    void get_20_Point(Player player) {
        player.receiveCard(new Card(Suit.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
    }

    void get_21_Point(Player player) {
        player.receiveCard(new Card(Suit.HEART, Denomination.JACK));
        player.receiveCard(new Card(Suit.HEART, Denomination.ACE));
    }

    void get_22_Point(Player player) {
        player.receiveCard(new Card(Suit.HEART, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.TWO));
    }

    void get_23_Point(Player player) {
        player.receiveCard(new Card(Suit.HEART, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.JACK));
        player.receiveCard(new Card(Suit.SPADE, Denomination.THREE));
    }

}