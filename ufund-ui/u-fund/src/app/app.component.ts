import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NeedsComponent, NeedDetailComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'u-fund';
}
