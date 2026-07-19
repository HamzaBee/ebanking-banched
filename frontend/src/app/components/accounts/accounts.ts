import { Component, OnInit, signal, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { DatePipe, DecimalPipe } from '@angular/common';
import { AccountsService } from '../../services/accounts.service';
import { AccountDetails } from '../../model/account.model';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [ReactiveFormsModule, DatePipe, DecimalPipe],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class Accounts implements OnInit {
  private accountService = inject(AccountsService);

  accountDetails = signal<AccountDetails | null>(null);
  errorMessage = signal<string>('');
  currentPage = signal<number>(0);
  pageSize: number = 5;

  accountFormGroup = new FormGroup({
    accountId: new FormControl('', { nonNullable: true })
  });

  operationFromGroup = new FormGroup({
    operationType:      new FormControl('', { nonNullable: true }),
    amount:             new FormControl(0, { nonNullable: true }),
    description:        new FormControl('', { nonNullable: true }),
    accountDestination: new FormControl('', { nonNullable: true })
  });

  ngOnInit(): void {
    // forms initialized above as class properties
  }

  handleSearchAccount(): void {
    const accountId: string = this.accountFormGroup.value.accountId || '';

    this.accountService.getAccount(accountId, this.currentPage(), this.pageSize).subscribe({
      next: (data) => {
        this.accountDetails.set(data);
        this.errorMessage.set('');
      },
      error: (err) => {
        console.error('Failed to fetch account:', err);
        this.errorMessage.set('Could not load account. Check if the server is running and CORS is enabled.');
      }
    });
  }

  gotoPage(page: number): void {
    this.currentPage.set(page);
    this.handleSearchAccount();
  }

  handleAccountOperation(): void {
    const accountId: string        = this.accountFormGroup.value.accountId || '';
    const operationType            = this.operationFromGroup.value.operationType;
    const amount: number           = this.operationFromGroup.value.amount || 0;
    const description: string      = this.operationFromGroup.value.description || '';
    const accountDestination: string = this.operationFromGroup.value.accountDestination || '';

    if (operationType === 'DEBIT') {
      this.accountService.debit(accountId, amount, description).subscribe({
        next: (data) => {
          alert('Success Debit');
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          console.log(err);
        }
      });
    } else if (operationType === 'CREDIT') {
      this.accountService.credit(accountId, amount, description).subscribe({
        next: (data) => {
          alert('Success Credit');
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          console.log(err);
        }
      });
    } else if (operationType === 'TRANSFER') {
      this.accountService.transfer(accountId, accountDestination, amount, description).subscribe({
        next: (data) => {
          alert('Success Transfer');
          this.operationFromGroup.reset();
          this.handleSearchAccount();
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
  }
}
