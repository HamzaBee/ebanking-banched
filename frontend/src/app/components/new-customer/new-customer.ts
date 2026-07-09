import { Component, inject } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { Customer } from '../../model/customer.model';

@Component({
  selector: 'app-new-customer',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './new-customer.component.html',
  styleUrl: './new-customer.component.css'
})
export class NewCustomerComponent {
  private fb = inject(FormBuilder);
  private customerService = inject(CustomerService);
  private router = inject(Router);

  // Initialize the FormGroup directly at the class level
  newCustomerFormGroup = this.fb.group({
    name: this.fb.control('', { nonNullable: true, validators: [Validators.required, Validators.minLength(4)] }),
    email: this.fb.control('', { nonNullable: true, validators: [Validators.required, Validators.email] })
  });

  handleSaveCustomer(): void {
    if (this.newCustomerFormGroup.invalid) return;

    const customer: Customer = this.newCustomerFormGroup.value as Customer;
    
    this.customerService.saveCustomer(customer).subscribe({
      next: (data) => {
        alert("Customer has been successfully saved!");
        this.router.navigateByUrl("/customers");
      },
      error: (err) => {
        console.error('Failed to save customer:', err);
        alert("An error occurred while saving the customer.");
      }
    });
  }
}
