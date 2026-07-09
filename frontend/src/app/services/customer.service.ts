import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../model/customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private http = inject(HttpClient);
  private backendUrl = 'http://localhost:8085/customers';

  // Fetch all customers
  public getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.backendUrl);
  }

  // Search customers by keyword
  public searchCustomers(keyword: string): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.backendUrl}/search?keyword=${keyword}`);
  }

  // Save a new customer
  public saveCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.backendUrl, customer);
  }

  // Delete a customer
  public deleteCustomer(id: number): Observable<any> {
    return this.http.delete<any>(`${this.backendUrl}/${id}`);
  }
}
