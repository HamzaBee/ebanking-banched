package ma.enset.ebankingbanched.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CurrentBankAccountDto extends BankAccountDto {
    private double overDraft;
}
