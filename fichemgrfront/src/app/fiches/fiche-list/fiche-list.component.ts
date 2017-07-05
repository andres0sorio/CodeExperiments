import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/switchMap';

import { FicheDataService } from '../../services/fiche-data.service';
import { Fiche } from '../../models/fiche';
import { IFiche } from '../../models/interfaces';

@Component({
  templateUrl: './fiche-list.component.html',
  styleUrls: ['./fiche-list.component.css']
})
export class FicheListComponent implements OnInit {

  errorMessage: string;
  storedFiches: Fiche[];
  mode = 'Observable';

  fiches: Observable<Fiche[]>;
  selectedId: number;
  selectedUuid : string;

  constructor(
    private service: FicheDataService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    /*this.fiches = this.route.params
      .switchMap((params: Params) => {
        this.selectedId = +params['id'];
        return this.service.getFiches();
      });*/
    this.getStoredFiches();
  }

  isSelected(fiche: Fiche) {
    return fiche.id === this.selectedId;
  }

  onSelect(fiche: Fiche) {
    this.selectedId = fiche.id;
    this.selectedUuid = fiche.fiche_uuid;
    // Navigate with relative link
    this.router.navigate(['../' + fiche.id + '/' + fiche.fiche_uuid], { relativeTo: this.route });
  }

  getStoredFiches() {

    this.service.getStoredFiches()
      .subscribe(
      storedFiches => this.storedFiches = storedFiches,
      error => this.errorMessage = <any>error);
  }

}
