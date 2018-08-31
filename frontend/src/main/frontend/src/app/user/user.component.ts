import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../models/user';
import { AppService } from '../services/app.service';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  providers : [AppService]
})
export class UserComponent implements OnInit {
  username: string;
  user: User;
  posts = [];
  age: number;
  galleryEmpty: boolean;
  isCurrentLoggedUser = false;

  constructor(private route: ActivatedRoute, private appService: AppService) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
       this.username = params['username'];

       this.appService.getUserByUsername(this.username).subscribe(
         data => {
           this.user = data;
           var curYear = (new Date()).getFullYear();
           var born = new Date(data.born).getFullYear();
           this.age = curYear - born;
         }
       );

       this.appService.getPostsByUsername(this.username).subscribe(
         data => {
           if (data && data.length) {
             this.galleryEmpty = false;
             this.posts = data
           } else {
             this.galleryEmpty = true;
           }
         }
       );
    });
  }

}
