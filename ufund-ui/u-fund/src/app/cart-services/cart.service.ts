import { Injectable } from '@angular/core';
import { Need } from '../need';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private items: { need: Need; quantity: number }[] = [];

  constructor() {}

  addToCart(need: Need) {
    const item = this.items.find((item) => item.need.id === need.id);
    if (item) {
      item.quantity++;
    } else {
      this.items.push({ need, quantity: 1 });
    }
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
  }

  checkout() {
    this.items = [];
  }
}
