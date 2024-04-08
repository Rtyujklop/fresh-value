import { Component, OnInit, inject } from '@angular/core';
import { Need } from '../need';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf, UpperCasePipe } from '@angular/common';
import { NeedDetailComponent } from '../need-detail/need-detail.component';
import { NeedService } from '../need.service';
import { MessageService } from '../message.service';
import { NeedDetailAdminComponent } from '../need-detail-admin/need-detail-admin.component';

@Component({
  selector: 'app-needs',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, UpperCasePipe, 
            NeedDetailComponent, NeedDetailAdminComponent],
  templateUrl: './needs.component.html',
  styleUrl: './needs.component.css'
})
export class NeedsComponent implements OnInit{
  nameFilterValue: string = '';
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
      .subscribe(needs => this.needs = needs)
  }

  addNeed(name: string, costNumber: string, description: string, ageNumber: string): void {

    const cost: Number = parseInt(costNumber);
    const age: Number = parseInt(ageNumber);
    name = name.trim();
    if (!name) { return; }
    this.needService.addNeed({ name, cost, description, age } as Need)
      .subscribe(need => {
        this.needs.push(need);
      });
  }

  deleteNeed(need: Need): void 
  {
    this.needs = this.needs.filter(n => n !== need);
    this.needService.deleteNeed(need.id).subscribe();
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

  deleteToken(): void 
  {
    localStorage.removeItem('token');
  }
}
