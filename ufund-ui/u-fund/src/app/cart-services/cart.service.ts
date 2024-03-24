import { Injectable } from '@angular/core';
import { Need } from '../need';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private items: { need: Need; quantity: number }[] = [];

  constructor() {
    this.loadCart();
  }

  addToCart(need: Need) {
    const item = this.items.find((item) => item.need.id === need.id);
    if (item) {
      item.quantity++;
    } else {
      this.items.push({ need, quantity: 1 });
    }
    this.saveCart();
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
    this.saveCart();
  }

  private saveCart() {
    localStorage.setItem('cart', JSON.stringify(this.items));
  }

  private loadCart() {
    const storedCart = localStorage.getItem('cart');
    if (storedCart) {
      this.items = JSON.parse(storedCart);
    } else {
      this.items = [];
    }
  }
}
