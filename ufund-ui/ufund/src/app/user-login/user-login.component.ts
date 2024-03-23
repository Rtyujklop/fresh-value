import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-user-login',
  standalone: true,
  imports: [],
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  login(credentials: any): void {
    this.authService.login(credentials).subscribe(
      () => {
        // Redirect to the 'needs' route upon successful login
        this.router.navigate(['/needs']);
      },
      (error) => {
        // Handle login error if needed
        console.error('Login error:', error);
      }
    );
  }
}
