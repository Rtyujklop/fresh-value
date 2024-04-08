import { Component, inject } from '@angular/core';
import { CartComponent } from '../cart/cart.component';
import { NeedDetailComponent } from '../need-detail/need-detail.component';
import { MessageService } from '../message.service';
import { NeedService } from '../need.service';
import { Need } from '../need';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf, UpperCasePipe } from '@angular/common';
import { RouterOutlet } from '@angular/router'; 
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-view',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, UpperCasePipe, 
            NeedDetailComponent, CartComponent, RouterOutlet],
  templateUrl: './user-view.component.html',
  styleUrl: './user-view.component.css'
})
export class UserViewComponent {
  nameFilterValue: string = '';
  constructor(private userService: UserService)
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
  }

  getNeeds(): void 
  {
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs);
  }

  costFilterAsc(): void
  {
    this.needs.sort((a,b) => a.cost - b.cost);
  }

  costFilterDes(): void
  {
    this.needs.sort((a,b) => b.cost - a.cost);
  }

  ageFilterAsc(): void 
  {
    this.needs.sort((a,b) => a.age - b.age);
  }

  ageFilterDes(): void
  {
    this.needs.sort((a,b) => b.age - a.age);
  }

  nameFilter(): void
  {
    const queue = this.nameFilterValue.trim().toLowerCase();
    if (!queue) {
      this.getNeeds(); // Reset to original list if queue is empty
      return;
    }
    this.needs = this.needs.filter(need =>
      need.name.toLowerCase().includes(queue)
    );
  }
  
  isUser(): Boolean
  {
    return this.userService.isUser();
  }

  deleteToken(): void 
  {
    localStorage.removeItem('token');
  }
}
