import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Need } from '../need';
import { CartService } from '../cart-services/cart.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  cartService = inject(CartService);

  deleteFromCart(need: Need) {
    this.cartService.deleteFromCart(need);
  }

  checkout() {
    this.cartService.checkout();
    window.alert('Thank you for your Donation!');
  }
}
