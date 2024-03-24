import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

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
    private router: Router
  ) { }

  login() {
    this.userService.getUsers().subscribe(users => {
      const foundUser = users.find(user => user.name === this.username && user.password === this.password);
      if (foundUser) {
        this.userService.setName(foundUser.name);
        if (foundUser.name === "Admin") {
          this.router.navigate(['../needs']);
        } else {
          this.router.navigate(['../userview']);
        }
        this.setLog("successful login");
      } else {
        this.setLog("Invalid username or password");
      }
    });
  }

  private setLog(message: string): void {
    this.log = message;
  }
}
