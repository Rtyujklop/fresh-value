import { Component, Input } from '@angular/core';
import { Need } from '../need';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf, UpperCasePipe, Location } from '@angular/common';
import { CartService } from '../cart-services/cart.service';
import { NeedService } from '../need.service';

@Component({
  selector: 'app-need-detail',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, UpperCasePipe],
  templateUrl: './need-detail.component.html',
  styleUrl: './need-detail.component.css',
})
export class NeedDetailComponent {
  @Input() need?: Need;

  constructor(private cartService: CartService, private needService: NeedService, private location: Location) {}
  addToCart(need: Need) {
    this.cartService.addToCart(need);
  }

  save(): void {
    if (this.need) {
      this.needService.updateNeed(this.need)
        .subscribe(() => this.goBack());
    }
  }

  goBack(): void {
    this.location.back();
  }
}

  

