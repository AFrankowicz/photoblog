import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';

import { AppComponent } from './app.component';

import { NavbarComponent } from './navbar/navbar.component';
import { BoardComponent } from './board/board.component';
import { PostComponent } from './post/post.component';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

import { AuthService } from './services/auth.service';
import { AppService } from './services/app.service';
import { RegisterComponent } from './register/register.component';
import { PublishComponent } from './publish/publish.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    BoardComponent,
    PostComponent,
    UserComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    PublishComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [AuthService, AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
