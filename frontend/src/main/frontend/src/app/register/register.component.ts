import { Component, OnInit,  ElementRef, ViewChild } from '@angular/core';
import { User } from '../models/user';
import { AppService } from '../services/app.service';
import { AuthService } from '../services/auth.service';
import { FormsModule, FormGroup }   from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {
  form: FormGroup;
  user: User;
  registerError = false;
  errorMessage = "";
  confirmPassword = ""

  // @ViewChild('fileInput') fileInput: ElementRef;

  constructor(private appService: AppService, private router: Router,
     private authService: AuthService ) {
      this.user = new User();
  }

  ngOnInit() {
  }

  onFileChange(event) {
    let reader = new FileReader();
    if(event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.user.avatar = reader.result.split(',')[1]
      };
    }
  }

  register(){
    if (!this.user.hasAllProp() || this.user.isAnyEmpty()){
      this.registerError = true;
      this.errorMessage = "All fields must be filled.";
    } else if (this.user.password != this.confirmPassword) {
      this.registerError = true;
      this.errorMessage = "Passwords doesn't match.";
    } else {
      this.registerError = false;
        this.appService.postUser(this.user).subscribe(user => {
          this.authService.login(this.user).subscribe(data => {
                this.authService.loggedIn = true;
                this.authService.loggedUser = user;
                localStorage.setItem('loggedUser', JSON.stringify({username: user.username, token: data.access_token }));
                this.router.navigateByUrl("/user/" + user.username);
          });
        }, error => {
            this.registerError = true;
            this.errorMessage = error.error;
        })
    }
  }
}
