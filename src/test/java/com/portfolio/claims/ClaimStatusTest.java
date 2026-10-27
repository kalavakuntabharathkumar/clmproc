package com.portfolio.claims;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ClaimStatusTest {
    @Test void workflowIncludesExpectedStates() {
        assertEquals(5, ClaimStatus.values().length);
        assertTrue(java.util.Arrays.asList(ClaimStatus.values()).contains(ClaimStatus.APPROVED));
        assertTrue(java.util.Arrays.asList(ClaimStatus.values()).contains(ClaimStatus.PAID));
    }
}