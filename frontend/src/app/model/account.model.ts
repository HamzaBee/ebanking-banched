export type OperationType = 'DEBIT' | 'CREDIT';
export type AccountStatus = 'CREATED' | 'ACTIVATED' | 'SUSPENDED';

export interface AccountOperation {
  id: number;
  operationDate: Date;
  amount: number;
  type: OperationType;
  description: string;
}

export interface AccountDetails {
  accountId: string;
  balance: number;
  currentPage: number;
  totalPages: number;
  pageSize: number;
  accountOperationDTOS: AccountOperation[];
}

export interface BankAccount {
  id: string;
  balance: number;
  createdAt: Date;
  status: AccountStatus;
  customerDto: {
    id: number;
    name: string;
    email: string;
  };
  type: string;
  overDraft?: number;       // CurrentBankAccount
  interestRate?: number;    // SavingBankAccount
}
