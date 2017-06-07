import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from '../components/home/home.component';
import { MenuComponent } from '../components/menu/menu.component';
import { StatsComponent } from '../components/stats/stats.component';
import { FichesComponent } from '../fiches/fiches.component';

import { PageNotFoundComponent } from '../components/page-not-found/page-not-found.component';
import { SelectivePreloadingStrategy } from '../selective-preloading-strategy';

import { fichesRoutes as childRoutes, FichesRoutingModule } from '../fiches/fiches-routing.module';

const appRoutes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'fiches', component: FichesComponent, children: childRoutes },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, { preloadingStrategy: SelectivePreloadingStrategy }),
    FichesRoutingModule
  ],
  exports: [
    RouterModule
  ],
  declarations: [],
  providers: [
    SelectivePreloadingStrategy
  ]
})

export class AppRoutingModule { }
