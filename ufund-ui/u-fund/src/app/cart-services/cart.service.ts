import { Injectable } from '@angular/core';
import { Need } from '../need';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private items: { need: Need; quantity: number }[] = [];

  constructor(private router: Router) {
    // Load items from local storage when the service is instantiated
    const storedItems = localStorage.getItem('cartItems');
    if (storedItems) {
      this.items = JSON.parse(storedItems);
    }
  }

  addToCart(need: Need) {
    const item = this.items.find((item) => item.need.id === need.id);
    if (item) {
      item.quantity++;
    } else {
      this.items.push({ need, quantity: 1 });
    }
    // Save items to local storage whenever they are updated
    localStorage.setItem('cartItems', JSON.stringify(this.items));
  }

  getCartCount() {
    return this.items.reduce((count, item) => count + item.quantity, 0);
  }

  getNeeds() {
    return this.items;
  }

  deleteFromCart(need: Need) {
    const item = this.items.find((item) => item.need.id === need.id);
    if (item) {
      item.quantity--;
      if (item.quantity === 0) {
        this.items = this.items.filter((i) => i.need.id !== need.id);
      }
    }
    // Save items to local storage whenever they are updated
    localStorage.setItem('cartItems', JSON.stringify(this.items));
  }

  navigateBack() {
    this.router.navigate(['/user-view']);
  }

  checkout() {
    this.items = [];
    // Clear items from local storage when the cart is checked out
    localStorage.removeItem('cartItems');
  }

  getTotalPrice() {
    return this.items.reduce(
      (total, item) => total + item.need.cost * item.quantity,
      0
    );
  }

  getTotalItems() {
    return this.items.reduce((total, item) => total + item.quantity, 0);
  }

  updateCart(item: { need: Need; quantity: number }) {
    const index = this.items.findIndex((i) => i.need.id === item.need.id);
    if (index !== -1) {
      this.items[index] = item;
    }
    localStorage.setItem('cartItems', JSON.stringify(this.items));
  }
}
