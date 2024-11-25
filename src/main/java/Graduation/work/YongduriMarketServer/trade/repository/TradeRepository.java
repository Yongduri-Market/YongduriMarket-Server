package Graduation.work.YongduriMarketServer.trade.repository;

import Graduation.work.YongduriMarketServer.domain.User;
import Graduation.work.YongduriMarketServer.domain.state.SalesStatus;
import Graduation.work.YongduriMarketServer.trade.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query(value = "SELECT * FROM trade WHERE (buyer_id = :buyerId OR :buyerId IS NULL) AND (seller_id = :sellId OR :sellId IS NULL)", nativeQuery = true)
    List<Trade> findAllByBuyerIdAndSellerId(@Param(value = "buyerId") Long buyerId, @Param(value = "sellId") Long sellId);

}
