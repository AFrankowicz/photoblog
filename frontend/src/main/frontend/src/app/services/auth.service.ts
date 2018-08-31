import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { AppService } from './app.service';
import { User } from "../models/user";

@Injectable()
export class AuthService implements CanActivate {
  loggedIn = false;
  loggedUser: User;

  constructor(private router: Router, private http: Http, private appService: AppService ) {
  }

  ngInit () { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (localStorage.getItem('loggedUser')) {
      return true;
    }
    else{
      this.router.navigate(['/login']);
      return false;
    }
  }

  login(user){
    var headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa('client:clientpassword')
    });

    var options = new RequestOptions({
      headers: headers
    });

    return this.http.post("/oauth/token?grant_type=password&username="
    +user.username+"&password="
    +user.password, null, options
    ).map(
      (res:Response) => res.json()
    );
  }
}
