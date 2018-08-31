import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from '../models/post';
import { AppService } from '../services/app.service';
import { Comment } from '../models/comment';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html'
})
export class PostComponent implements OnInit {
  postId: number;
  post: Post;
  comments = [];
  commentError = false;
  newComment: Comment;
  errorMessage = "";
  currentLoggedUser: string;


  constructor(private route: ActivatedRoute, private appService: AppService) {
    this.newComment = new Comment();
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
       this.postId = params['id'];

       var loggedUserMaybe = localStorage.getItem('loggedUser');
       if (loggedUserMaybe != null) {
           this.currentLoggedUser = JSON.parse(loggedUserMaybe).username;
       }

       this.appService.getPostById(this.postId).subscribe(
         data => {
           this.post = data;
           console.log("getcomments");
           this.appService.getComments(this.postId).subscribe(
             data => this.comments = data
           );
         }
       );
     });
  }

  isCurrentLoggedUser(username){
    return this.currentLoggedUser === username;
  }

  addComment() {
    if (!this.newComment.hasAllProp() || this.newComment.isAnyEmpty()){
      this.commentError = true;
      this.errorMessage = "Text of the comment cannot be empty.";
    } else {
      this.commentError = false;
      this.appService.publishComment(this.postId, this.newComment).subscribe(post => {
        this.appService.getComments(this.postId).subscribe(
          data => {
            this.comments = data;
            console.log(data);
        }
        );
      }, error => {
          this.commentError = true;
          this.errorMessage = error.error;
      });
    }
  }

  deleteComment(id){
    this.appService.deleteComment(this.postId, id).subscribe(del => {
      this.appService.getComments(this.postId).subscribe(
        data => {
          this.comments = data;
          console.log(data);
      });
    });
  }

}
