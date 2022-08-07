package pl.oli.cantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.oli.cantor.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
