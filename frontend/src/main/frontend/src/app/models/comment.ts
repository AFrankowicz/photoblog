import { User } from '../models/user';
import { Post } from '../models/post';
import { JsonProperty } from 'json-typescript-mapper';

export class Comment {
  id: String;

  @JsonProperty('text')
  text: String;

  publishedBy: User;

  on: Post

  createdOn: any;

  updatedOn: any;

  isNull(elem){
    return elem == "";
  }

  hasAllProp(){
    return this.hasOwnProperty('text');
  }

  isAnyEmpty(){
    return  this.isNull(this.text);
  }
}
