import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FichesComponent } from './fiches.component';
import { FicheListComponent }  from './fiche-list/fiche-list.component';
import { FicheDetailComponent }  from './fiche-detail/fiche-detail.component';
import { FicheHomeComponent } from './fiche-home/fiche-home.component';

import { FicheDataService } from '../services/fiche-data.service'

import { FichesRoutingModule } from './fiches-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    FichesRoutingModule
  ],
  declarations: [
    FichesComponent,
    FicheListComponent,
    FicheDetailComponent,
    FicheHomeComponent
  ],
  providers: [
    FicheDataService
  ]
})
export class FichesModule { }
