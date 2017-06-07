import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FichesComponent } from './fiches.component';
import { FicheListComponent } from './fiche-list/fiche-list.component';
import { FicheDetailComponent } from './fiche-detail/fiche-detail.component';

export const fichesRoutes: Routes = [
  { path: '', redirectTo: 'fiche-list', pathMatch: 'full' },
  { path: 'fiche-list', component: FicheListComponent },
  { path: ':id', component: FicheDetailComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(fichesRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class FichesRoutingModule { }

/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/