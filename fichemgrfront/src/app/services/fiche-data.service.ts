import { Injectable } from '@angular/core';
import { Http, RequestOptions, Request, Response , Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Fiche } from "../models/fiche";

let FICHES = [
  new Fiche({
    id: 1, book: {
      title: "Book One",
      subTitle: "A book about something",
      author: "Andrew McNabb",
      yearPub: 2003,
      editor: "The Mancunian",
      collection: "Greatest Books",
      pages: 200,
      language: "English"
    }
  }),
  new Fiche({
    id: 2, book: {
      title: "Book Two",
      subTitle: "A book about something else",
      author: "Wolfgang Gradl",
      yearPub: 2005,
      editor: "Publishing Editors",
      collection: "History of Computing",
      pages: 200,
      language: "German"
    }
  })
];

let fichesPromise = Promise.resolve(FICHES);

@Injectable()
export class FicheDataService {

  private backendUrl = 'http://localhost:4567/objects';  // URL to web API 

  static nextFicheId = 100;

  constructor(private http: Http) { }

  addFiche(nbook: any) {
    console.log('new book name:', nbook['title']);
    if (1) {
      let fiche = new Fiche({ id: FicheDataService.nextFicheId++, book: nbook });
      fichesPromise.then(fiches => fiches.push(fiche));
    }
  }

  getFiches() {
    return fichesPromise;
  }

  // GET
  getStoredFiches(): Observable<Fiche[]> {

    return this.http.get(this.backendUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }
  //(r: Response) => r.json() as Fiche[])
  private extractData(res: Response) {
    let body = res.json();
    console.log("Body: ", body);
    return body;
  }

  getFiche(id: number | string) {
    return fichesPromise
      .then(fiches => fiches.find(fiche => fiche.id === +id));
  }

  private handleError(error: Response | any) {
    // In a real world app, you might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error("handleError:", errMsg);
    return Observable.throw(errMsg);
  }

}
