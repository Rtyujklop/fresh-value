import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { CartService } from '../cart-services/cart.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  currentUrl: string | undefined;
  hideNavbar: boolean = false;

  constructor(private router: Router, public cartService: CartService) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.currentUrl = event.urlAfterRedirects;
        this.hideNavbar = this.currentUrl.includes('login');
      }
    });
  }

  navigateTo(path: string) {
    this.router.navigate([path]);
  }
}
