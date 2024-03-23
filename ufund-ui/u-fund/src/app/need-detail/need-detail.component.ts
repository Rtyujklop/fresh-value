import { Component, Input } from '@angular/core';
import { Need } from '../need';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf, UpperCasePipe } from '@angular/common';
import { CartService } from '../cart-services/cart.service';

@Component({
  selector: 'app-need-detail',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, UpperCasePipe],
  templateUrl: './need-detail.component.html',
  styleUrl: './need-detail.component.css',
})
export class NeedDetailComponent {
  @Input() need?: Need;

  constructor(private cartService: CartService) {}
  addToCart(need: Need) {
    this.cartService.addToCart(need);
  }
}
