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

  login(username: String, password: string) {
    this.userService.getUser(username).subscribe(users => {
      const foundUser = users[0].username === username && users[0].password === password;
      console.log(users)
      console.log(foundUser)
      console.log(users[0].password)
      console.log(users[0].name)
      if (foundUser) {
        this.userService.setName(users[0].name);
        if (users[0].name === "Admin") {
          this.router.navigate(['../needs']);
        } else {
          this.router.navigate(['../userview']);
        }
        this.messageService.add("successful login");
      } else {
        this.messageService.add("Invalid username or password");
      }
    });
  }

  private setLog(message: string): void {
    this.log = message;
  }
}
