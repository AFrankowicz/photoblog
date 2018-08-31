import { Component, OnInit } from '@angular/core';
import { AppService } from '../services/app.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit {
  posts = [];
  constructor(private appService: AppService) {}

  ngOnInit() {
    this.appService.getPosts().subscribe(
      data => this.posts = data
    );
  }

}
