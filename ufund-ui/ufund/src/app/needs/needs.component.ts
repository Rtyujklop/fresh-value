import { Component } from '@angular/core';
import { Need } from '../needs';
import { NgFor } from '@angular/common';
import { NeedServiceService } from '../need-service.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-needs',
  standalone: true,
  imports: [ NgFor ],
  templateUrl: './needs.component.html',
  styleUrl: './needs.component.css'
})
export class NeedsComponent {
  /*
  constructor(private needService: NeedServiceService)
  {}
  needs: Need[] = []

  ngOnInit(): void {
    this.getNeeds()
  }

  getNeeds(): void 
  {
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs);
  }
  */
}
