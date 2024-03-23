import { Injectable } from '@angular/core';
import { Need } from '../need';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private items: Need[] = [];

  constructor() {}

  addToCart(need: Need) {
    this.items.push(need);
  }

  getNeeds() {
    return this.items;
  }

  deleteFromCart(need: Need) {
    this.items = this.items.filter((item) => item.id !== need.id);
  }
}
