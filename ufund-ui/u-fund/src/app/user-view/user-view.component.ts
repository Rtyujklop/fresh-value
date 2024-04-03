import { Component, inject } from '@angular/core';
import { CartComponent } from '../cart/cart.component';
import { NeedDetailComponent } from '../need-detail/need-detail.component';
import { MessageService } from '../message.service';
import { NeedService } from '../need.service';
import { Need } from '../need';
import { FormsModule, NgModel } from '@angular/forms';
import { NgFor, NgIf, UpperCasePipe } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-user-view',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, UpperCasePipe, 
            NeedDetailComponent, CartComponent, RouterOutlet],
  templateUrl: './user-view.component.html',
  styleUrl: './user-view.component.css'
})
export class UserViewComponent {
  constructor(private messageService: MessageService)
  {
    
  }
  needService = inject(NeedService)

  ngOnInit(): void 
  {
    this.getNeeds();
  }

  needs: Need[] = [];
  selectedNeed?: Need;

  onSelect(need: Need): void 
  {
    this.selectedNeed = need;
    this.messageService.add(`NeedsComponent: Selected need id=${need.id}`);
  }

  getNeeds(): void 
  {
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs);
  }

  costFilter(): void
  {
    this.needs.sort((a,b) => a.cost - b.cost);
  }
}
