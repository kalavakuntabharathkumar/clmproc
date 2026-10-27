package com.portfolio.claims;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/reconciliations")
public class ReconciliationController {
    private final ClaimRepository claims;
    public ReconciliationController(ClaimRepository claims) { this.claims = claims; }

    @PostMapping
    public Map<String, Object> reconcile(@RequestBody ReconciliationRequest request) {
        Claim claim = claims.findById(request.claimId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claim not found"));
        if (claim.getStatus() != ClaimStatus.APPROVED)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Only approved claims can be paid");
        if (request.paidAmount() == null || request.paidAmount().signum() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paid amount must be non-negative");
        claim.setPaidAmount(request.paidAmount());
        claim.setStatus(ClaimStatus.PAID);
        claims.save(claim);
        BigDecimal variance = claim.getAmount().subtract(request.paidAmount());
        return Map.of("claimId", claim.getId(), "claimAmount", claim.getAmount(), "paidAmount", request.paidAmount(), "variance", variance, "status", "PAID");
    }
    public record ReconciliationRequest(Long claimId, BigDecimal paidAmount) {}
}