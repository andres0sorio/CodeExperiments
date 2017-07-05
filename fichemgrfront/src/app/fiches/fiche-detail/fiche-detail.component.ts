import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Fiche } from "../../models/fiche";
import { FicheDataService } from '../../services/fiche-data.service';

@Component({
  selector: 'app-fiche-detail',
  templateUrl: './fiche-detail.component.html',
  styleUrls: ['./fiche-detail.component.css']
})
export class FicheDetailComponent implements OnInit {

  id: string;
  uuid: string;
  fiche : Fiche;

  constructor(private service: FicheDataService,
    private router: Router,
    private route: ActivatedRoute) {  }

  ngOnInit() {
    this.route.params.subscribe(params => { this.id = params['id']; this.uuid = params['uuid'];});
    this.service.getStoredFiche(this.id, this.uuid).subscribe( (retrieveFiche: Fiche ) => this.fiche = retrieveFiche);
  }

  gotoFiches() {
    /*
    let heroId = this.hero ? this.hero.id : null;
    // Pass along the hero id if available
    // so that the HeroList component can select that hero.
    // Include a junk 'foo' property for fun.
    */
    let ficheId = this.id;
    this.router.navigate(['/fiches/list', { id: ficheId }]);

  }

}
