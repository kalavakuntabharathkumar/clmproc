package com.portfolio.claims;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClaimController {
    private final ClaimRepository claims;
    public ClaimController(ClaimRepository claims) { this.claims = claims; }

    @GetMapping("/health")
    public Map<String, String> health() { return Map.of("status", "ok"); }

    @GetMapping("/claims")
    public List<Claim> all(@RequestParam(required = false) ClaimStatus status) {
        return status == null ? claims.findAll() : claims.findByStatus(status);
    }

    @GetMapping("/claims/{id}")
    public Claim one(@PathVariable Long id) {
        return claims.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Claim not found"));
    }

    @PostMapping("/claims")
    @ResponseStatus(HttpStatus.CREATED)
    public Claim create(@Valid @RequestBody ClaimRequest request) {
        return claims.save(new Claim(request.memberId(), request.providerName(), request.amount(), request.serviceDate()));
    }

    @PatchMapping("/claims/{id}/status")
    public Claim updateStatus(@PathVariable Long id, @RequestBody StatusRequest request) {
        Claim claim = one(id);
        ClaimStatus next;
        try { next = ClaimStatus.valueOf(request.status().toUpperCase()); }
        catch (Exception ex) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status"); }
        if (claim.getStatus() == ClaimStatus.PAID || claim.getStatus() == ClaimStatus.REJECTED)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Terminal claim status");
        claim.setStatus(next);
        return claims.save(claim);
    }

    public record ClaimRequest(String memberId, String providerName, java.math.BigDecimal amount, java.time.LocalDate serviceDate) {}
    public record StatusRequest(String status) {}
}