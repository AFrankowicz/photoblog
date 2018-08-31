import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Post } from '../models/post';
import { User } from '../models/user';
import { Comment } from '../models/comment';
import { serialize } from 'json-typescript-mapper';

import 'rxjs/add/operator/map';

@Injectable()
export class AppService {

  constructor ( private http: Http ) {}

  getCachedToken(){
    var token = "";
    var loggedUserMaybe = localStorage.getItem('loggedUser');
    if (loggedUserMaybe != null) {
      token = JSON.parse(loggedUserMaybe).token
    }
    return token;
  }

  getAuthOptions(token){
    var headers = new Headers({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token
    });
    var options = new RequestOptions({
      headers: headers
    });
    return options;
  }

  getPosts() {
    return this.http.get("/api/posts").map(
      (res:Response) => res.json().map(post => <Post>post));
  }

  getPostById(id) {
    return this.http.get("/api/posts/" + id).map(
      (res:Response) => <Post>res.json());
  }

  getUserByUsername(username) {
    return this.http.get("/api/accounts/" + username).map(
      (res:Response) => <User>res.json());
  }

  getPostsByUsername(username) {
    return this.http.get("/api/accounts/" + username + "/posts").map(
      (res:Response) => res.json().map(post => <Post>post));
  }

  postUser(user) {
    var headers = new Headers({
      'Content-Type': 'application/json'
    });
    var options = new RequestOptions({
      headers: headers
    });
    return this.http.post("/api/register", JSON.stringify(serialize(user)), options).map(
      (res:Response) => res.json()
    );
  }

  publishPost(post){
    var token = this.getCachedToken();
    var options = this.getAuthOptions(token);

    return this.http.post("/api/publish", JSON.stringify(serialize(post)), options).map(
      (res:Response) => res.json()
    );
  }

  getComments(id){
    console.log("id");
    return this.http.get("/api/posts/"+ id +"/comments").map(
      (res:Response) => res.json().map(post => <Comment>post));
  }

  publishComment(postId, comment){
    var token = this.getCachedToken();
    var options = this.getAuthOptions(token);

    return this.http.post("/api/posts/"+ postId +"/comment", JSON.stringify(serialize(comment)), options).map(
      (res:Response) => res.json()
    );
  }

  deleteComment(postId, commentId){
    var token = this.getCachedToken();
    var options = this.getAuthOptions(token);
    return this.http.delete("/api/posts/"+ postId +"/comment/" + commentId, options).map(
      (res:Response) => res.json()
    );
  }

}
