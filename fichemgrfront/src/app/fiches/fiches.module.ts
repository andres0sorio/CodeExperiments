import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FicheListComponent }  from './fiche-list/fiche-list.component';
import { FicheDetailComponent }  from './fiche-detail/fiche-detail.component';
import { FicheDataService } from '../services/fiche-data.service'

import { FichesRoutingModule } from './fiches-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FichesRoutingModule
  ],
  declarations: [
    FicheListComponent,
    FicheDetailComponent
  ],
  providers: [
    FicheDataService
  ]
})
export class FichesModule { }
