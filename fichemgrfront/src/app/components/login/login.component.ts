import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  message: string;

  constructor(public authService: AuthService) {
    this.message = "";
  }

  login(username: string, password: string): boolean {
    this.message = '';
    this.authService.login(username, password);
    /*
    if (!this.authService.hasToken()) {
      this.message = 'Incorrect credentials.';
      setTimeout(function () {
        this.message = '';
      }.bind(this), 2500);
    } */
    return false;
  }

  logout(): boolean {
    this.authService.logout();
    return false;
  }

}
