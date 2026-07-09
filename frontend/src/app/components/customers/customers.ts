import { Component, OnInit, signal, inject } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Customer } from '../../model/customer.model';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './customers.html',
  styleUrl: './customers.css',
})
export class Customers implements OnInit {
  private customerService = inject(CustomerService);
  private router = inject(Router);

  customers = signal<Array<Customer>>([]);
  errorMessage = signal<string>('');
  searchFormGroup = new FormGroup({
    keyword: new FormControl('', { nonNullable: true })
  });

  ngOnInit(): void {
    this.handleSearchCustomers();
  }
  handleSearchCustomers(): void {
    const kw = this.searchFormGroup.value.keyword || '';

    this.customerService.searchCustomers(kw).subscribe({
      next: (data) => {
        // Fallback to empty array if backend returns null/empty
        this.customers.set(data || []);
        this.errorMessage.set('');
      },
      error: (err) => {
        console.error('Failed to search customers:', err);
        this.errorMessage.set('Could not search customers from backend. Check if the server is running and CORS is enabled.');
      }
    });
  }

  handleDeleteCustomer(c: Customer): void {
    if (!c.id) return;
    const conf = confirm("Are you sure?");
    if (!conf) return;

    this.customerService.deleteCustomer(c.id).subscribe({
      next: () => {
        // Modern signal way to filter out deleted customer
        this.customers.update(data => data.filter(item => item.id !== c.id));
      },
      error: (err) => {
        console.error('Failed to delete customer:', err);
        alert('An error occurred while deleting the customer.');
      }
    });
  }
}














/*

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers.set(data);
      },
      error: (err) => {
        console.error('Failed to fetch customers:', err);
        this.errorMessage.set('Could not fetch customers from backend. Check if the server is running and CORS is enabled.');
      }
    });
  }

 */

