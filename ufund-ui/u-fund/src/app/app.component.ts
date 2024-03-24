import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { CartComponent } from './cart/cart.component';
import { MessagesComponent } from './messages/messages.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NeedsComponent, NeedDetailComponent, CartComponent, MessagesComponent, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'u-fund';
}
