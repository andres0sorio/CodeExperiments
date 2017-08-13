import { Component, OnDestroy } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { MessageService } from '../../services/message.service';
import { LocaleService } from '../../services/locale.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public labels : any;
  public messageLabels : any;

  constructor(public authService: AuthService, private messageService: MessageService, private locale : LocaleService) { 

    this.labels = locale.get("login");
    this.messageLabels = locale.get("messages");
    
  }

  login(username: string, password: string) {

    this.authService.login(username, password).subscribe(
      data => {
        this.messageService.sendMessage('notice',this.messageLabels[0].notice.welcome);
        setTimeout(function () {
          this.messageService.clearMessage();
        }.bind(this), 2500);
        this.authService.useJwtHelper();
      },
      error => {
        this.messageService.sendMessage('warning', this.messageLabels[0].warning.wrongcredentials);
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
