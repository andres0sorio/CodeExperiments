import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FichesComponent } from './fiches.component';
import { FicheListComponent } from './fiche-list/fiche-list.component';
import { FicheDetailComponent } from './fiche-detail/fiche-detail.component';
import { FicheHomeComponent } from './fiche-home/fiche-home.component';
import { FicheAddComponent } from './fiche-add/fiche-add.component';

export const fichesRoutes: Routes = [
  {
    path: 'fiches',
    component: FichesComponent,
    children: [{
      path: '',
      component: FicheHomeComponent,
      children: [
        {
          path: 'id/:id',
          component: FicheDetailComponent
        },
        {
          path: 'list',
          component: FicheListComponent
        },
        {
          path: 'add',
          component: FicheAddComponent
        }]
    }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(fichesRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: []
})
export class FichesRoutingModule { }

/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/