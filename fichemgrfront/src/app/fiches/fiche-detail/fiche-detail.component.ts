import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-fiche-detail',
  templateUrl: './fiche-detail.component.html',
  styleUrls: ['./fiche-detail.component.css']
})
export class FicheDetailComponent  {

  id: string;

  constructor(private router: Router, private route: ActivatedRoute) { 
    route.params.subscribe(params => { this.id = params['id']; });
  }

}
