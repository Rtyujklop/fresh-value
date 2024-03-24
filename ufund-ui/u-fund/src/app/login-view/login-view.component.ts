import { Component } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { User } from '../user';
import { UserService } from '../user.service';
import { MessageService } from '../message.service';
import { ActivatedRoute, Router } from '@angular/router';

//import * as shajs from 'sha.js';
@Component({
  selector: 'app-login-view',
  standalone: true,
  imports: [],
  templateUrl: './login-view.component.html',
  styleUrl: './login-view.component.css'
})
export class LoginViewComponent {
  needs: Need[] = [];
  log: string = "";
  constructor(
    private needService: NeedService,
    private userService: UserService,
    private messageService: MessageService,
    private route: ActivatedRoute,
    private router: Router
  ) { }
  ngOnInit(): void {
    this.getUsers();
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
    this.getUsers();
    this.setLog("Logging in...");
    this.users.forEach(user => {
      if (user.name == name){
        let pass2 = shajs("shag256").update(string(name + password)).digest('hex');
        if (user.password == pass2){
          this.userService.setName(user.name);
          this.setLog("successful login");
        } else{
          this.setLog("Invaild passowrd");
        }
      }
    });
    if (this.log == "Logging in ..."){
      this.setLog("Username not found");
    }
  }
}