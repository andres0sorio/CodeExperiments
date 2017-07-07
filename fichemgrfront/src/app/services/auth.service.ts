import { Injectable } from '@angular/core';
import { Http, RequestOptions, Request, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { JwtHelper } from 'angular2-jwt';

@Injectable()
export class AuthService {

  private authSvcUrl = 'http://localhost:4568';

  jwtHelper: JwtHelper = new JwtHelper();

  constructor(private http: Http) {
  }

  login(user: string, password: string) {

    let body = JSON.stringify({ username: user, password: password });

    return this.http.post(this.authSvcUrl + '/auth/login/', body)
      .map((response: Response) => {
        let message = response.json();
        if ((message.errorInd === false) && message.value) {
          localStorage.setItem('token', message.value);
        }
      });
  }

  logout(): any {
    localStorage.removeItem('token');
  }

  getUser(): any {
    var token = localStorage.getItem('token');
    if ( token !== null ) {
      let user = this.jwtHelper.decodeToken(token)["sub"];  
      return user;
    }
    return null;
  }

  getToken(): any {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return this.getUser() !== null;
  }

  useJwtHelper() {
    var token = localStorage.getItem('token');
    console.log(token);

    console.log(
      this.jwtHelper.decodeToken(token),
      this.jwtHelper.getTokenExpirationDate(token),
      this.jwtHelper.isTokenExpired(token)
    );
    console.log(this.jwtHelper.decodeToken(token)["sub"]);
  }


}

export const AUTHSVC_PROVIDERS: Array<any> = [
  { provide: AuthService, useClass: AuthService }
];

