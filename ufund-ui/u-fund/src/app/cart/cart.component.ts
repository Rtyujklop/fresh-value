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

  checkout() {
    this.cartService.checkout();
    window.alert('Thank you for your Donation!');
    this.cartService.navigateBack();
  }

  increaseQuantity(item: { need: Need; quantity: number }) {
    item.quantity++;
    this.cartService.updateCart(item);
  }

  decreaseQuantity(item: { need: Need; quantity: number }) {
    if (item.quantity > 1) {
      item.quantity--;
      this.cartService.updateCart(item);
    } else {
      this.cartService.deleteFromCart(item.need);
    }
  }
}
