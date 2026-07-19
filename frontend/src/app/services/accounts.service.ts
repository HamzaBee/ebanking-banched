import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AccountDetails, BankAccount } from '../model/account.model';

@Injectable({
  providedIn: 'root'
})
export class AccountsService {
  private http = inject(HttpClient);
  private backendUrl = 'http://localhost:8085';

  // Get paginated operations for a specific account
  public getAccount(accountId: string, page: number, size: number): Observable<AccountDetails> {
    return this.http.get<AccountDetails>(
      `${this.backendUrl}/accounts/${accountId}/pageOperations?page=${page}&size=${size}`
    );
  }

  // Get a single bank account by ID
  public getBankAccount(accountId: string): Observable<BankAccount> {
    return this.http.get<BankAccount>(`${this.backendUrl}/accounts/${accountId}`);
  }

  // Get all bank accounts
  public getBankAccounts(): Observable<BankAccount[]> {
    return this.http.get<BankAccount[]>(`${this.backendUrl}/accounts`);
  }

  // Debit operation on an account
  public debit(accountId: string, amount: number, description: string): Observable<any> {
    const data = { accountId, amount, description };
    return this.http.post(`${this.backendUrl}/accounts/debit`, data);
  }

  // Credit operation on an account
  public credit(accountId: string, amount: number, description: string): Observable<any> {
    const data = { accountId, amount, description };
    return this.http.post(`${this.backendUrl}/accounts/credit`, data);
  }

  // Transfer between two accounts
  public transfer(
    accountSource: string,
    accountDestination: string,
    amount: number,
    description: string
  ): Observable<any> {
    const data = { accountIdSource: accountSource, accountIdDestination: accountDestination, amount, description };
    return this.http.post(`${this.backendUrl}/accounts/transfer`, data);
  }
}
