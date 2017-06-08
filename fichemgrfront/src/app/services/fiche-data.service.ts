import { Injectable } from '@angular/core';
import { Fiche } from "../models/fiche";

let FICHES = [
  new Fiche(1, {
    title: "Book One",
    subTitle: "A book about something",
    author: "Andrew McNabb",
    yearPub: 2003,
    editor: "The Mancunian",
    collection: "Greatest Books",
    pages: 200,
    language: "English"
  }),
  new Fiche(2, {
    title: "Book Two",
    subTitle: "A book about something else",
    author: "Wolfgang Gradl",
    yearPub: 2005,
    editor: "Publishing Editors",
    collection: "History of Computing",
    pages: 200,
    language: "German"
  })
];

let fichesPromise = Promise.resolve(FICHES);

@Injectable()
export class FicheDataService {

  static nextFicheId = 100;

  constructor() { }

  addFiche(nfiche: any) {
    console.log('new book name:', nfiche['title']);
    if (1) {
      let fiche = new Fiche(FicheDataService.nextFicheId++, nfiche);
      fichesPromise.then(fiches => fiches.push(fiche));
    }
  }
  
  getFiches() {
    return fichesPromise;
  }

  getFiche(id: number | string) {
    return fichesPromise
      .then(fiches => fiches.find(fiche => fiche.id === +id));
  }



}
