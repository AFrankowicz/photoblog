import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { AppService } from '../services/app.service';
import { User } from "../models/user";
import { Router } from '@angular/router';

@Component({
    selector: 'blog-navbar',
    templateUrl: 'navbar.component.html'
})

export class NavbarComponent implements OnInit {
  constructor(private authService: AuthService, private appService: AppService,
    private router: Router,) { }

  ngOnInit(){
    var loggedUserMaybe = localStorage.getItem('loggedUser');
    if (loggedUserMaybe != null) {
      this.authService.loggedIn = true;
      this.appService.getUserByUsername(JSON.parse(loggedUserMaybe).username).subscribe(
        data => this.authService.loggedUser = data
      );
    }
  }
  logout(){
    localStorage.removeItem('loggedUser');
    this.authService.loggedIn = false;
    this.authService.loggedUser = null;
    this.router.navigateByUrl("/");
  }
}
