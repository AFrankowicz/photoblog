import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { AppService } from '../services/app.service';
import { AuthService } from '../services/auth.service';
import { FormsModule }   from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  user: User;
  loginError = false;
  errorMessage = "Wrong username or password";

  constructor(private appService: AppService, private router: Router,
    private authService: AuthService) {
    this.user = new User();
  }

  ngOnInit() { }

  login() {
    this.authService.login(this.user).subscribe(data => {
          this.loginError = false;
          this.authService.loggedIn = true;
          this.appService.getUserByUsername(this.user.username).subscribe(
            data => this.authService.loggedUser = data
          );
          localStorage.setItem('loggedUser', JSON.stringify({username: this.user.username, token: data.access_token }));
          this.router.navigateByUrl("/");
    }, error => {
        this.loginError = true;
    });
  }
}
