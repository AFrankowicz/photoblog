import { User } from '../models/user';
import { JsonProperty } from 'json-typescript-mapper';

export class Post {
  id: String;

  @JsonProperty('title')
  title: String;

  @JsonProperty('content')
  content: String;

  @JsonProperty('description')
  description: String;

  publishedBy: User;

  createdOn: any;

  updatedOn: any;

  commentsNum: number = 0;

  isNull(elem){
    return elem == "";
  }

  hasAllProp(){
    return this.hasOwnProperty('title') && this.hasOwnProperty('content')
    && this.hasOwnProperty('description');
  }

  isAnyEmpty(){
    return  this.isNull(this.title) || this.isNull(this.content)
    || this.isNull(this.description);
  }
}
