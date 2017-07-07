import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
import { Router } from '@angular/router';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing/app-routing.module';

import { HomeComponent } from './components/home/home.component';
import { MenuComponent } from './components/menu/menu.component';
import { StatsComponent } from './components/stats/stats.component';
import { LoginComponent } from './components/login/login.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { FichesModule } from './fiches/fiches.module';

import { AUTHSVC_PROVIDERS } from './services/auth.service';
import { JWT_PROVIDERS } from './services/jwt.service';
import { MessageService } from './services/message.service';
import { LoggedInGuard } from './logged-in.guard';

import { AUTH_PROVIDERS, JwtHelper } from 'angular2-jwt';
import { AuthModule } from './helpers/auth/auth.module';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AuthModule,
    JsonpModule,
    FichesModule,
    AppRoutingModule,
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    MenuComponent,
    StatsComponent,
    PageNotFoundComponent,
    LoginComponent
  ],
  providers: [
    AUTHSVC_PROVIDERS,
    JWT_PROVIDERS,
    LoggedInGuard,
    MessageService,
    JwtHelper
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 

  constructor( router : Router ) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }

}
