package com.eazybytes.accounts.audit;


import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

// Step 2 - to get values to CreatedBy and UpdatedBy we need to use implementation of "AuditorAware".
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
    
}
