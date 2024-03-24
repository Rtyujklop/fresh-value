import { Component, OnInit, inject } from '@angular/core';
import { Need } from '../need';
import { FormsModule, NgModel } from '@angular/forms';
import { NgFor, NgIf, UpperCasePipe } from '@angular/common';
import { NeedDetailComponent } from '../need-detail/need-detail.component';
import { NeedService } from '../need.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-needs',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, UpperCasePipe, NeedDetailComponent],
  templateUrl: './needs.component.html',
  styleUrl: './needs.component.css'
})
export class NeedsComponent implements OnInit{

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

  addNeed(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.needService.addNeed({ name } as Need)
      .subscribe(need => {
        this.needs.push(need);
      });
  }

}
