package com.portfolio.claims;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "claims", indexes = {
    @Index(name = "idx_claims_status", columnList = "status"),
    @Index(name = "idx_claims_member_date", columnList = "member_id, service_date")
})
public class Claim {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(name = "member_id", nullable = false, length = 40)
    private String memberId;
    @NotBlank @Column(name = "provider_name", nullable = false, length = 120)
    private String providerName;
    @NotNull @PositiveOrZero @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;
    @NotNull @Column(name = "service_date", nullable = false)
    private LocalDate serviceDate;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 24)
    private ClaimStatus status = ClaimStatus.SUBMITTED;
    @Column(name = "paid_amount", precision = 12, scale = 2)
    private BigDecimal paidAmount = BigDecimal.ZERO;
    protected Claim() {}
    public Claim(String memberId, String providerName, BigDecimal amount, LocalDate serviceDate) {
        this.memberId = memberId; this.providerName = providerName; this.amount = amount; this.serviceDate = serviceDate;
    }
    public Long getId() { return id; }
    public String getMemberId() { return memberId; }
    public String getProviderName() { return providerName; }
    public BigDecimal getAmount() { return amount; }
    public LocalDate getServiceDate() { return serviceDate; }
    public ClaimStatus getStatus() { return status; }
    public BigDecimal getPaidAmount() { return paidAmount; }
    public void setStatus(ClaimStatus status) { this.status = status; }
    public void setPaidAmount(BigDecimal paidAmount) { this.paidAmount = paidAmount; }
}