package com.portfolio.claims;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByStatus(ClaimStatus status);
}