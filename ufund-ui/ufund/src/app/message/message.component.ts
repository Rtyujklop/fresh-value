import { Component } from '@angular/core';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-message',
  standalone: true,
  imports: [],
  templateUrl: './message.component.html',
  styleUrl: './message.component.css'
})
export class MessageComponent {

  constructor(public messageService: MessageService) {}

}
