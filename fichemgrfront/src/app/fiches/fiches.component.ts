import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
//import { Book } from '../models/book';

@Component({
  templateUrl: './fiches.component.html',
  styleUrls: ['./fiches.component.css']
})
export class FichesComponent { 

  constructor(private router: Router, private route: ActivatedRoute) { };

  goToFiche(id: string): void {
    this.router.navigate(['./id/', id], {relativeTo: this.route});
  }

}
