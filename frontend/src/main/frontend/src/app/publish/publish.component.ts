import { Component, OnInit,  ElementRef, ViewChild } from '@angular/core';
import { Post } from '../models/post';
import { AppService } from '../services/app.service';
import { AuthService } from '../services/auth.service';
import { FormsModule, FormGroup }   from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-publish',
  templateUrl: './publish.component.html'
})
export class PublishComponent implements OnInit {
  form: FormGroup;
  post: Post;
  publishError = false;
  errorMessage = "";

  constructor(private appService: AppService, private router: Router,
     private authService: AuthService ) {
      this.post = new Post();
  }

  ngOnInit() {
  }

  onFileChange(event) {
    let reader = new FileReader();
    if(event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.post.content = reader.result.split(',')[1]
      };
    }
  }

  publish(){
    if (!this.post.hasAllProp() || this.post.isAnyEmpty()){
      this.publishError = true;
      this.errorMessage = "All fields must be filled.";
    } else {
      this.publishError = false;
        this.appService.publishPost(this.post).subscribe(post => {
          console.log(post);
          this.router.navigateByUrl("post/" + post.id);
        }, error => {
            this.publishError = true;
            this.errorMessage = error.error;
        })
    }
  }

}
