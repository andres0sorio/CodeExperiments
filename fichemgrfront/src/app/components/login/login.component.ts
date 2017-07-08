import { Component, OnDestroy } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { MessageService } from '../../services/message.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(public authService: AuthService, private messageService: MessageService) { }

  login(username: string, password: string) {

    this.authService.login(username, password).subscribe(
      data => {
        this.messageService.sendMessage('notice','Welcome!');
        setTimeout(function () {
          this.messageService.clearMessage();
        }.bind(this), 2500);
        this.authService.useJwtHelper();
      },
      error => {
        this.messageService.sendMessage('warning', 'Incorrect credentials');
        setTimeout(function () {
          this.messageService.clearMessage();
        }.bind(this), 2500);
      });

  }

  logout(): boolean {
    this.authService.logout();
    return false;
  }

}
