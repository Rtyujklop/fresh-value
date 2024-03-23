import { Component, Input } from '@angular/core';
import { Need } from '../need';
import { FormsModule} from '@angular/forms';
import { NgFor, NgIf, UpperCasePipe } from '@angular/common';


@Component({
  selector: 'app-need-detail',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, UpperCasePipe],
  templateUrl: './need-detail.component.html',
  styleUrl: './need-detail.component.css'
})
export class NeedDetailComponent {
  @Input() need?: Need;
}
