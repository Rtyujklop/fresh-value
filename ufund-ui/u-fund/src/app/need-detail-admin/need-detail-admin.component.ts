import { Component, Input } from '@angular/core';
import { Need } from '../need';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf, UpperCasePipe, Location } from '@angular/common';
import { NeedService } from '../need.service';

@Component({
  selector: 'app-need-detail-admin',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, UpperCasePipe],
  templateUrl: './need-detail-admin.component.html',
  styleUrl: './need-detail-admin.component.css',
})
export class NeedDetailAdminComponent {
  @Input() need?: Need;

  constructor(private needService: NeedService, private location: Location) {}

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
