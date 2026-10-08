package org.sid.bank_account_service.entities;

import org.sid.bank_account_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1", types = Bankaccount.class)
public interface AccountProjection {
    String getId();

    AccountType getType();

    Double getBalance();
}
