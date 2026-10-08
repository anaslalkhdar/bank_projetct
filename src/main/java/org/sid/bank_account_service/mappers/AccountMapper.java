package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.Bankaccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(Bankaccount bankAccount) {
        BankAccountResponseDTO responseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, responseDTO);
        return responseDTO;
    }

    public Bankaccount fromBankAccountRequestDTO(BankAccountRequestDTO requestDTO) {
        Bankaccount bankAccount = new Bankaccount();
        BeanUtils.copyProperties(requestDTO, bankAccount);
        return bankAccount;
    }
}
