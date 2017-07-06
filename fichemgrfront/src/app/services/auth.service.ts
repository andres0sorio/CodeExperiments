import { Injectable } from '@angular/core';
import { Http, RequestOptions, Request, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {

  private authSvcUrl = 'http://localhost:4568';

  constructor(private http: Http) {
  }

  login(user: string, password: string) {

    let body = JSON.stringify({ username: user, password: password });

    this.http.post(this.authSvcUrl + '/auth/login/', body)
      .subscribe((response: Response) => {
        let message = response.json();
        if ((message.errorInd === false) && message.value) {
          localStorage.setItem('token', JSON.stringify("a token"));
        }
      });
  }

  logout(): any {
    localStorage.removeItem('token');
  }

  getUser(): any {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return this.getUser() !== null;
  }

}

export const AUTH_PROVIDERS: Array<any> = [
  { provide: AuthService, useClass: AuthService }
];

