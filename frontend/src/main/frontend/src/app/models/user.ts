import { JsonProperty } from 'json-typescript-mapper';

export class User {
  id: string;

  @JsonProperty('username')
  username: string;

  @JsonProperty('password')
  password: string;

  @JsonProperty('name')
  name: string;

  @JsonProperty('surname')
  surname: string;

  @JsonProperty('sex')
  sex: string;

  @JsonProperty('born')
  born: string;

  @JsonProperty('bio')
  bio: string;

  joinedOn: any;

  @JsonProperty('avatar')
  avatar: string;

  isNull(elem){
    return elem == "";
  }

  hasAllProp(){
    return this.hasOwnProperty('username') && this.hasOwnProperty('password')
    && this.hasOwnProperty('name') && this.hasOwnProperty('surname')
    && this.hasOwnProperty('sex') && this.hasOwnProperty('born')
    && this.hasOwnProperty('bio') && this.hasOwnProperty('avatar');
  }

  isAnyEmpty(){
    return  this.isNull(this.username) || this.isNull(this.name)
    || this.isNull(this.surname) || this.isNull(this.sex)
    || this.isNull(this.born) || this.isNull(this.bio)
    || this.isNull(this.avatar);
  }

}
