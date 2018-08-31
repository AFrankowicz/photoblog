import { TestBed, async } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterTestingModule } from '@angular/router/testing';
import { AppRoutingModule } from './app.routing';
import { APP_BASE_HREF } from '@angular/common';

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

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
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
        AppRoutingModule
      ],
      providers: [{provide: APP_BASE_HREF, useValue: '/'}, AuthService, AppService]
    }).compileComponents();
  }));

  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
