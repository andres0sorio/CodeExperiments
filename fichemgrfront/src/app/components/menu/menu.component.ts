import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../services/jwt.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(public jwtservice: JwtService) { }

  ngOnInit() {
  }

}
