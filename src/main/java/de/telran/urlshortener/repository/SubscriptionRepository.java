package de.telran.urlshortener.repository;

import de.telran.urlshortener.model.entity.subscription.Subscription;
import lombok.Getter;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s WHERE s.expirationDate >= CURRENT_DATE")
    List<SubscriptionRepository> findAllActualSubscriptions();

    @EntityGraph(value = "Subscription.withUser", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Subscription> findByIdWithUser(Long id);
}
