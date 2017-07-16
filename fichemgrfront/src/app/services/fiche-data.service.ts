import { Injectable } from '@angular/core';
import { Http, RequestOptions, Request, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { Fiche } from "../models/fiche";
import { Book } from "../models/book";
import { IFiche } from "../models/interfaces";
import { MOCKFICHES } from "../models/mock-fiches";
import { AuthHttp } from 'angular2-jwt';

let fichesPromise = Promise.resolve(MOCKFICHES);

@Injectable()
export class FicheDataService {

  private backendUrl = 'https://fast-sea-84532.herokuapp.com';  // URL to web API 
  //private backendUrl = 'http://localhost:4567';
  contentHeaders = new Headers();

  constructor(private http: Http, public authHttp: AuthHttp) {
    this.contentHeaders.append('Accept', 'application/json');
    this.contentHeaders.append('Content-Type', 'application/json');
  }

  addFiche(data: any) {
    console.log('new book name:', data['title']);
    console.log('some comments:', data['comments']);
    if (1) {

      let comments = data['comments'];
      delete data['comments'];
      let ficheInfo: any = { "id": 0, "book": data, "comments": comments };
      console.log(ficheInfo);
      let fiche = new Fiche(ficheInfo);
      fichesPromise.then(fiches => fiches.push(fiche));
    }
  }

  getFiches() {
    return fichesPromise;
  }

  //POST 1.
  createFiche(user: string, data: any) {
    console.log('new book name:', data['title']);
    console.log('some comments:', data['comments']);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    if (1) {

      let comments = data['comments'];
      /* 
      comments.forEach(element => {
        element.author = user;
      });
      */
      delete data['comments'];
      let ficheInfo: any = { "id": 0, "book": data, "comments": comments };
      let fiche = new Fiche(ficheInfo);
      console.log(JSON.stringify(fiche));
      this.authHttp.post(this.backendUrl + "/users/fiches/", JSON.stringify(fiche), options)
        .map((res: Response) => res.json())
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'))
        .subscribe();
    }
  }

  //POST 2.
  updateFiche(id: string, uuid: string, data: any) {
    console.log('new book name:', data['title']);
    console.log('some comments:', data['comments']);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    if (1) {

      let comments = data['comments'];
      delete data['comments'];
      data.book_uuid = uuid;
      let ficheInfo: any = { "id": id, "book": data, "comments": comments };
      let fiche = new Fiche(ficheInfo);
      console.log(JSON.stringify(fiche));
      this.authHttp.put(this.backendUrl + "/users/fiches/" + id + "/" + uuid, JSON.stringify(fiche), options)
        .map((res: Response) => res.json())
        .catch((error: any) => Observable.throw(error.json().error || 'Server error'))
        .subscribe();
    }
  }

  // GET 1.
  getStoredFiches(): Observable<Fiche[]> {
    let options = new RequestOptions({ headers: this.contentHeaders });

    return this.authHttp.get(this.backendUrl + "/users/fiches/", options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  //GET 1. method 2
  getStoredIFiches(): Observable<IFiche[]> {
    return this.authHttp.get(this.backendUrl + "/users/fiches/")
      .map(response => response.json() as IFiche[])
      .catch(this.handleError);
  }

  // GET 2.
  getStoredFiche(id: string, uuid: string): Observable<Fiche> {

    return this.authHttp.get(this.backendUrl + "/users/fiches/" + id + "/" + uuid)
      .map(this.extractData)
      .catch(this.handleError);
  }

  // DELETE 1.
  deleteStoredFiche(id: string, uuid: string) {

    this.authHttp.delete(this.backendUrl + "/users/fiches/" + id + "/" + uuid)
      .map(this.extractData)
      .catch(this.handleError).subscribe();

  }

  //MOCK
  getFiche(id: number | string) {
    return fichesPromise
      .then(fiches => fiches.find(fiche => fiche.id === +id));
  }

  private extractData(res: Response) {
    let body = res.json();
    console.log("Body: ", body);
    return body;
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
