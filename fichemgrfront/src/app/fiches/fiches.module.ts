import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { FichesComponent } from './fiches.component';
import { FicheListComponent }  from './fiche-list/fiche-list.component';
import { FicheDetailComponent }  from './fiche-detail/fiche-detail.component';
import { FicheHomeComponent } from './fiche-home/fiche-home.component';

import { FicheDataService } from '../services/fiche-data.service'

import { FichesRoutingModule } from './fiches-routing.module';
import { FicheAddComponent } from './fiche-add/fiche-add.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    FichesRoutingModule
  ],
  declarations: [
    FichesComponent,
    FicheListComponent,
    FicheDetailComponent,
    FicheHomeComponent,
    FicheAddComponent
  ],
  providers: [
    FicheDataService
  ]
})
export class FichesModule { }
