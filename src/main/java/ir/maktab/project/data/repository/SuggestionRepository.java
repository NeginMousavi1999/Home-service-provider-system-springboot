package ir.maktab.project.data.repository;

import ir.maktab.project.data.entity.members.Expert;
import ir.maktab.project.data.entity.order.Order;
import ir.maktab.project.data.entity.order.Suggestion;
import ir.maktab.project.data.enumuration.SuggestionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
    Optional<List<Suggestion>> findBySuggestionStatusAndExpert(SuggestionStatus suggestionStatus, Expert expert);

    Optional<List<Suggestion>> findByExpert(Expert expert);

    Optional<List<Suggestion>> findByOrder(Order order);

    @Transactional
    @Modifying
    @Query(value = "update Suggestion s set s.suggestionStatus=:suggestionStatus where s.id=:id")
    void updateStatus(@Param("id") int id, @Param("suggestionStatus") SuggestionStatus suggestionStatus);
}
