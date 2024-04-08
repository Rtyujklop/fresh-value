import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private router: Router) {}

  login(credentials: any): Observable<any> {
    return this.http.post<any>('http://localhost:8080/login', credentials).pipe(
      tap((response) => {
        // Assuming the response contains a token or user information
        // You can store the token in local storage for future requests
        localStorage.setItem('token', response.token);
        // Navigate to the needs page upon successful login
        this.router.navigate(['/needs']);
      })
    );
  }
}
