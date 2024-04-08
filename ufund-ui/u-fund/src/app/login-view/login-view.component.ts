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
    private router: Router
  ) { }

  login(username: string, password: string) {
    this.userService.getUser(username).subscribe(users => {
      if(users[0] === undefined)
      {
        alert("Incorrect username or password. Please try again.");
      }
      else
      {
        const foundUser = users[0].username === username && users[0].password === password;
        if (foundUser) {
          if (users[0].username === "Admin") 
          {
            this.router.navigate(['../needs']);
            localStorage.setItem('token', 'admin-token');
          } else 
          {
            localStorage.setItem('token', 'user-token');
            this.router.navigate(['../user-view']);
          }
          alert("Successful login!");
        } else
        {
          alert("Invalid username or password. Please try again.");
        }
      }
    });
  }
}
0