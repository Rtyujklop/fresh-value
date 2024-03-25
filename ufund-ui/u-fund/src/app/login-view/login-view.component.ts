import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { MessageService } from '../message.service';
@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css']
})
export class LoginViewComponent {
  username: string = '';
  password: string = '';
  log: string = "";

  constructor(
    private userService: UserService,
    private router: Router,
    private messageService: MessageService
  ) { }

  login(username: string, password: string) {
    this.userService.getUser(username).subscribe(users => {
      const foundUser = users[0].username === username && users[0].password === password;
      console.log(users)
      if (foundUser) {
        this.userService.setName(users[0].username);
        if (users[0].username === "Admin") {
          this.router.navigate(['../needs']);
        } else {
          this.router.navigate(['../user-view']);
        }
        this.messageService.add("successful login");
      } else {
        this.messageService.add("Invalid username or password");
      }
    });
  }
}
