import { Component } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { User } from '../user';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';
import { ActivatedRoute, Router } from '@angular/router';
import shajs from 'sha.js';

@Component({
  selector: 'app-login-view',
  standalone: true,
  imports: [],
  templateUrl: './login-view.component.html',
  styleUrl: './login-view.component.css'
})

export class LoginViewComponent {
  users: User[] = [];
  log: string = "";
  constructor(
    private userService: UserService,
    private messageService: MessageService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userService.getUsers();
  }

  switchPw(){
    const el = document.getElementById("password");
    if (el != null){
      if(el.getAttribute("type") != "text"){
        el.setAttribute("type", "password");
      }
    }
  }

  login(name: string, password: string){
    this.userService.getUsers();
    this.setLog("Logging in...");
    this.users.forEach(user => {
      if (user.name == name){
        let pass2 = shajs("sha256").update(String(name + password)).digest('hex');
        if (user.password == pass2){
          this.userService.setName(user.name);
          if (this.userService.getName() == "Admin") {
            this.router.navigate(['../needs'], { relativeTo: this.route});
          }
          else {
            this.router.navigate(['../userview'], { relativeTo: this.route});
          }
          this.setLog("successful login");
        } 
        else {
          this.setLog("Invaild passowrd");
        }
      }
    });
    if (this.log == "Logging in ...") {
      this.setLog("Username not found");
    }
  }

  private setLog(message: string): void {
    this.log = message;
    this.messageService.add(`LoginViewComponent: ${message}`);
  }
}